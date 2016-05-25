package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import model.Game;
import model.bonus.Bonus;
import model.bonus.ScoreBonus;
import model.gameTable.NobilityTrack;

public class NobilityTrackTest {

	@Test
	public void testGetNobilityTrack() throws IOException{
		Game game=new Game();
		NobilityTrack nobilityTrack=new NobilityTrack(20);
		assertEquals(nobilityTrack.getClass(), game.getGameTable().getNobilityTrack().getClass());
	}
	
	@Test
	public void test() throws IOException{
		NobilityTrack nobilityTrack=new NobilityTrack(1);
		List<Set<Bonus>> track= new ArrayList<Set<Bonus>>();
		Set<Bonus> casella=new HashSet<Bonus>();
		Bonus bonus=new ScoreBonus(2);
		casella.add(bonus);
		track.add(casella);
		nobilityTrack.addBonus(0, bonus);
		assertEquals(casella, nobilityTrack.getTrack().get(0));
	}

}
