
package model;
 
import java.util.ArrayList;
import java.util.List;

import controller.State;
import model.gameTable.GameTable;
import observerPattern.Observable;
import players.Player;
import view.ViewNotify;
 
public class Game extends Observable<ViewNotify>{
	
	private final List<Player> players;
	private Player currentPlayer;
	private final GameTable gameTable;
	private GameState gameState;
	private State state;
	
               
	public Game(List<Player> players, GameTable gameTable){
		this.players=players;
		this.gameTable=gameTable;
		this.currentPlayer=this.players.get(0);
		this.gameState=GameState.RUNNING;
		
	}


	public void nextPlayer(){
		this.currentPlayer=this.players.get(this.players.indexOf(currentPlayer)+1);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public GameTable getGameTable() {
		return gameTable;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}


	public GameState getGameState() {
		return gameState;
	}

	public State getState() {
		return state;
	}


	public void setState(State state) {
		this.state = state;
	}


	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	@Override
	public String toString() {
		return "Game\n\n Players: \n" + players + "\n\n CurrentPlayer: \n" + currentPlayer + "\n\n GameTable:\n" + gameTable + "]";
	}	
	
	public void assignBonusNobilityEndGame(){
		int first=0;
		int second=0;
		for(Player player: this.players){
			if(player.getNobility()>first){
				second=first;
				first=player.getNobility();
			}
			if(player.getNobility()<first && player.getNobility()>second){
					second=player.getNobility();
			}
		}
		for(Player player: this.players){
			if(player.getNobility()==first)
				player.incrementScore(5);
			if(player.getNobility()==second)
				player.incrementScore(2);
		}
	}
	
	public void assignBonusPermitTilesEndGame(){
		int numberOfPermitTiles=0;
		for(Player player : this.players){
			if(player.getPlayersPermitTilesTurnedDown().size()+player.getPlayersPermitTilesTurnedUp().size()>numberOfPermitTiles)
				numberOfPermitTiles=player.getPlayersPermitTilesTurnedDown().size()+player.getPlayersPermitTilesTurnedUp().size();
		}
		for(Player player : this.players)
			if(player.getPlayersPermitTilesTurnedDown().size()+player.getPlayersPermitTilesTurnedUp().size()==numberOfPermitTiles)
				player.incrementScore(3);
	}
	
	public Player selectWinner(){
		int winnerScore=0;
		Player currentWinnerPlayer = null;
		for(Player player : this.players){
			if(player.getScore()>winnerScore)
				winnerScore=player.getScore();
		}
		List<Player> winnerPlayers= new ArrayList<Player>(); //list of players with the winnerScore (per gestire il caso di pareggio)  
		for(Player player : this.players){
			if(player.getScore()==winnerScore)
				winnerPlayers.add(player);
		}
		if(winnerPlayers.size()==1)
			return winnerPlayers.get(0);
		int drawScore=0;
		for(Player player: winnerPlayers){
			if(player.getPlayersPermitTilesTurnedDown().size()+player.getPlayersPermitTilesTurnedUp().size()+player.getAssistants()>drawScore)
				drawScore=player.getPlayersPermitTilesTurnedDown().size()+player.getPlayersPermitTilesTurnedUp().size()+player.getAssistants();
				currentWinnerPlayer=player;
			}
		return currentWinnerPlayer;
	}
}