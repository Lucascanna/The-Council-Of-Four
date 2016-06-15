package client.modelDTO.gameTableDTO;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.ModelDTO;
import server.model.bonus.ScoreBonus;
import server.model.player.Player;

public class GenericPlayerDTO implements ModelDTO<Player>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7091136957135865278L;
	private String name;
	private int playerNumber;
	private int assistants;
	private int score;
	private int nobility;
	private int coins;
	private int emporiums;
	private int hand;
	private List<PermitTileDTO> availablePermitTiles;
	private List<ScoreBonus> playersFinalBonus;
	
	public GenericPlayerDTO(){
		this.availablePermitTiles=new ArrayList<PermitTileDTO>();
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public int getAssistants() {
		return assistants;
	}

	public int getHand() {
		return hand;
	}

	public void setHand(int hand) {
		this.hand = hand;
	}

	public void setAssistants(int assistants) {
		this.assistants = assistants;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getNobility() {
		return nobility;
	}

	public void setNobility(int nobility) {
		this.nobility = nobility;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public int getEmporiums() {
		return emporiums;
	}

	public void setEmporiums(int emporiums) {
		this.emporiums = emporiums;
	}

	public List<PermitTileDTO> getAvailablePermitTiles() {
		return availablePermitTiles;
	}

	public void setAvailablePermitTiles(List<PermitTileDTO> availablePermitTiles) {
		this.availablePermitTiles = availablePermitTiles;
	}

	public List<ScoreBonus> getPlayersFinalBonus() {
		return playersFinalBonus;
	}


	public void setPlayersFinalBonus(List<ScoreBonus> playersFinalBonus) {
		this.playersFinalBonus = playersFinalBonus;
	}


	@Override
	public String toString() {
		return "\nPlayer " + playerNumber + "\tname: "+ name + "\tassistants=" + assistants + "\tscore="
				+ score + "\tnobility=" + nobility + "\tcoins=" + coins + "\thand="+hand+"\tplayersPermitTilesTurnedUp=" + availablePermitTiles +
				 "\temporiums=" + this.emporiums + "\tfinalBonus= " + playersFinalBonus+"]";
	}



	
}
