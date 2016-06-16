package actionsTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.actions.bonusActions.ChooseCityBonusAction;
import server.model.gameTable.City;
import server.model.player.Player;
import server.model.stateMachine.State01;
import server.model.stateMachine.State11;
import server.model.stateMachine.bonusStates.InteractiveBonusState;

public class ChooseCityBonusActionTest {

	@Test
	public void testExecuteAction() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		ChooseCityBonusAction action= new ChooseCityBonusAction();
		City city= null;
		for(City c: game.getGameTable().getRegionBoards().get(1).getRegionCities())
			if(c.getName().equals("Juvelar"))
				city=c;
		action.setSelectedCity(city);
		game.setState(new InteractiveBonusState(new State11()));
		assertEquals("Juvelar", city.getName());
		assertTrue(action.executeAction(game));
		assertEquals(State01.class, game.getState().getClass());
	}

	@Test(expected=NullPointerException.class)
	public void testNullException() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		ChooseCityBonusAction action= new ChooseCityBonusAction();
		action.executeAction(game);
		assertTrue(false);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testIllegalException() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		ChooseCityBonusAction action= new ChooseCityBonusAction();
		action.map();
		assertTrue(false);
	}
}
