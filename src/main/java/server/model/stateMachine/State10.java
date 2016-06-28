package server.model.stateMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import server.model.Game;
import server.model.actions.Action;
import server.model.actions.standardActions.AcquirePermitTile;
import server.model.actions.standardActions.BuildByKing;
import server.model.actions.standardActions.BuildByPermitTile;
import server.model.actions.standardActions.ElectCouncillor;
import server.model.player.Player;
import server.model.stateMachine.bonusStates.AdditionalMainActionBonusState;
import server.model.stateMachine.bonusStates.InteractiveBonusState;
import server.view.notifies.AvailableActionsNotify;
import server.view.notifies.GameTableNotify;
import server.view.notifies.PlayerNotify;

/**
 * Models the state in which the current player has only the possibility to do a main action 
 * @author andreapasquali
 *
 */
public class State10 implements State{
	
	/**
	 * coordinates the transition caused by a main action
	 * if the current player is not the last one, it returns a new Begin State changing the current player
	 * else it returns a new Selling State
	 */
	@Override
	public State mainActionTransition(Game game) {
		
		if (!game.getCurrentPlayer().equals(game.lastPlayer())){
			game.nextPlayer();
			return new BeginState();
		}
		else {
			game.startMarket();
			return new SellingState();
		}
	}
	
	/**
	 *if current player is the last one, it returns a new BeginState,
	 *else it returns a new SellingState
	 */
	@Override
	public State moveToNextTransition(Game game){
		if (!game.getCurrentPlayer().equals(game.lastPlayer())){
			game.nextPlayer();
			return new BeginState();
		}
		else {
			game.startMarket();
			return new SellingState();
		}
	}
	
	/**
	 *returns a new State10 after an additional main action bonus.
	 */
	@Override
	public State additionalMainActionTransition() {
		return new AdditionalMainActionBonusState(this);
	}
	
	/**
	 * returns a new InteractiveBonusState 
	 */
	@Override
	public State interactiveBonusTransition() {
		return new InteractiveBonusState(this);
	}
	
	/**
	 * returns a list of acceptable actions of this state
	 */
	@Override
	public List<Action> getAcceptableActions(Game game) {
		return Arrays.asList(
				new ElectCouncillor(),
				new AcquirePermitTile(),
				new BuildByPermitTile(),
				new BuildByKing());
	}

	/**
	 * sends GameTableNotify, PlayerNotify, AvailableActionNotify to the clients
	 */
	@Override
	public void updateClients(Game game) {
		game.notifyObserver(new GameTableNotify(game, new ArrayList<Player>(game.getPlayers()),false));
		game.notifyObserver(new PlayerNotify(game, game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new AvailableActionsNotify(game.getState().getAcceptableActions(game), 
				Arrays.asList(game.getCurrentPlayer()), game.getCurrentPlayer().getName() +
				", you have the following available actions. Choose one of them"));
	}

}
