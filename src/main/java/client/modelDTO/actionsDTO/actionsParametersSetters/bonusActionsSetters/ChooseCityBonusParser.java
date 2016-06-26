package client.modelDTO.actionsDTO.actionsParametersSetters.bonusActionsSetters;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.actionsParametersSetters.ActionParserVisitor;
import client.modelDTO.actionsDTO.bonusActions.ChooseCityActionDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.GenericPlayerDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.view.ClientView;

public class ChooseCityBonusParser implements ActionParserVisitor {

	private ChooseCityActionDTO selectedAction;


	public ChooseCityBonusParser(ChooseCityActionDTO selectedAction) {
		this.selectedAction=selectedAction;
	}


	@Override
	public ActionDTO setParameters(ClientView view, GameDTO game) {
		view.displayMessage("City bonus earned! You have the possibility to choose from the cities in which you have built"
						+ "an emporium, and get the bonuses associated to that");
		
		List<CityDTO> acceptableCities=new ArrayList<>();
		for (RegionDTO region : game.getClientGameTable().getClientRegions())
			for (CityDTO city : region.getCities())
				for (GenericPlayerDTO emporium : city.getBuildedEmporiums())
					if (emporium.equals(game.getClientPlayer().getName()))
						acceptableCities.add(city);
		
		for (int i=1; i<=this.selectedAction.getNumberOfCities(); i++) {
		
			if (!acceptableCities.isEmpty()) {
		
				CityDTO selectedCity=view.askForCity(acceptableCities);
				this.selectedAction.getSelectedCities().add(selectedCity);
				acceptableCities.remove(selectedCity);
			}
			else 
				view.displayMessage("But it seems you haven't built an emporium yet");
		}
		
		return selectedAction;
	}

}
