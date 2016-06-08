package modelDTO.actionsDTO.standardActions;

import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ActionWithParameters;
import modelDTO.gameTableDTO.RegionDTO;
import modelDTO.parser.ActionParserVisitor;
import modelDTO.parser.ChangePermitTilesParser;
import server.model.actions.Action;
import server.view.mapperVisitor.ActionDTOMapper;

public class ChangePermitTilesDTO implements ActionDTO, ActionWithParameters {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3441345313935469077L;
	private RegionDTO selectedRegion;
	private boolean parametersSetted=false;


	public RegionDTO getSelectedRegion() {
		return selectedRegion;
	}

	public void setSelectedRegion(RegionDTO selectedRegion) {
		this.selectedRegion = selectedRegion;
	}
	
	public boolean checkIfParametersSetted() {
		return parametersSetted;
	}

	public void parametersSetted() {
		this.parametersSetted=true;
	}

	@Override
	public String toString() {
		return "q2: change the permit tiles of a region";
	}

	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		return new ChangePermitTilesParser(this, game);
	}

	@Override
	public Action startVisitor(ActionDTOMapper mapper) {
		return mapper.map(this);
	}
	
	
}
