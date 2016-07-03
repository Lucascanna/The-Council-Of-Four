package bonusTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.bonuses.NobilityBonus;
import server.model.player.Player;


public class NobilityBonusTest {

	@Test
	public void testAssignBonus() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(a);
		NobilityBonus bonus= new NobilityBonus(2);
		int temp=game.getCurrentPlayer().getScore();
		bonus.assignBonus(game);
		assertEquals(temp+2, game.getCurrentPlayer().getScore());
		assertEquals(2, game.getCurrentPlayer().getNobility());
		assertEquals("nobility+" + 2, bonus.toString());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeException(){
		new NobilityBonus(-2);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testZeroException(){
		new NobilityBonus(0);
	}

}