package client.modelDTO.actionsParametersSetters;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.standardActions.ElectCouncillorDTO;
import client.modelDTO.gameTableDTO.CouncillorDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.view.ClientView;

/**
 * This class provides the logic to set the needed parameters of a ElectCouncillorDTO
 * @author cg31
 *
 */
public class ElectCouncillorParametersSetter implements ActionParametersSetter {

	private ElectCouncillorDTO selectedAction;
	
	/**
	 * Constructor of ElectCouncillorDTO
	 * @param selectedAction is the action selected by the user
	 */
	public ElectCouncillorParametersSetter(ElectCouncillorDTO selectedAction) {
		this.selectedAction=selectedAction;
	}
	
	
	@Override
	public void setParameters(ClientView view, GameDTO game) {
		view.displayMessage("Ok! you have chosen to elect a councillor.");
		
		view.displayMessage("Select the councillor you want to elect");
		this.selectedAction.setNewCouncillor(view.askForCouncillor());
		
		view.displayMessage("Select the balcony in which you wnt to change the councillor");
		List<CouncillorDTO[]> acceptableCouncilBalconies=new ArrayList<>();
		for (RegionDTO region : game.getClientGameTable().getClientRegions())
			acceptableCouncilBalconies.add(region.getBalcony());
		acceptableCouncilBalconies.add(game.getClientGameTable().getClientKingBalcony());
		this.selectedAction.setCouncilBalcony(view.askForCouncilBalcony());
		
		this.selectedAction.parametersSet();
	}

}

