package gameStuff;

import java.util.Set;

import bonus.Bonus;

/**
	 * this class models a permit tile
	 * @author Emanuele
	 *
	 */

public class PermitTile {
	
	private final Set<City> buildableCities;
	private final Set<Bonus> bonus;
	
	/**
	 * constructor of a permit tile
	 * @param buildableCities is the set of cities where you can build in
	 * @param bonus is the bonus associated to the permit tile 
	 */
	public PermitTile(Set<City> buildableCities, Set<Bonus> bonus) {
		this.buildableCities=buildableCities;
		this.bonus=bonus;
	}
	
	public Set<City> getBuildableCities() {
		return buildableCities;
	}
	
	public Set<Bonus> getBonus() {
		return bonus;
	}
}