package client.modelDTO.actionsDTO.actionsParametersSetters.bonusActionsSetters;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.actionsParametersSetters.ActionParserVisitor;
import client.modelDTO.actionsDTO.bonusActions.ChooseCityActionDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.GenericPlayerDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.view.ClientView;

/**
 * This class provides the logic to set the needed parameters of a ChooseCityActionDTO
 * @author cg31
 *
 */
public class ChooseCityBonusParser implements ActionParserVisitor {

	private ChooseCityActionDTO selectedAction;

	/**
	 * Constructor of ChooseCityActionDTO
	 * @param selectedAction is the action selected by the user
	 */
	public ChooseCityBonusParser(ChooseCityActionDTO selectedAction) {
		this.selectedAction=selectedAction;
	}


	@Override
	public void setParameters(ClientView view, GameDTO game) {
		view.displayMessage("City bonus earned! You have the possibility to choose from the cities in which you have built"
						+ "an emporium, and get the bonuses associated to that");
		
		List<CityDTO> acceptableCities=new ArrayList<>();
		for (RegionDTO region : game.getClientGameTable().getClientRegions())
			for (CityDTO city : region.getCities())
				for (GenericPlayerDTO emporium : city.getBuildedEmporiums())
					if (emporium.getName().equals(game.getClientPlayer().getName()))
						acceptableCities.add(city);
		
		for (int i=1; i<=this.selectedAction.getNumberOfCities(); i++) {
			CityDTO selectedCity=view.askForCity(acceptableCities);
			this.selectedAction.getSelectedCities().add(selectedCity);
			acceptableCities.remove(selectedCity);
		}
		
		this.selectedAction.parametersSetted();
	}

}
