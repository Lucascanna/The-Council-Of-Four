package server.model.actions;

import server.model.Game;

/**
 * The class that models the generic quick actions of the game
 * @author Luca
 *
 */
public interface QuickAction extends Action {
	
	public default void nextState(Game game){
		game.setState(game.getState().quickActionTransition(game));
		game.getState().updateClients(game);
	}
}