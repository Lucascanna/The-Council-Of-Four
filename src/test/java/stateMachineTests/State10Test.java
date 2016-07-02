package stateMachineTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.actions.standardActions.AcquirePermitTile;
import server.model.actions.standardActions.BuildByKing;
import server.model.actions.standardActions.BuildByPermitTile;
import server.model.actions.standardActions.ElectCouncillor;
import server.model.player.Player;
import server.model.stateMachine.BeginState;
import server.model.stateMachine.SellingState;
import server.model.stateMachine.State10;
import server.model.stateMachine.bonusStates.AdditionalMainActionBonusState;
import server.model.stateMachine.bonusStates.InteractiveBonusState;

public class State10Test {

	@Test
	public void testMainActionTransition() throws IOException {
			Game game=new Game();
			List<Player> players = new ArrayList<>();
			Player a = new Player("Andre");
			Player b = new Player("Andre");
			b.setPlayerNumber(2);
			players.add(a);
			players.add(b);
			game.start(players);
			State10 state= new State10();
			game.setCurrentPlayer(a);
			game.setState(state);
			assertEquals(BeginState.class, state.mainActionTransition(game).getClass());
			assertEquals(b, game.getCurrentPlayer());
			game.setState(state);
			assertEquals(SellingState.class, state.mainActionTransition(game).getClass());
			assertEquals(a, game.getCurrentPlayer());
	}
	
	@Test
	public void testMoveToNextTransition() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		Player b = new Player("Andre");
		b.setPlayerNumber(2);
		players.add(a);
		players.add(b);
		game.start(players);
		State10 state= new State10();
		game.setCurrentPlayer(a);
		game.setState(state);
		assertEquals(BeginState.class, state.moveToNextTransition(game).getClass());
		assertEquals(b, game.getCurrentPlayer());
		game.setState(state);
		assertEquals(SellingState.class, state.moveToNextTransition(game).getClass());
		assertEquals(a, game.getCurrentPlayer());
	}
	
	@Test
	public void testAdditionalMainActionAndInteractiveBonusTransition(){
		State10 state= new State10();
		assertTrue(AdditionalMainActionBonusState.class==state.additionalMainActionTransition().getClass());
		assertTrue(InteractiveBonusState.class==state.interactiveBonusTransition().getClass());
	}
	
	@Test
	public void testGetAcceptableActions(){
		Game game= new Game();
		State10 state= new State10();
		assertEquals(ElectCouncillor.class, state.getAcceptableActions(game).get(0).getClass());
		assertEquals(AcquirePermitTile.class, state.getAcceptableActions(game).get(1).getClass());
		assertEquals(BuildByPermitTile.class, state.getAcceptableActions(game).get(2).getClass());
		assertEquals(BuildByKing.class, state.getAcceptableActions(game).get(3).getClass());
	}
	
}
