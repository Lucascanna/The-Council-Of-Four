package modelDTO.actionsDTO;

import modelDTO.GameDTO;
import modelDTO.parser.ActionParserVisitor;
import server.model.Game;
import server.model.actions.Action;
import server.model.actions.AddPlayer;

public class AddPlayerDTO implements ActionDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3589948126875889377L;
	private String playerName;
	
	
	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action map(Game game) {
		AddPlayer action = new AddPlayer();
		action.setPlayerName(this.playerName);
		return action;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public String toString() {
		return "player added";
	}

}
