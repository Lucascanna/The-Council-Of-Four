package server.model.actions.standardActions;

import java.util.Arrays;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.standardActions.BuildByPermitTileDTO;
import server.model.Game;
import server.model.actions.MainAction;
import server.model.bonus.Bonus;
import server.model.gameTable.City;
import server.model.gameTable.ConnectedBuiltCityDiscover;
import server.model.gameTable.Emporium;
import server.model.gameTable.PermitTile;
import server.view.notifies.ErrorNotify;

/**
 * This action allows the current player to build in one city from those which
 * are indicated by the permit deck. After that, the player catches the bonuses
 * of that city plus all of the linked cities in which he has already built
 * @author Emanuele
 *
 */

public class BuildByPermitTile extends MainAction {

	private PermitTile selectedPermitTile;
	private City selectedCity;
	
	
	public void setSelectedPermitTile(PermitTile selectedPermitTile) {
		this.selectedPermitTile = selectedPermitTile;
	}


	public void setSelectedCity(City selectedCity) {
		this.selectedCity = selectedCity;
	}


	/**
	 * First of all checks if the parameters the current player gave are corrected,
	 * then removes the emporium from the hand of the player an adds it on the set of emporiums 
	 * of the selected city, then assigns all the bonuses of liked cities, then decrements player's assistants
	 * @return TRUE if the action goes well, false otherwise
	 */
	@Override
	public boolean executeAction(Game game) throws NullPointerException {
		if(this.selectedCity==null || this.selectedPermitTile==null) {
			throw new NullPointerException("Paramters not setted");
		}
		
		ConnectedBuiltCityDiscover likedCities=new ConnectedBuiltCityDiscover();
		
		if (!checkCityNotContainsEmporium(game)) {
			game.notifyObserver(new ErrorNotify("It seems that this city arelady contains your emporium!. Try again or choose another action", 
					Arrays.asList(game.getCurrentPlayer())));
			return false;
		}
		if (!checkPermitTileContainsCity()) {
			game.notifyObserver(new ErrorNotify("It seems that the permit tile you selected doesn't contain the city in which you want to build!. Try again or choose another action", 
					Arrays.asList(game.getCurrentPlayer())));
			return false;
		}	
		if (!checkEnoughAssistants(game)) {
			game.notifyObserver(new ErrorNotify("It seems that you haven't enough assistants!. Try again or choose another action", 
					Arrays.asList(game.getCurrentPlayer())));
			return false;
		}
		
		game.getCurrentPlayer().decrementAssistants(assistantsToPay());	
		Emporium temporaryEmporium=game.getCurrentPlayer().removeEmporium();
		this.selectedCity.addEmporium(temporaryEmporium);
		for (City city : likedCities.getConnectedBuiltCities(game.getGameTable().getMap().getGameMap(), this.selectedCity, temporaryEmporium))
			for (Bonus bonusToAssign : city.getRewardToken())
				bonusToAssign.assignBonus(game);
		game.getCurrentPlayer().getPlayersPermitTilesTurnedDown().add(this.selectedPermitTile);
		game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().remove(this.selectedPermitTile);

		if (this.selectedCity.getRegion().isBonusAvailable())
			assignRegionBonus(game);
		if (this.selectedCity.getColour().isBonusAvailable())
			assignColourBonus(game);
		
		if(game.getCurrentPlayer().getRemainigEmporiums().size()==0){
			game.setLastLap(true);
			game.getCurrentPlayer().incrementScore(3);
		}
		
		this.nextState(game);
		
		return true;
	}
	
	/**
	 * Checks if the selected city already contains the emporium of the current player
	 * @return TRUE if the selected city doesn't contain the emporium, false otherwise
	 */
	private boolean checkCityNotContainsEmporium(Game game) {
		for (Emporium emporium : this.selectedCity.getCityEmporiums())
			if (emporium.getEmporiumsPlayer().equals(game.getCurrentPlayer()))
				return false;
		return true;
	}
	
	/**
	 * Checks if the selected permit tile contains the city in which you want to build
	 * @return TRUE if the selected permit tile contains the selected city, FALSE otherwise
	 */
	private boolean checkPermitTileContainsCity() {
		return this.selectedPermitTile.getBuildableCities().contains(this.selectedCity);
	}
	
	/**
	 * Checks if player's assistants are enough to build an emporium in the selected city
	 * @return TRUE if the assistants are enough, FALSE otherwise
	 */
	private boolean checkEnoughAssistants(Game game) {
		return game.getCurrentPlayer().getNumberOfAssistants() >= 
				assistantsToPay();
	}
	
	/**
	 * @return the amount of assistants to pay
	 */
	private int assistantsToPay() {
		return this.selectedCity.getCityEmporiums().size();
	}
	
	/**
	 * Checks if, after an emporium build, the current player has completed the region.
	 * in case of that, the player picks the region board bonus, and if they are available, 
	 * he also picks one king reward tile
	 */
	private void assignRegionBonus(Game game) {
		int regionCitiesCounter=0;
		for (City city : this.selectedCity.getRegion().getRegionCities())
			for (Emporium emporium : city.getCityEmporiums())
				if (emporium.getEmporiumsPlayer().equals(game.getCurrentPlayer()))
					regionCitiesCounter++;
		if (regionCitiesCounter==this.selectedCity.getRegion().getRegionCities().size()) {
			game.getCurrentPlayer().getPlayersFinalBonus().add(
			this.selectedCity.getRegion().getRegionBonus());
			this.selectedCity.getRegion().notBonusAvailable();
			if (!(game.getGameTable().getKingRewardTiles().isEmpty()))
				assignKingRewardTile(game);
		}
	}

	/**
	 * Checks if, after an emporium build, the current player has completed the cities of that colour.
	 * in case of that, the player picks the region board bonus, and if they are available, 
	 * he also picks one king reward tile
	 */
	private void assignColourBonus(Game game) {
		int colourCitiesCounter=0;
		for (City city : this.selectedCity.getColour().getCitiesOfThisColour())
			for (Emporium emporium : city.getCityEmporiums())
				if (emporium.getEmporiumsPlayer().equals(game.getCurrentPlayer()))
					colourCitiesCounter++;
		if (colourCitiesCounter==this.selectedCity.getColour().getCitiesOfThisColour().size()) {
			game.getCurrentPlayer().getPlayersFinalBonus().add(
			this.selectedCity.getColour().getColorBonus());
			this.selectedCity.getColour().notBonusAvailable();
			if (!(game.getGameTable().getKingRewardTiles().isEmpty()))
				assignKingRewardTile(game);
		}
	}
	
	/**
	 * Remove the king reward tile from the game table and assigns it to the player
	 */
	private void assignKingRewardTile(Game game) {
		game.getCurrentPlayer().getPlayersFinalBonus().add(
		game.getGameTable().getKingRewardTiles().remove(0));
	}
	

	@Override
	public ActionDTO map() {
		return new BuildByPermitTileDTO();
	}


}