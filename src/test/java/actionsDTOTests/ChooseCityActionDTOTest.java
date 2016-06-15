package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.actionsParametersSetters.ChooseCityBonusParser;
import client.modelDTO.actionsDTO.bonusActions.ChooseCityActionDTO;
import client.modelDTO.gameTableDTO.CityDTO;

public class ChooseCityActionDTOTest {

	@Test
	public void testSetter() {
		GameDTO gameDTO=new GameDTO();
		CityDTO selectedCity= new CityDTO();
		ChooseCityActionDTO action= new ChooseCityActionDTO();
		action.setCity(selectedCity);
		assertFalse(action.checkIfParametersSetted());
		assertEquals(ChooseCityBonusParser.class, action.setParser(null, null).getClass());
		action.parametersSetted();
		assertTrue(action.checkIfParametersSetted());
		assertTrue(action.getSelectedCity()==selectedCity);
	}
}
