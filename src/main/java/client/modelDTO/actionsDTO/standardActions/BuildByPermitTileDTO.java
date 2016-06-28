package client.modelDTO.actionsDTO.standardActions;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsDTO.actionsParametersSetters.ActionParserVisitor;
import client.modelDTO.actionsDTO.actionsParametersSetters.BuildByPermitTileParser;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

public class BuildByPermitTileDTO implements ActionDTO, ActionWithParameters {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8061305769975404856L;
	private  PermitTileDTO selectedPermitTile;
	private  CityDTO selectedCity;
	private boolean parametersSetted=false;

	public PermitTileDTO getSelectedPermitTile() {
		return selectedPermitTile;
	}

	public CityDTO getSelectedCity() {
		return selectedCity;
	}

	public void setSelectedPermitTile(PermitTileDTO selectedPermitTile) {
		this.selectedPermitTile = selectedPermitTile;
	}

	public void setSelectedCity(CityDTO selectedCity) {
		this.selectedCity = selectedCity;
	}
	@Override
	public boolean checkIfParametersSetted() {
		return parametersSetted;
	}
	@Override
	public void parametersSetted() {
		this.parametersSetted=true;
	}

	@Override
	public String toString() {
		return "m3: build an emporium using a permit tile";
	}

	@Override
	public ActionParserVisitor setParser() {
		return new BuildByPermitTileParser(this);
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return mapper.map(this);
	}
	
	
}
