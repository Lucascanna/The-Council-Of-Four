package bonus;


import gameStuff.RegionBoard;
import model.Game;

/**
 * Pick up an uncovered permit tile without paying the cost
 * @author Emanuele
 *
 */

public class PickPermitTileBonus implements Bonus {
	
	private final RegionBoard chosenRegion;
	private final int numberOfPermitTile;

	/**
	 * constructor of PickPermitTileBonus
	 * @param chosenRegion is the region you have chosen
	 * @param numberOfPermitTile is the number of the permit tile you have chosen
	 */
	public PickPermitTileBonus(RegionBoard chosenRegion, int numberOfPermitTile) {
		this.chosenRegion=chosenRegion;
		this.numberOfPermitTile=numberOfPermitTile;
	}

	/**
	 * Picks the selected permit tile, and adds each bonus
	 * @param currentPlayer is the player who is playing the turn
	 */
	public void assignBonus(Game game) {
		game.getCurrentPlayer().addTile(this.chosenRegion.pickUncoveredPermitTile(this.numberOfPermitTile));
		this.chosenRegion.uncoverPermitTiles();
		for (Bonus bonusToAssign : this.chosenRegion.getUncoveredPermitTiles()[numberOfPermitTile].getBonus())
			bonusToAssign.assignBonus(game);
	}

}