package controller;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.actions.Action;
import model.actions.PickPoliticsCard;

public class BeginState implements State {

	@Override
	public State mainActionTransition() throws RuntimeException{
		throw new RuntimeException("There are not such transictions for this state");
	}

	@Override
	public State quickActionTransition() throws RuntimeException{
		throw new RuntimeException("There are not such transictions for this state");
	}

	@Override
	public State additionalMainActionTransition() throws RuntimeException{
		throw new RuntimeException("There are not such transictions for this state");
	}

	@Override
	public State moveToNextTransition() throws RuntimeException{
		throw new RuntimeException("There are not such transictions for this state");
	}
	
	@Override
	public State pickPoliticsCardTransition() {
		return new State11();
	}	
	

	@Override
	public List<Action> getAcceptableActions(Game game) {
		List<Action> acceptableAction= new ArrayList<Action>();
		acceptableAction.add(new PickPoliticsCard(game));
		
		return acceptableAction;
	}

	
	public String toString(Game game) {
		String availableActions = "Player "+game.getCurrentPlayer().getName()+
				" is your turn! Pick a politics card pressing pc.\n";
		return availableActions;
	}

}
