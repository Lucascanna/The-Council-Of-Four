package bonusTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.bonus.ScoreBonus;
import server.model.player.Player;

public class ScoreBonusTest {

	@Test
	public void testAssignBonus() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(game.getPlayers().get(0));
		ScoreBonus bonus=new ScoreBonus(1);
		ScoreBonus bonus1=new ScoreBonus(1);
		bonus.assignBonus(game);
		assertEquals(1, game.getCurrentPlayer().getScore());
		assertEquals("score+" + 1, bonus.toString());
		assertEquals(32, bonus.hashCode());
		assertEquals(bonus, bonus1);
	}
	
	@Test
	public void testAssignBonusToPlayer() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		ScoreBonus bonus=new ScoreBonus(1);
		bonus.assignBonusToPlayer(game.getPlayers().get(0));
		assertEquals(1, game.getPlayers().get(0).getScore());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNegativeException(){
		new ScoreBonus(-2);
	}
}