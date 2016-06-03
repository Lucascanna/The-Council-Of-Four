package modelDTO.actionsDTO.standardActions;

import java.util.ArrayList;
import java.util.List;

import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ActionWithParameters;
import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.gameTableDTO.RegionDTO;
import modelDTO.parser.AcquirePermitTileParser;
import modelDTO.parser.ActionParserVisitor;
import server.model.Game;
import server.model.actions.Action;
import server.model.actions.standardActions.AcquirePermitTile;
import server.model.gameTable.CardColour;
import server.model.gameTable.PoliticsCard;
import server.model.gameTable.RegionBoard;

public class AcquirePermitTileDTO implements ActionDTO, ActionWithParameters {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8143885166021767590L;
	
	private Integer numberOfPermitTile;
	private RegionDTO chosenRegion;
	private List<CardColourDTO> cardsToDescard;
	private boolean parametersSetted=false;

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
	
	public boolean checkIfParametersSetted() {
		return parametersSetted;
	}

	public void parametersSetted() {
		this.parametersSetted=true;
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
		
		action.setNumberOfPermitTile(this.numberOfPermitTile);
		
		for(RegionBoard region : game.getGameTable().getRegionBoards())
			if(region.getName().equals(this.chosenRegion.getName()))
				action.setChosenRegion(region);
		
		List<PoliticsCard> convertedCards =new ArrayList<>();
		for(CardColourDTO cardColourDTO : this.cardsToDescard)
			convertedCards.add(new PoliticsCard(new CardColour(cardColourDTO.getName())));
		action.setCardsToDescard(convertedCards);
		
		return action;
	}
}
