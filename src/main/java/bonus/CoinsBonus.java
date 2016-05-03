package bonus;

import model.Game;

/**
 * CoinsBonus class
 * @author Andrea, Emanuele
 *
 */

public class CoinsBonus implements Bonus{

	private final int coinsIncreasement;

	/**
	 * Is the constructor of CoinsBonus
	 * @param coinsIncreasement is the increasement of coins
	 */
	public CoinsBonus(int coinsIncreasement){
		this.coinsIncreasement=coinsIncreasement;
	}
	
	/**
	 * Assigns to the current player a coinsIncreasement number of coins
	 * @param currentPlayer is the player who is playing the turn
	 */
	public void assignBonus(Game game) {
		game.getCurrentPlayer().incrementCoins(coinsIncreasement);
	}

}