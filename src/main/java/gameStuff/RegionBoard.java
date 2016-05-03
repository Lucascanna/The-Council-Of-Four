package gameStuff;

import java.util.HashSet;
import java.util.Set;

import bonus.Bonus;

/**
 * Models a region board
 * @author andreapasquali
 *
 */
public class RegionBoard {

	private final String name;
	private final PermitDeck regionPermitDeck;
	private final CouncilBalcony regionBalcony;
	private final Bonus regionBonus;
	private boolean bonusAvailable;
	private final Set<City> regionCities;
	private final PermitTile[] uncoveredPermitTiles;
	private static final int numberOfUncoveredPermitTiles=2;
	
	/**
	 * Initially the bonus is available and the set of the cities of the region is empty
	 * The city's constructor will add the city to the set. 
	 * @param name
	 * @param regionPermitDeck
	 * @param regionBalcony
	 * @param regionBonus
	 */
	public RegionBoard(String name, PermitDeck regionPermitDeck, 
			CouncilBalcony regionBalcony, Bonus regionBonus){		
		this.name=name;		
		this.regionPermitDeck=regionPermitDeck;
		this.regionBalcony=regionBalcony;		
		this.regionBonus=regionBonus;
		this.bonusAvailable=true;
		this.regionCities=new HashSet<City>();
		this.uncoveredPermitTiles=new PermitTile[numberOfUncoveredPermitTiles];
		uncoverPermitTiles();		
	}
	
	
	
	public boolean isBonusAvailable() {
		return bonusAvailable;
	}



	public void notBonusAvailable() {
		this.bonusAvailable = false;
	}



	public String getName() {
		return name;
	}



	public PermitDeck getRegionPermitDeck() {
		return regionPermitDeck;
	}



	public CouncilBalcony getRegionBalcony() {
		return regionBalcony;
	}



	public Bonus getRegionBonus() {
		return regionBonus;
	}



	public Set<City> getRegionCities() {
		return regionCities;
	}



	public PermitTile[] getUncoveredPermitTiles() {
		return uncoveredPermitTiles;
	}
	
	
	public void addCityOfThisRegion(City city){
		this.regionCities.add(city);
	}



	/**
	 * Picks an uncovered permit tile among the available-one.
	 * After the execution of this method there is an empty slot.
	 * @param index
	 * @return the picked uncovered card
	 */
	public PermitTile pickUncoveredPermitTile(int index) {
		if(index>=numberOfUncoveredPermitTiles)
			throw new IllegalArgumentException("Index too big");
		PermitTile temp=this.uncoveredPermitTiles[index];
		this.uncoveredPermitTiles[index]=null;
		return temp;
	}
	
	
	/**
	 * Uncovers a new permit tile in each empty slot.
	 */
	public void uncoverPermitTiles(){
		for(int i=0; i<numberOfUncoveredPermitTiles; i++)
			if(this.uncoveredPermitTiles[i]==null)
				this.uncoveredPermitTiles[i]=
						this.regionPermitDeck.pickPermitTile();
	}
	
	
	/**
	 * Substitutes both of the uncovered permit tiles.
	 */
	public void substitutePermitTiles() {
		for (int i=0; i<=numberOfUncoveredPermitTiles; i++) {
			this.regionPermitDeck.addOnBottom(this.uncoveredPermitTiles[i]);
			this.uncoveredPermitTiles[i]=null;
		}
		uncoverPermitTiles();
	}


}