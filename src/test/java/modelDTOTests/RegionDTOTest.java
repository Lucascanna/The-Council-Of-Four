package modelDTOTests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import client.modelDTO.gameTableDTO.CardColourDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import server.model.bonus.ScoreBonus;

public class RegionDTOTest {

	@Test
	public void testGettersAndSetters() {
		String name="Sea";
		CardColourDTO[] balcony=new CardColourDTO[4];
		Set<CityDTO> cities= new HashSet<>();
		PermitTileDTO[] uncoveredPermitTiles= new PermitTileDTO[2];
		RegionDTO region= new RegionDTO();
		region.setBalcony(balcony);
		region.setCities(cities);
		region.setName(name);
		ScoreBonus regionBonus= new ScoreBonus(5);
		region.setRegionBonus(regionBonus);
		region.setUncoveredPermitTiles(uncoveredPermitTiles);
		assertTrue(region.getBalcony()==balcony);
		assertTrue(region.getRegionBonus()==regionBonus);
		assertTrue(region.getCities()==cities);
		assertTrue(region.getName()==name);
		assertTrue(region.getUncoveredPermitTiles()==uncoveredPermitTiles);
		assertEquals("\n"+name + "\t" + Arrays.toString(balcony) + "\tTiles:" + Arrays.toString(uncoveredPermitTiles)+
				"\tBonus: "+regionBonus+"\n" + cities, region.toString());
	}

	public void testEquals() {
		String name1="Sea";
		CardColourDTO[] balcony1=new CardColourDTO[4];
		Set<CityDTO> cities1= new HashSet<>();
		PermitTileDTO[] uncoveredPermitTiles1= new PermitTileDTO[2];
		String name2="Sea";
		CardColourDTO[] balcony2=new CardColourDTO[4];
		Set<CityDTO> cities2= new HashSet<>();
		PermitTileDTO[] uncoveredPermitTiles2= new PermitTileDTO[2];
		RegionDTO region1= new RegionDTO();
		RegionDTO region2= new RegionDTO();
		region1.setBalcony(balcony1);
		region1.setCities(cities1);
		region1.setName(name1);
		region1.setUncoveredPermitTiles(uncoveredPermitTiles1);
		region2.setBalcony(balcony2);
		region2.setCities(cities2);
		region2.setName(name2);
		region2.setUncoveredPermitTiles(uncoveredPermitTiles2);
		assertEquals(region1, region2);
	}
}
