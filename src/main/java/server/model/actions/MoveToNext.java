package server.model.actions;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.MoveToNextDTO;
import server.model.Game;
import server.model.player.Player;
import server.serverNotifies.MessageServerNotify;

/**
 * This class modelizes the skip action
 * @author cg31
 *
 */
public class MoveToNext implements QuickAction {
	
	/**
	 * If the lap is finished starts the market phase, otherwise sets the next player.
	 */
	@Override
	public boolean executeAction(Game game) {
		
		this.updateClients(game);
		game.setState(game.getState().moveToNextTransition(game));
		game.getState().updateAvailableActions(game);
		
		return true;
	}

	@Override
	public void updateClients(Game game) {
		List<Player> otherPlayers=new ArrayList<>();
		for (Player player : game.getPlayers())
			if (!player.equals(game.getCurrentPlayer()))
				otherPlayers.add(player);
		game.notifyObserver(new MessageServerNotify(game.getCurrentPlayer().getName()
				+ " decided to skip the turn", otherPlayers));
	}

	@Override
	public ActionDTO map() {
		return new MoveToNextDTO();
	}

}
