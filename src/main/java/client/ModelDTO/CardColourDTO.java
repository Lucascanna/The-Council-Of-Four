package client.ModelDTO;

import model.gameTable.CardColour;

public class CardColourDTO implements ModelDTO<CardColour>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8929965571917578063L;
	private String name;
	
	@Override
	public void map(CardColour realObject) {
		this.name=realObject.getColour();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CardColourDTO [name=" + name + "]";
	}
	
	
}
