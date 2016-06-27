package server.view.notifies;

import java.util.List;

import client.modelDTO.clientNotifies.ClientNotify;
import client.modelDTO.clientNotifies.GameTableDTONotify;
import server.model.Game;
import server.model.player.Player;

public class GameTableNotify implements ViewNotify {
	
	private final Game game;
	private final List<Player> interestedPlayers;
	private final boolean startGame;
	
	public GameTableNotify(Game game, List<Player> interestedPlayers, boolean startGame) {
		this.game=game;
		this.interestedPlayers=interestedPlayers;
		this.startGame=startGame;
	}
	
	@Override
	public ClientNotify toClientNotify() {
		return new GameTableDTONotify(this.game.getGameMapper().gameTableMap(this.game),startGame);
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}
	
	public Player getCurrentPlayer(){
		return game.getCurrentPlayer();
	}

}
