package modelDTO.gameTableDTO;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import server.model.gameTable.RegionBoard;
import modelDTO.ModelDTO;
import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.gameTableDTO.CityDTO;

public class RegionDTO implements ModelDTO<RegionBoard>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6668103687292375094L;
	private String name;
	private CardColourDTO[] balcony;
	private Set<CityDTO> cities;
	private PermitTileDTO[] uncoveredPermitTiles;
	
	public RegionDTO(){
		
		this.balcony=new CardColourDTO[4]; 
		this.cities=new HashSet<>();		
		this.uncoveredPermitTiles=new PermitTileDTO[2];
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CardColourDTO[] getBalcony() {
		return balcony;
	}

	public void setBalcony(CardColourDTO[] balcony) {
		this.balcony = balcony;
	}

	public Set<CityDTO> getCities() {
		return cities;
	}

	public void setCities(Set<CityDTO> cities) {
		this.cities = cities;
	}

	public PermitTileDTO[] getUncoveredPermitTiles() {
		return uncoveredPermitTiles;
	}

	public void setUncoveredPermitTiles(PermitTileDTO[] uncoveredPermitTiles) {
		this.uncoveredPermitTiles = uncoveredPermitTiles;
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
		RegionDTO other = (RegionDTO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  "\n"+name + "\t" + Arrays.toString(balcony) + "\tTiles:" + Arrays.toString(uncoveredPermitTiles)+
				"\t" + cities;
	}

	
	
}
