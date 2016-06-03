package server.model.actions.standardActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.standardActions.AcquirePermitTileDTO;
import server.model.Game;
import server.model.actions.MainAction;
import server.model.bonus.Bonus;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.Councillor;
import server.model.gameTable.PoliticsCard;
import server.model.gameTable.RegionBoard;
import server.view.notifies.AvailableActionsNotify;
import server.view.notifies.ErrorNotify;
import server.view.notifies.GameTableNotify;
import server.view.notifies.PlayerNotify;

/**
 * This class is the main action "acquire permit tile", it operates on the 
 * protected attribute game through the method executeAction.
 * @author Emanuele
 *
 */

public class AcquirePermitTile extends MainAction {


	private Integer numberOfPermitTile;
	private RegionBoard chosenRegion;
	private List<PoliticsCard> cardsToDescard;
	
	
	public void setNumberOfPermitTile(Integer numberOfPermitTile) {
		this.numberOfPermitTile = numberOfPermitTile;
	}

	public void setCardsToDescard(List<PoliticsCard> cardsToDescard) {
		this.cardsToDescard = cardsToDescard;
	}

	public void setChosenRegion(RegionBoard chosenRegion) {
		this.chosenRegion = chosenRegion;
	}

	/**
	 * Acquire a permit tile from a designated region board by satisfying the councillors:
	 * 4 cards of the same colour of the councillors,
	 * 3 cards of the same colour of the councillors + use 4 coins,
	 * 2 cards of the same colour of the councillors + use 7 coins,
	 * 1 card of the same colour of the councillors + use 10 coins;
	 * each rainbow card requires 1 additional coin each to use.
	 */
	@Override
	public boolean executeAction(Game game) throws NullPointerException, IllegalArgumentException{
		if (this.numberOfPermitTile==null||
				this.cardsToDescard==null||
				this.chosenRegion==null)
			throw new NullPointerException("Paramters not setted");
		
		if (!this.CheckEnoughCoins(game)) {
			game.notifyObserver(new ErrorNotify("It seems that you haven't enough coins!", 
					Arrays.asList(game.getCurrentPlayer())));
			return false;
		}
		if (!this.CheckHandSatisfiesBalcony(game)) {
					game.notifyObserver(new ErrorNotify("It seems that the cards in you hand don't satisfy the councillors!", 
							Arrays.asList(game.getCurrentPlayer())));
			return false;
		}
					
		for (Bonus bonusToAssign : this.chosenRegion.getUncoveredPermitTiles()[numberOfPermitTile].getBonus())
			bonusToAssign.assignBonus(game);
		game.getCurrentPlayer().decrementCoins(CoinsToPay());
		
		for (PoliticsCard card : cardsToDescard) {
			if (!game.getCurrentPlayer().getHand().contains(card))
				throw new IllegalArgumentException("Current player hasn't these cards");
			game.getCurrentPlayer().removeCardFromHand(card);
		}
		
		game.getCurrentPlayer().addTile(this.chosenRegion.pickUncoveredPermitTile(this.numberOfPermitTile));
		this.chosenRegion.uncoverPermitTiles();
		
		this.nextState(game);
		
		game.notifyObserver(new GameTableNotify(game, game.getPlayers()));
		game.notifyObserver(new PlayerNotify(game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new AvailableActionsNotify(game.getState().getAcceptableActions(game), 
				Arrays.asList(game.getCurrentPlayer())));
		
		return true;
	}
	
	/**
	 * Calculate the coins you have to pay according to the cards you
	 * selected and the number of rainbow politics cards
	 * @return the amount of coins
	 * @throws IndexOutOfBoundsException if the list of cards to discard is empty
	 */
	private int CoinsToPay() throws IndexOutOfBoundsException {
		if (this.cardsToDescard.isEmpty())
			throw new IndexOutOfBoundsException("you have selected 0 cards to buy a permit tile");
		
		int coinsToPay;
		
		if (this.cardsToDescard.size()==CouncilBalcony.getNumberofcouncillors())
			coinsToPay=0;
		else {
			coinsToPay=((CouncilBalcony.getNumberofcouncillors()-(this.cardsToDescard.size()))*3)+1;
		}
		for (PoliticsCard card : cardsToDescard)
			if ("Rainbow".equals(card.getColour().getColour()))
				coinsToPay++;
	//	System.out.println("councillors  "+CouncilBalcony.getNumberofcouncillors());
	//	System.out.println("hand size  "+this.cardsToDescard.size());
	//	System.out.println("hand  "+this.cardsToDescard);
	//	System.out.println("coinstopay  "+coinsToPay);
		return coinsToPay;
	}
	
	/**
	 * checks if the player has enough coins
	 */
	private boolean CheckEnoughCoins(Game game) {
	//	System.out.println("coins of the player "+game.getCurrentPlayer().getCoins());
	//	System.out.println("coinstopaybb  "+CoinsToPay());
		return CoinsToPay() < game.getCurrentPlayer().getCoins();
	}
	
	/**
	 * checks if the player hands cards' colour match with the colour of councillors
	 * of the selected balcony
	 */
	private boolean CheckHandSatisfiesBalcony(Game game) {
		List<Councillor> temporaryBalcony=new ArrayList<Councillor>();
		int satisfyCounter=0;
		for (int i=0; i<=CouncilBalcony.getNumberofcouncillors()-1; i++)
			temporaryBalcony.add(game.getGameTable().getCouncilOfKing().getCouncillors()[i]);
		
		for (PoliticsCard politicsCardInHand: this.cardsToDescard) {
			if (politicsCardInHand.getColour().getColour()=="Rainbow")
				satisfyCounter++;
			for (Councillor councillorToSatisfy : temporaryBalcony)
				if (councillorToSatisfy.getColour().getColour().equals(politicsCardInHand.getColour().getColour())) {
					temporaryBalcony.remove(councillorToSatisfy);
					satisfyCounter++;
				}
			
			
		/*	for (int j=0; j<=temporaryBalcony.size()-1;) {
				if (temporaryBalcony.get(j).getColour().equals(politicsCardInHand.getColour())) {
					temporaryBalcony.remove(temporaryBalcony.get(j));
					satisfyCounter++;
				}
				else
					j++;
			}*/
		}
	//	System.out.println(this.cardsToDescard);
	//	System.out.println(temporaryBalcony);
	//	System.out.println(satisfyCounter);
	//	System.out.println(this.cardsToDescard.size());
		return satisfyCounter==this.cardsToDescard.size();
	}

	
	@Override
	public ActionDTO map() {
		return new AcquirePermitTileDTO();
	}
	
}