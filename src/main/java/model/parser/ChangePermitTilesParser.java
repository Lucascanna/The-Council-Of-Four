package model.parser;

import java.util.ArrayList;
import java.util.List;

import model.actions.Action;
import model.actions.ChangePermitTiles;

public class ChangePermitTilesParser implements ActionParserVisitor {

	private ChangePermitTiles selectedAction;
			
	public ChangePermitTilesParser(ChangePermitTiles selectedAction) {
		this.selectedAction=selectedAction;
	}

	@Override
	public List<List<String>> acceptableParameters(Parser parser) {
		List<List<String>> acceptableStrings=new ArrayList<List<String>>();
		acceptableStrings.add(parser.acceptableRegions());
		return acceptableStrings;
	}

	@Override
	public Action parametersParser(List<String> stringParameters, Parser parser) {
		selectedAction.setSelectedRegion(parser.regionTranslator
				(stringParameters.remove(0)));
		return selectedAction;
	}

}
