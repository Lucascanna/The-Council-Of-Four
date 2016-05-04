package players;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gameStuff.Emporium;
import gameStuff.PermitTile;
import gameStuff.PoliticsCard;

/** Player class contains all of the informations about a player
	 * 
	 * @author Emanuele
	 *
	 */

public class Player {

	private final int playerNumber;
	private final String name;
	private int assistants;
	private final Score score;
	private int nobility;
	private int coins;
	private final List<PoliticsCard> hand;
	private final Set<PermitTile> playersPermitTilesTurnedUp;
	private final Set<PermitTile> playersPermitTilesTurnedDown;
	private final List<Emporium> emporiums;
	private static final int intialNumberOfEmporiums=10;
	
	public Player(int playerNumber, String name, int assistants, int coins) {
		this.playerNumber=playerNumber;
		this.name=name;
		this.assistants=assistants;
		this.score=new Score(0);
		this.nobility=0;
		this.coins=coins;
		this.hand=new ArrayList<PoliticsCard>();
		this.playersPermitTilesTurnedDown=new HashSet<PermitTile>();
		this.playersPermitTilesTurnedUp=new HashSet<PermitTile>();
		this.emporiums=new ArrayList<Emporium>();
		for(int i=0;i<intialNumberOfEmporiums;i++)
			this.emporiums.add(new Emporium(this));
	}

	public int getPlayerNumber() {
		return playerNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAssistants() {
		return assistants;
	}
	
	public Score getScore() {
		return score;
	}
	
	public int getNobility() {
		return nobility;
	}
	
	public List<PoliticsCard> getHand() {
		return hand;
	}


	public int getCoins() {
		return coins;
	}
	

	public List<Emporium> getRemainigEmporiums() {
		return emporiums;
	}


	/**
	 * increment player's Score of an "increment" calling the method incrementScore in the class Score
	 * @param increment
	 * @param score
	 */
	public void incrementScore(int increment, Score score) {
		score.incrementScore(increment);
	}
	
	/**
	 * increments player's assistants number of an "increment"
	 * @param increment
	 */
	public void incrementAssistants(int increment) {
		this.assistants+=increment;
	}
	/**
	 * decrements number of player's assistances if it has more assistances than the decrement.
	 * it returns false if player has too few assistants!
	 * @param decrement
	 */
	public boolean decrementAssistants(int decrement) {
		if(assistants>=decrement){
			this.assistants-=decrement;
			return true;}
		else
			return false;
	}
	
	/**
	 * increments player's nobility position of an "increment"
	 * @param increment
	 */
	public void incrementNobility(int increment) {
		this.nobility+=increment;
	}

	
	/**
	 * increments player's coins of a "increment"
	 * @param increment
	 */
	public void incrementCoins(int increment) {
		this.coins+=increment;
	}
	/**
	 * decrements player's coins if players has more coins than decrement.
	 * it returns false if player has too few coins!
	 * @param decrement
	 */
	public boolean decrementCoins(int decrement) {
		if(coins>=decrement){
			this.coins-=decrement;
			return true;
			}
		else
			return false;
	}
	
	/**
	 * adds one "card" to the player's deck
	 * @param card
	 */
	public void addCardToHand(PoliticsCard card){
		this.hand.add(card);
	}
	
	/**
	 * removes one card "card" from the player's deck
	 * returns TRUE if player's deck contained the card; 
	 * returns FALSE if player's deck didn't contain the card.
	 * @param card
	 */
	public boolean removeCardFromHand(PoliticsCard card){
		return this.hand.remove(card);
	}
	
	public void addTile(PermitTile tile){
		this.playersPermitTilesTurnedUp.add(tile);
	}
	
	/**
	 * turn a tile from up to down.
	 * into his "playersPermitTilesTurnedUp
	 * @param tile to cover
	 * @return TRUE if tile is an uncovered permit tile of the player. FALSE otherwise.
	 */
	public boolean turnTileToDown(PermitTile tile){
		if(!this.playersPermitTilesTurnedUp.contains(tile))
			return false;
		playersPermitTilesTurnedDown.add(tile);
		return this.playersPermitTilesTurnedUp.remove(tile);
	}
	
	/**
	 * remove an emporium
	 * @return the removed emporium
	 */
	public Emporium removeEmporium(){		
		return this.emporiums.remove(0);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}