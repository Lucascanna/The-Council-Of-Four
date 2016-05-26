package server.model.actions.standardActions;

import java.util.ArrayList;
import java.util.List;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ChangePermitTilesDTO;
import modelDTO.actionsDTO.NeedParameters;
import players.Player;
import server.model.Game;
import server.model.actions.QuickAction;
import server.model.gameTable.RegionBoard;
import server.view.notifies.GameNotify;

/**
 * It's the quick action "change permit tiles" it operates on the 
 * protected attribute game through the method executeAction.
 * The controller will pass to the constructor the region where to change the two uncovered permit tiles  
 * @author Luca
 *
 */
public class ChangePermitTiles extends QuickAction implements NeedParameters{
	
	private RegionBoard selectedRegion;
	private static final int necessaryAssistants=1;
	
	public void setSelectedRegion(RegionBoard selectedRegion) {
		this.selectedRegion = selectedRegion;
	}
	
	
	private boolean checkAssistant(Game game){
		return game.getCurrentPlayer().getNumberOfAssistants()>=necessaryAssistants;
	}
	
	
	/**
	 * Changes the two uncovered permit tiles of the selected region
	 * and decrements the necessary assistant to the current player.
	 * @return TRUE if the action ends well; FALSE otherwise.
	 */
	public boolean executeAction(Game game) throws NullPointerException{
		
		if(this.selectedRegion==null)
			throw new NullPointerException("Paramters not setted");
		
		List<Player> interestedPlayers=new ArrayList<Player>();
		interestedPlayers.add(game.getCurrentPlayer());
		
		if(!this.checkAssistant(game)){
			this.sendErrorNotify(game, interestedPlayers);
			return false;
		}
		for(RegionBoard region : game.getGameTable().getRegionBoards())
			if(region.equals(selectedRegion))
				region.substitutePermitTiles();
		game.getCurrentPlayer().decrementAssistants(necessaryAssistants);
		
		this.nextState(game);
		game.notifyObserver(new GameNotify(game,interestedPlayers));
		return true;
	}

	@Override
	public String toString() {
		return "q2: change the permit tiles of a region";
	}


	@Override
	public ActionDTO map() {
		
		return new ChangePermitTilesDTO();
	}		



	}