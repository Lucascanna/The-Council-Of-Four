package server.model.bonus;


import server.model.Game;
import server.model.gameTable.PermitTile;
import server.model.gameTable.RegionBoard;

/**
 * Pick up an uncovered permit tile without paying the cost
 * @author Emanuele
 *
 */

public class PickPermitTileBonus implements Bonus {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5266466654375532116L;
	private final RegionBoard chosenRegion;
	private final int numberOfPermitTile;

	/**
	 * constructor of PickPermitTileBonus
	 * @param chosenRegion is the region you have chosen
	 * @param numberOfPermitTile is the number of the permit tile you have chosen
	 * @throws IllegalArgumentException if number of permit tile isn't 0 or 1
	 */
	public PickPermitTileBonus(RegionBoard chosenRegion, int numberOfPermitTile) throws IllegalArgumentException {
		if(numberOfPermitTile>1 || numberOfPermitTile<0)
			throw new IllegalArgumentException("the permit tile doesn't exist");
		this.chosenRegion=chosenRegion;
		this.numberOfPermitTile=numberOfPermitTile;
	}

	/**
	 * Picks the selected permit tile, and adds each bonus
	 * @param game is the current game
	 */
	@Override
	public void assignBonus(Game game) {
		PermitTile tile= this.chosenRegion.pickUncoveredPermitTile(this.numberOfPermitTile);
		game.getCurrentPlayer().addTile(tile);
		for (Bonus bonusToAssign : tile.getBonuses())
			bonusToAssign.assignBonus(game);
		this.chosenRegion.uncoverPermitTiles();
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chosenRegion == null) ? 0 : chosenRegion.hashCode());
		result = prime * result + numberOfPermitTile;
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
		PickPermitTileBonus other = (PickPermitTileBonus) obj;
		if (chosenRegion == null) {
			if (other.chosenRegion != null)
				return false;
		} else if (!chosenRegion.equals(other.chosenRegion))
			return false;
		if (numberOfPermitTile != other.numberOfPermitTile)
			return false;
		return true;
	}

}