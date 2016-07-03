package modelDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.gameTableDTO.CardColourDTO;
import client.modelDTO.gameTableDTO.PoliticsCardDTO;

public class PoliticsCardDTOTest {

	@Test
	public void test() {
		PoliticsCardDTO card= new PoliticsCardDTO();
		CardColourDTO colour= new CardColourDTO();
		PoliticsCardDTO card1= new PoliticsCardDTO(colour);
		String a="blue";
		colour.setName(a);
		card.setColour(colour);
		assertTrue(card.getColour()==colour);
		assertEquals(3027096, card.hashCode());
		assertTrue(card.equals(card1));
		assertTrue("blue"==card.toString());
	}

}
