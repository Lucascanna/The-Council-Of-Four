package server.model.actions;

import java.util.Arrays;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.PickPoliticsCardDTO;
import server.model.Game;
import server.view.notifies.AvailableActionsNotify;
import server.view.notifies.PlayerNotify;

public class PickPoliticsCard implements Action {

	@Override
	public boolean executeAction(Game game) {
		game.getCurrentPlayer().getHand().add(game.getGameTable().getPoliticsDeck().pickCard());
		
		game.setState(game.getState().pickPoliticsCardTransition());
		
		game.notifyObserver(new PlayerNotify(game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new AvailableActionsNotify(game.getState().getAcceptableActions(game), 
				Arrays.asList(game.getCurrentPlayer())));
		System.out.println("game has notified the server view");
		return true;
	}

	
	@Override
	public String toString() {
		return "pc: pick a politics card";
	}


	@Override
	public ActionDTO map() {
		return new PickPoliticsCardDTO();
	}

}
