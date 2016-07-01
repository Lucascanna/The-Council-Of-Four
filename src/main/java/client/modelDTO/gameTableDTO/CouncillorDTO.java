package client.modelDTO.gameTableDTO;

import client.modelDTO.ModelDTO;

/**
 * This class provides all the informations about a councillor, but without logic
 * @author cg31
 *
 */
public class CouncillorDTO implements ModelDTO {

	private static final long serialVersionUID = 3297590450164485862L;
	private CardColourDTO colour;
	
	/**
	 * Constructor of CouncillorDTO
	 */
	public CouncillorDTO() {
	}
	
	/**
	 * Constructor of CouncillorDTO
	 * @param cardColourDTO is the colour of the councillor
	 */
	public CouncillorDTO(CardColourDTO cardColourDTO) {
		this.colour=cardColourDTO;
	}

	public CardColourDTO getColour() {
		return this.colour;
	}

	public void setColour(CardColourDTO colour) {
		this.colour=colour;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colour == null) ? 0 : colour.hashCode());
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
		CouncillorDTO other = (CouncillorDTO) obj;
		if (colour == null) {
			if (other.colour != null)
				return false;
		} else if (!colour.equals(other.colour))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return colour.toString();
	}

	
}
