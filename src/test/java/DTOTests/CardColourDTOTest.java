package DTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import modelDTO.gameTableDTO.CardColourDTO;

public class CardColourDTOTest {

	@Test
	public void test() {
		CardColourDTO colour= new CardColourDTO();
		String name= "Blu";
		colour.setName(name);
		assertTrue(colour.getName()==name);
	}

}
