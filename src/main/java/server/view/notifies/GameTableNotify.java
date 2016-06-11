package server.view.notifies;

import java.util.List;

import modelDTO.clientNotifies.ClientNotify;
import modelDTO.clientNotifies.GameTableDTONotify;
import players.Player;
import server.model.Game;

public class GameTableNotify implements ViewNotify {
	
	private final Game game;
	private final List<Player> interestedPlayers;
	
	public GameTableNotify(Game game, List<Player> interestedPlayers) {
		this.game=game;
		this.interestedPlayers=interestedPlayers;
	}
	
	@Override
	public ClientNotify toClientNotify() {
		return new GameTableDTONotify(this.game.getGameMapper().gameTableMap(this.game));
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}
	
	public Player getCurrentPlayer(){
		return game.getCurrentPlayer();
	}

}
