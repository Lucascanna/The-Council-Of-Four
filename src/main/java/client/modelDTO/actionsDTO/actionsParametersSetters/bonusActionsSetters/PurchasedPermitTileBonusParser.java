package client.modelDTO.actionsDTO.actionsParametersSetters.bonusActionsSetters;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.actionsParametersSetters.ActionParserVisitor;
import client.modelDTO.actionsDTO.bonusActions.PurchasedPermitTileActionDTO;
import client.view.ClientView;

/**
 * This class provides the logic to set the needed parameters of a PurchasedPermitTileActionDTO
 * @author cg31
 *
 */
public class PurchasedPermitTileBonusParser implements ActionParserVisitor {

	private PurchasedPermitTileActionDTO selectedAction;

	/**
	 * Constructor of PurchasedPermitTileActionDTO
	 * @param selectedAction is the action selected by the user
	 */
	public PurchasedPermitTileBonusParser(PurchasedPermitTileActionDTO selectedAction) {
		this.selectedAction=selectedAction;
	}

	@Override
	public void setParameters(ClientView view, GameDTO game) {
		view.displayMessage("Permit tile bonus earned! You have the possibility to choose from your permit tiles,"
						+ "covered or not, and get the bonuses associated to that");
			this.selectedAction.setPermitTile(view.askForPermitTileUncoveredAndCovered());
		this.selectedAction.parametersSet();
	}

}
