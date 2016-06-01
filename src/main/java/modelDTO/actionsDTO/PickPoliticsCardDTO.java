package modelDTO.actionsDTO;

import server.model.Game;
import server.model.actions.Action;
import server.model.actions.PickPoliticsCard;

public class PickPoliticsCardDTO implements ActionDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7937576386571037161L;

	@Override
	public Action map(Game game) {
		return new PickPoliticsCard();
	}
	
	@Override
	public String toString() {
		return "pc: pick a politics card";
	}

}
