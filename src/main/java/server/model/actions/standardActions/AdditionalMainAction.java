package server.model.actions.standardActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.standardActions.AdditionalMainActionDTO;
import server.model.Game;
import server.model.actions.QuickAction;
import server.model.player.Player;
import server.serverNotifies.ErrorServerNotify;
import server.serverNotifies.MessageServerNotify;
import server.serverNotifies.PlayerServerNotify;

/**
 * It's the quick action "additional main action"
 * @author cg31
 *
 */
public class AdditionalMainAction implements QuickAction {

	private static final int necessaryAssistants=3;
	
	private boolean checkAssistants(Game game){
		return (game.getCurrentPlayer().getNumberOfAssistants()>=necessaryAssistants);
	}
	
	/**
	 * Decrements the necessaryAssistants to the current player and give him
	 * an additional main action
	 * @return TRUE if the action ends well; FALSE otherwise.
	 */
	@Override
	public boolean executeAction(Game game) {
		
		if (!this.checkAssistants(game)) {
			game.notifyObserver(new ErrorServerNotify("It seems that you haven't enough assistants! Try again or choose another action", 
					Arrays.asList(game.getCurrentPlayer())));
			return false;
		}
		
		game.getCurrentPlayer().decrementAssistants(necessaryAssistants);
	
		this.notifyPlayers(game);
		game.setState(game.getState().additionalMainActionTransition());
		game.getState().updateClients(game);
		
		return true;
	}
	
	
	@Override
	public void notifyPlayers(Game game) {
		game.notifyObserver(new PlayerServerNotify(game, game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new MessageServerNotify("Action completed succesfully!", 
				Arrays.asList(game.getCurrentPlayer())));
		List<Player> otherPlayers=new ArrayList<>();
		for (Player player : game.getPlayers())
			if (!player.equals(game.getCurrentPlayer()))
				otherPlayers.add(player);
		game.notifyObserver(new MessageServerNotify(game.getCurrentPlayer().getName()
				+ "vgot an additional main action", otherPlayers));
	}

	@Override
	public ActionDTO map() {
		return new AdditionalMainActionDTO();
	}

}