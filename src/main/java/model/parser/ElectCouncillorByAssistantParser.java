package model.parser;

import java.util.ArrayList;
import java.util.List;

import model.actions.Action;
import model.actions.ElectCouncillorByAssistant;

public class ElectCouncillorByAssistantParser implements ActionParserVisitor {

	private ElectCouncillorByAssistant selectedAction;
			
	public ElectCouncillorByAssistantParser(ElectCouncillorByAssistant selectedAction) {
		this.selectedAction=selectedAction;
	}

	@Override
	public List<List<String>> acceptableParameters(Parser parser) {
		List<List<String>> acceptableStrings=new ArrayList<List<String>>();
		acceptableStrings.add(parser.acceptableCouncillors());
		acceptableStrings.add(parser.acceptableCouncilBalcony());
		return acceptableStrings;
	}

	@Override
	public Action parametersParser(List<String> stringParameters, Parser parser) {
		selectedAction.setNewCouncillor(parser.councillorTranslator
				(stringParameters.remove(0)));
		selectedAction.setCouncilBalcony(parser.councilBalconyTranslator
				(stringParameters.remove(0)));
		return selectedAction;
	}

}
