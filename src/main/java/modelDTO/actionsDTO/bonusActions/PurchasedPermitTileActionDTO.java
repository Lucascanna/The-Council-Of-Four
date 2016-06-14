package modelDTO.actionsDTO.bonusActions;

import client.view.ClientView;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ActionWithParameters;
import modelDTO.gameTableDTO.PermitTileDTO;
import modelDTO.parser.ActionParserVisitor;
import modelDTO.parser.PurchasedPermitTileBonusParser;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

public class PurchasedPermitTileActionDTO implements ActionDTO, ActionWithParameters {

	/**
	 * 
	 */
	private static final long serialVersionUID = 893810260862447362L;
	private PermitTileDTO selectedPermitTile;
	private boolean parametersSetted=false;
	
	public void setPermitTile(PermitTileDTO selectedPermitTile) {
		this.selectedPermitTile=selectedPermitTile;
	}

	public PermitTileDTO getSelectedPermitTile() {
		return selectedPermitTile;
	}

	@Override
	public boolean checkIfParametersSetted() {
		return this.parametersSetted;
	}

	@Override
	public void parametersSetted() {
		this.parametersSetted=true;
	}

	@Override
	public ActionParserVisitor setParser(ClientView view, GameDTO game) {
		return new PurchasedPermitTileBonusParser(this, view, game);
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return mapper.map(this);
	}
}
