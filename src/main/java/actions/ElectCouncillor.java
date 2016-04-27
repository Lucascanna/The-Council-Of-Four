 package actions;

import gameStuff.CouncilBalcony;
import gameStuff.Councillor;
import model.Game;

/**
 * It's the main action "elect councillor" it operates on the 
 * protected attribute game through the method executeAction.
 * The controller will pass to the constructor the councillor to add,
 * and the balcony where he wants to add it. 
 * @author Luca
 *
 */
public class ElectCouncillor extends MainAction {

	private final Councillor newCouncillor;
	private final CouncilBalcony councilBalcony;
	
	public ElectCouncillor(Game game, Councillor newCouncillor, CouncilBalcony councilBalcony){
		super(game);
		this.newCouncillor=newCouncillor;
		this.councilBalcony=councilBalcony;
	}
/**
 * Substitutes a given councillor in one of the balconies of the game,
 * adds the old substituted councillor in the reserve,
 * gives to the current player 4 coins.
 * @return TRUE if the action ends well; FALSE otherwise.
 */
	public boolean executeAction() {
		Councillor oldCouncillor;
		if(!this.game.getGameTable().getCouncilReserve()
				.getCouncillors().contains(newCouncillor))
			return false;
		oldCouncillor=this.councilBalcony.substituteCouncillor(newCouncillor);
		this.game.getGameTable().getCouncilReserve().getCouncillors().add(oldCouncillor);
		this.game.getCurrentPlayer().incrementCoins(4);
		return true;
	}

}