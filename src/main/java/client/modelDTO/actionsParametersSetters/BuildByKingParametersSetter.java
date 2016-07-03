package client.modelDTO.actionsParametersSetters;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.standardActions.BuildByKingDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.view.ClientView;

/**
 * This class provides the logic to set the needed parameters of a BuildByKingDTO
 * @author cg31
 *
 */
public class BuildByKingParametersSetter implements ActionParametersSetter {

	private BuildByKingDTO selectedAction;
	
	/**
	 * Constructor of BuildByKingDTO
	 * @param selectedAction is the action selected by the user
	 */
	public BuildByKingParametersSetter(BuildByKingDTO selectedAction) {
		this.selectedAction=selectedAction;
	}

	
	@Override
	public void setParameters(ClientView view, GameDTO game) {
		view.displayMessage("Ok! you have chosen to build an emporium with the help of the king.");
		
		if (!game.getClientPlayer().getHand().isEmpty()) {
			
			view.displayMessage("Select the city in which you want to build an emporium");
			List<CityDTO> acceptableCities=new ArrayList<>();
			for (RegionDTO region : game.getClientGameTable().getClientRegions())
				acceptableCities.addAll(region.getCities());
			this.selectedAction.setSelectedCity(view.askForCity(acceptableCities));
			
			view.displayMessage("Select the politics cards you want to descard");
			this.selectedAction.setCardsToDescard(view.askForPoliticsCards());
			
			this.selectedAction.parametersSet();
		
		}
		else 
			view.displayMessage("But it seems that you haven't any politics card in your hand! Select another action please");
	}

}
