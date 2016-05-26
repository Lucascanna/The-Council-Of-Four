package server.model.bonus;

import server.model.Game;

/**
 * This bonus allows the current player to choose from a permit tile he has already picked
 * (covered or not) and then assigns him the bonus/bonuses associated to the permit tile
 * This is a particular bonus which requires a choice by the current player.
 * In order to do this the method assignBonus invokes a particular action which
 * manages the assignment of the bonus
 * @author Emanuele
 *
 */

public class PurchasedPermitTileBonus implements Bonus {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5619798617816315708L;

	/**
	 * Invokes the action associated o this bonus
	 * @param game is the current game
	 */
	public void assignBonus(Game game) {
		//TODO
	}

}