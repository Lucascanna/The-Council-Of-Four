package server.model.actions.marketActions;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.marketActions.AcceptAnOfferDTO;
import server.model.Game;
import server.model.actions.Action;
import server.model.market.Offer;

public class AcceptAnOffer implements Action {

	
	private Offer offer;

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	
	@Override
	public boolean executeAction(Game game){
		game.getMarket().buyOffer(this.offer, game.getCurrentPlayer());
		game.setState(game.getState().buyActionTransition(game));
		return true;
	}

	@Override
	public ActionDTO map() {
		return new AcceptAnOfferDTO();
	}

}
