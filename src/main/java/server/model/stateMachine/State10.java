package server.model.stateMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import players.Player;
import server.model.Game;
import server.model.actions.Action;
import server.model.actions.standardActions.AcquirePermitTile;
import server.model.actions.standardActions.BuildByKing;
import server.model.actions.standardActions.BuildByPermitTile;
import server.model.actions.standardActions.ElectCouncillor;
import server.view.notifies.AvailableActionsNotify;
import server.view.notifies.GameTableNotify;
import server.view.notifies.PlayerNotify;

public class State10 implements State{
	
	@Override
	public State mainActionTransition(Game game) {
		
		if (game.getCurrentPlayer().getPlayerNumber()!=game.getPlayers().size()+game.getQuittedPlayers().size()){
			game.nextPlayer();
			return new BeginState();
		}
		else {
			game.nextPlayer();
			game.startMarket();
			return new SellingState();
		}
	}
		
	@Override
	public List<Action> getAcceptableActions(Game game) {
		return Arrays.asList(
				new ElectCouncillor(),
				new AcquirePermitTile(),
				new BuildByPermitTile(),
				new BuildByKing());
	}

	@Override
	public void updateClients(Game game) {
		game.notifyObserver(new GameTableNotify(game, new ArrayList<Player>(game.getPlayers())));
		game.notifyObserver(new PlayerNotify(game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new AvailableActionsNotify(game.getState().getAcceptableActions(game), 
				Arrays.asList(game.getCurrentPlayer()), game.getCurrentPlayer().getName() +
				", you have the following available actions. Choose one of them"));
	}

}
