package server.model.actions.standardActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.standardActions.BuildByKingDTO;
import players.Player;
import server.model.Game;
import server.model.actions.MainAction;
import server.model.bonus.Bonus;
import server.model.gameTable.City;
import server.model.gameTable.ConnectedBuiltCityDiscover;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.Councillor;
import server.model.gameTable.Emporium;
import server.model.gameTable.PoliticsCard;
import server.model.gameTable.RegionBoard;
import server.view.notifies.AvailableActionsNotify;
import server.view.notifies.ErrorNotify;
import server.view.notifies.GameTableNotify;
import server.view.notifies.PlayerNotify;

/**
 * This class models the build an emporium with king's help action
 * @author Emanuele
 *
 */

public class BuildByKing extends MainAction {

	private City selectedCity;
	private List<PoliticsCard> cardsToDescard;
	
	
	public void setSelectedCity(City selectedCity) {
		this.selectedCity = selectedCity;
	}



	public void setCardsToDescard(List<PoliticsCard> cardsToDescard) {
		this.cardsToDescard = cardsToDescard;
	}



	/**
	 * First of all checks if the parameters the current player gave are corrected, 
	 * then removes the cards from the hand of the player and decrements his coins, 
	 * then removes the emporium from the hand of the player an adds it on the set of emporiums 
	 * of the selected city, then assigns all the bonuses of liked cities, then decrements player's assistants
	 * @return TRUE if the action goes well, false otherwise
	 */
	@Override
	public boolean executeAction(Game game) throws NullPointerException{
		
		if(this.cardsToDescard==null || this.selectedCity==null)
			throw new NullPointerException("Paramters not setted");
		
		ConnectedBuiltCityDiscover likedCities=new ConnectedBuiltCityDiscover();
		
		if (!checkCityNotContainsEmporium(game)) {
			game.notifyObserver(new ErrorNotify("It seems that you haven't enough assistants!",
					Arrays.asList(game.getCurrentPlayer())));
			return false;
		}
		if (!checkEnoughAssistants(game)) {
			game.notifyObserver(new ErrorNotify("It seems that you haven't enough assistants!",
					Arrays.asList(game.getCurrentPlayer())));
			return false;
		}
		if (!CheckHandSatisfiesBalcony(game)) {
			game.notifyObserver(new ErrorNotify("It seems that you haven't enough assistants!",
					Arrays.asList(game.getCurrentPlayer())));
			return false;
		}	
		if (!CheckEnoughCoins(game)) {
			game.notifyObserver(new ErrorNotify("It seems that you haven't enough assistants!",
					Arrays.asList(game.getCurrentPlayer())));
			return false;
		}
		
		game.getCurrentPlayer().decrementCoins(CoinsToPay(game));
		for (PoliticsCard card : cardsToDescard)
			game.getCurrentPlayer().removeCardFromHand(card);
		
		Emporium temporaryEmporium=game.getCurrentPlayer().removeEmporium();
		this.selectedCity.addEmporium(temporaryEmporium);
		for (City city : likedCities.getConnectedBuiltCities(game.getGameTable().getMap().getGameMap(), this.selectedCity, temporaryEmporium))
			for (Bonus bonusToAssign : city.getRewardToken())
				bonusToAssign.assignBonus(game);
		game.getCurrentPlayer().decrementAssistants(assistantsToPay(game));
		findKing(game).setIsKingPresent(false);
		this.selectedCity.setIsKingPresent(true);

		if (this.selectedCity.getRegion().isBonusAvailable())
			assignRegionBonus(game);
		if (this.selectedCity.getColour().isBonusAvailable())
			assignColourBonus(game);
		
		this.nextState(game);

		game.notifyObserver(new GameTableNotify(game, new ArrayList<Player>(game.getPlayers())));
		game.notifyObserver(new PlayerNotify(game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new AvailableActionsNotify(game.getState().getAcceptableActions(game), 
				Arrays.asList(game.getCurrentPlayer())));
		
		return true;
	}
	
	/**
	 * Finds the city where the king is located
	 * @return the city with the king
	 */
	private City findKing(Game game) {
		for (RegionBoard region : game.getGameTable().getRegionBoards())
			for (City city : region.getRegionCities())
				if (city.getIsKingPresent()==true)
					return city;
		return selectedCity;
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
	 * Checks if player's assistants are enough to build an emporium in the selected city
	 * @return TRUE if the assistants are enough, FALSE otherwise
	 */
	private boolean checkEnoughAssistants(Game game) {
		return game.getCurrentPlayer().getNumberOfAssistants() >= 
				assistantsToPay(game);
	}
	
	/**
	 * checks if the player has enough coins
	 */
	private boolean CheckEnoughCoins(Game game) {
		return game.getCurrentPlayer().getCoins() >= CoinsToPay(game);
	}
	
	/**
	 * @return the amount of assistants to pay
	 */
	private int assistantsToPay(Game game) {
		return this.selectedCity.getCityEmporiums().size()+
				2*game.getGameTable().getMap().getShortestPathLenght(this.selectedCity, findKing(game));
	}
	
	/**
	 * Calculate the coins you have to pay according to the cards you
	 * selected and the number of rainbow politics cards
	 * @return the amount of coins
	 */
	private int CoinsToPay(Game game) {
		int coinsToPay;
		game.getGameTable().getCouncilOfKing();
		if (this.cardsToDescard.size()==CouncilBalcony.getNumberofcouncillors())
			coinsToPay=0;
		else
			coinsToPay=((CouncilBalcony.getNumberofcouncillors()-(this.cardsToDescard.size()))*3)+1;
		for (PoliticsCard card : cardsToDescard)
			if (card.getColour().getColour() == "rainbow")
				coinsToPay++;
		return coinsToPay;
	}
	
	/**
	 * checks if the player hands cards' colour matches with the colour of councillors
	 * of the selected balcony
	 */
	private boolean CheckHandSatisfiesBalcony(Game game) {
		List<Councillor> temporaryBalcony=new ArrayList<Councillor>();
		int satisfyCounter=0;
		for (int i=0; i<=CouncilBalcony.getNumberofcouncillors()-1; i++)
			temporaryBalcony.add(game.getGameTable().getCouncilOfKing().getCouncillors()[i]);
		
		for (PoliticsCard politicsCardInHand: this.cardsToDescard) {
			if (politicsCardInHand.getColour().getColour().equals("Rainbow"))
				satisfyCounter++;		
				for (Councillor councillorToSatisfy : temporaryBalcony)
				if (councillorToSatisfy.getColour().getColour().equals(politicsCardInHand.getColour().getColour())) {
					temporaryBalcony.remove(councillorToSatisfy);
					satisfyCounter++;
					break;
				} 
		}
		return satisfyCounter==this.cardsToDescard.size();
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
		}
		if (!(game.getGameTable().getKingRewardTiles().isEmpty()))
			assignKingRewardTile(game);
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
		}
		if (!(game.getGameTable().getKingRewardTiles().isEmpty()))
			assignKingRewardTile(game);
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
		return new BuildByKingDTO();
	}

}