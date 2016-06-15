package client.modelDTO.actionsDTO.standardActions;

import java.util.List;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsDTO.actionsParametersSetters.AcquirePermitTileParser;
import client.modelDTO.actionsDTO.actionsParametersSetters.ActionParserVisitor;
import client.modelDTO.gameTableDTO.CardColourDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.view.ClientView;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

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
	public ActionParserVisitor setParser(ClientView view, GameDTO game) {
		return new AcquirePermitTileParser(this, view, game);
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return mapper.map(this);
	}
}
