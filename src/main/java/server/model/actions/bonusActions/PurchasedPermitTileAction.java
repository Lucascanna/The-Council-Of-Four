package server.model.actions.bonusActions;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.bonusActions.PurchasedPermitTileActionDTO;
import server.model.Game;
import server.model.actions.Action;
import server.model.bonus.Bonus;
import server.model.gameTable.PermitTile;

/**
 * It's the class that models the action associated to the choice of the PurchasedPermitTile.
 * @author Emanuele
 *
 */

public class PurchasedPermitTileAction implements Action {

	private PermitTile selectedPermitTile;

	public void setSelectedPermitTile(PermitTile selectedPermitTile) {
		this.selectedPermitTile=selectedPermitTile;
	}
	
	
	@Override
	public boolean executeAction(Game game) {
		if (this.selectedPermitTile==null) {
			game.setState(game.getState().moveToNextTransition(game));
			return false;
		}
			
		for (Bonus bonusToAssign : this.selectedPermitTile.getBonuses())
			bonusToAssign.assignBonus(game);
		
		game.setState(game.getState().moveToNextTransition(game));
		game.getState().updateClients(game);
		
		return true;
	}


	@Override
	public ActionDTO map() {
		return new PurchasedPermitTileActionDTO();
	}
	
}
