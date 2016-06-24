package modelDTOTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import client.modelDTO.gameTableDTO.CityColourDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.GenericPlayerDTO;
import client.modelDTO.gameTableDTO.RewardTokenDTO;
import server.model.bonus.Bonus;

public class CityDTOTest {

	@Test
	public void testGettersAndSetters() {
		CityDTO city= new CityDTO();
		String name="città";
		city.setName(name);
		CityColourDTO colour= new CityColourDTO();
		city.setColour(colour);
		Set<GenericPlayerDTO> emporiums= new HashSet<>();
		city.setBuildedEmporiums(emporiums);
		Set<Bonus> rewardToken= new HashSet<>();
		city.setRewardToken(new RewardTokenDTO(rewardToken));
		assertTrue(city.getBuildedEmporiums()==emporiums);
		assertTrue(city.getColour()==colour);
		assertTrue(city.getName()==name);
		assertTrue(city.getRewardToken().getBonuses()==rewardToken);
		List<String> emporiumsPlayers=new ArrayList<>();
		for(GenericPlayerDTO player : emporiums)
			emporiumsPlayers.add(player.getName());
		assertEquals(name+"\t"+emporiums+"\t"+rewardToken+"\n", city.toString());
	}
}
