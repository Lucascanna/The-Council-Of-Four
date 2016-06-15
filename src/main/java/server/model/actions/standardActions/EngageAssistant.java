package server.model.actions.standardActions;

import java.util.Arrays;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.standardActions.EngageAssistantDTO;
import server.model.Game;
import server.model.actions.QuickAction;
import server.view.notifies.ErrorNotify;
/**
 * It's the quick action "engage assistants" it operates on the 
 * protected attribute game through the method executeAction.
 * @author Luca
 *
 */
public class EngageAssistant extends QuickAction {

	private static final int necessaryCoins=3;

	
	private boolean checkCoins(Game game){
		return game.getCurrentPlayer().getCoins()>=necessaryCoins;
	}

	/**
	 * Decrements 3 coins to the current player and gives him an assistant.
	 * @return TRUE if the action ends well; FALSE otherwise.
	 */
	@Override
	public boolean executeAction(Game game) {
		
		if (!this.checkCoins(game)){
			game.notifyObserver(new ErrorNotify("It seems that you haven't enough coins!. Try again or choose another action", 
					Arrays.asList(game.getCurrentPlayer())));
			return false;
		}
		
		game.getCurrentPlayer().decrementCoins(necessaryCoins);
		game.getCurrentPlayer().incrementAssistants(1);
		
		this.nextState(game);
		
		return true;
	}
	
	
	@Override
	public ActionDTO map() {
		return new EngageAssistantDTO();
	}
	
}