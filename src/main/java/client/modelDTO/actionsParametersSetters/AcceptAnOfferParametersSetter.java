package client.modelDTO.actionsParametersSetters;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.marketActions.AcceptAnOfferDTO;
import client.view.ClientView;

/**
 * This class provides the logic to set the needed parameters of a AcceptAnOfferDTO
 * @author cg31
 *
 */
public class AcceptAnOfferParametersSetter implements ActionParametersSetter {

	private AcceptAnOfferDTO selectedAction;
	
	/**
	 * Constructor of AcceptAnOfferDTO
	 * @param selectedAction is the action selected by the user
	 */
	public AcceptAnOfferParametersSetter(AcceptAnOfferDTO selectedAction) {
		this.selectedAction=selectedAction;
	}
	

	@Override
	public void setParameters(ClientView view, GameDTO game) {
		
		if (!game.getMarket().getOffersList().isEmpty()) {
		
			view.displayMessage("Choose the object you want to buy.");
			this.selectedAction.setOffer(view.askForAcceptingAnOffer());
			
			this.selectedAction.parametersSet();
			
		}
		else 
			view.displayMessage("There is nothing to buy from other players");
	}

}
