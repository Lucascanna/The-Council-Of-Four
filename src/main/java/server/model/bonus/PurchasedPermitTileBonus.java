package server.model.bonus;

import java.util.Arrays;

import server.model.Game;
import server.view.notifies.PermitTileBonusNotify;

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
	@Override
	public void assignBonus(Game game) {
		game.notifyObserver(new PermitTileBonusNotify(Arrays.asList(game.getCurrentPlayer())));
	}

}