package client.modelDTO.actionsDTO.actionsParametersSetters;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.standardActions.ChangePermitTilesDTO;
import client.view.ClientView;

public class ChangePermitTilesParser implements ActionParserVisitor {

	private ChangePermitTilesDTO selectedAction;
	private ClientView view;
	private GameDTO game;
	
	public ChangePermitTilesParser(ChangePermitTilesDTO selectedAction, ClientView view, GameDTO game) {
		this.selectedAction=selectedAction;
		this.view=view;
		this.game=game;
	}

	
	@Override
	public ActionDTO setParameters() {
		this.view.displayMessage("Ok! you have chosen to change the permit tiles of a region. Now I need some other infos, like:");
		
		this.view.displayMessage("the name of the region in which you want to pick");
		this.selectedAction.setSelectedRegion(this.view.askForRegionBoard
				(this.game.getClientGameTable().getClientRegions()));
		
		this.selectedAction.parametersSetted();
		
		return this.selectedAction;
	}

}
