package model.parser;

import java.util.ArrayList;
import java.util.List;

import model.actions.Action;
import model.actions.BuildByKing;
import model.gameTable.CouncilBalcony;
import model.gameTable.PoliticsCard;

public class BuildWithKingParser implements ActionParserVisitor {

	@Override
	public List<List<String>> acceptableParameters(Parser parser) {
		List<List<String>> acceptableStrings=new ArrayList<List<String>>();
		acceptableStrings.add(parser.acceptableCities());
		acceptableStrings.add(parser.acceptableFirstPoliticsCard());
		for (int i=0; i<=CouncilBalcony.getNumberofcouncillors(); i++)
			acceptableStrings.add(parser.acceptablePoliticsCards());
		return acceptableStrings;
	}

	@Override
	public Action parametersParser(List<String> stringParameters, Parser parser) {
		BuildByKing buildByPermitTile=new BuildByKing(parser.game);
		buildByPermitTile.setSelectedCity(parser.cityTranslator
				(stringParameters.remove(0)));
		List<PoliticsCard> cardsTranslated = new ArrayList<PoliticsCard>();
		cardsTranslated.add(parser.politicsCardTranslator(stringParameters.remove(0)));
		for (String parameter : stringParameters)
			if (!parameter.equals("x"))
				cardsTranslated.add(parser.politicsCardTranslator(parameter));
		buildByPermitTile.setCardsToDescard(cardsTranslated);
		return buildByPermitTile;
	}

}
