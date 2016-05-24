package client.actionDTO;


import model.gameTable.CardColour;

public class ElectCouncillorDTO implements ActionDTO {
	
	private CardColour newCouncillor;
	private CardColour[] councilBalcony;

	public CardColour getNewCouncillor() {
		return newCouncillor;
	}

	public CardColour[] getCouncilBalcony() {
		return councilBalcony;
	}
	
	
	public void setNewCouncillor(CardColour newCouncillor) {
		this.newCouncillor = newCouncillor;
	}

	public void setCouncilBalcony(CardColour[] councilBalcony) {
		this.councilBalcony = councilBalcony;
	}

	@Override
	public String toString() {
		return "m1: elect a councillor";
	}
	
	
}
