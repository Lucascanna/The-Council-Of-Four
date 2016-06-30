package server.model.stateMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import server.model.Game;
import server.model.actions.Action;
import server.model.actions.MoveToNext;
import server.model.actions.marketActions.AcceptAnOffer;
import server.model.player.Player;
import server.view.notifies.AvailableActionsNotify;
import server.view.notifies.MarketNotify;

public class BuyingState implements State {

	@Override
	public State buyActionTransition(Game game) {
		game.notifyObserver(new AvailableActionsNotify(Arrays.asList(), 
				Arrays.asList(game.getCurrentPlayer()), 
				"Ok, your turn is over now. I will notify you when it will be your turn again"));
		if (!game.getMarket().getBuyingPlayerList().isEmpty()){
			game.getMarket().buyingNextPlayer(game);
			return this;
		}
		else {
			game.notifyObserver(new MarketNotify(game, 
					new ArrayList<Player>(game.getPlayers()), false, true));
			game.nextPlayer();
			return new BeginState();
		}
	}

	@Override
	public State moveToNextTransition(Game game) {
		game.notifyObserver(new AvailableActionsNotify(Arrays.asList(), 
				Arrays.asList(game.getCurrentPlayer()), 
				"Ok, I will notify you when it will be your turn again"));
		if (!game.getMarket().getBuyingPlayerList().isEmpty()){
			game.getMarket().buyingNextPlayer(game);
			return this;
		}
		else {
			game.notifyObserver(new MarketNotify(game, 
					new ArrayList<Player>(game.getPlayers()), false, true));
			game.nextPlayer();
			return new BeginState();
		}
	}

	@Override
	public List<Action> getAcceptableActions(Game game) {
		return Arrays.asList(new AcceptAnOffer(), new MoveToNext());
	}

	@Override
	public void updateClients(Game game) {
		game.notifyObserver(new MarketNotify(game, 
				new ArrayList<Player>(game.getPlayers()), false, false));
		game.notifyObserver(new AvailableActionsNotify(game.getState().getAcceptableActions(game), 
				Arrays.asList(game.getCurrentPlayer()), game.getCurrentPlayer().getName() +
				", do you want to buy something from the market?"));
	}

}
