package client.actionDTO;

import java.util.List;

import client.ModelDTO.CardColourDTO;
import client.ModelDTO.GameDTO;
import client.ModelDTO.RegionDTO;
import client.parser.AcquirePermitTileParser;
import client.parser.ActionParserVisitor;
import model.Game;
import model.actions.Action;
import model.actions.standardAction.AcquirePermitTile;
import model.gameTable.CardColour;

public class AcquirePermitTileDTO implements ActionDTO {

	private Integer numberOfPermitTile;
	private RegionDTO chosenRegion;
	private List<CardColourDTO> cardsToDescard;

	public int getNumberOfPermitTiles() {
		return numberOfPermitTile;
	}

	public RegionDTO getChoosenRegion() {
		return chosenRegion;
	}

	public List<CardColourDTO> getCardsToDescard() {
		return cardsToDescard;
	}
	
	public void setNumberOfPermitTile(Integer numberOfPermitTile) {
		this.numberOfPermitTile=numberOfPermitTile;
	}

	public void setCardsToDescard(List<CardColourDTO> cardsToDescard) {
		this.cardsToDescard=cardsToDescard;
	}

	public void setChosenRegion(RegionDTO chosenRegion) {
		this.chosenRegion=chosenRegion;
	}

	@Override
	public String toString() {
		return "m2: acquire a permit tile";
	}

	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		return new AcquirePermitTileParser(this, game);
	}

	@Override
	public Action map(Game game) {
		AcquirePermitTile action = new AcquirePermitTile();
		
		return null;
	}

}
