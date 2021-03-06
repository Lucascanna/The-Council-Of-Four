package server.model.actions.standardActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.standardActions.ElectCouncillorByAssistantDTO;
import server.model.Game;
import server.model.actions.QuickAction;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.Councillor;
import server.model.player.Player;
import server.serverNotifies.ErrorServerNotify;
import server.serverNotifies.GameTableServerNotify;
import server.serverNotifies.MessageServerNotify;
import server.serverNotifies.PlayerServerNotify;

/**
 * It's the quick action "elect councillor by sending an assistant"
 * @author cg31
 *
 */
public class ElectCouncillorByAssistant implements QuickAction {

	private Councillor newCouncillor;
	private CouncilBalcony councilBalcony;
	public static final int necessaryAssistants=1;
	
	public void setNewCouncillor(Councillor newCouncillor) {
		this.newCouncillor = newCouncillor;
	}

	public void setCouncilBalcony(CouncilBalcony councilBalcony) {
		this.councilBalcony = councilBalcony;
	}

	private boolean checkAssistants(Game game){
		return game.getCurrentPlayer().getNumberOfAssistants()>=necessaryAssistants;
	}

	
	public Councillor getNewCouncillor() {
		return newCouncillor;
	}

	public CouncilBalcony getCouncilBalcony() {
		return councilBalcony;
	}

	/**
	  * Substitutes a given councillor in one of the balconies of the game,
	  * adds the old substituted councillor in the reserve,
	  * decrement an assistant to the current player.
	  * @return TRUE if the action ends well; FALSE otherwise.
	 */
	@Override
	public boolean executeAction(Game game) throws NullPointerException{
		if(this.councilBalcony==null || this.newCouncillor==null)
			throw new NullPointerException("Parameters not setted");

		if (!this.checkAssistants(game)) {
			game.notifyObserver(new ErrorServerNotify("It seems that you haven't enough assistants!. Try again or choose another action",
					Arrays.asList(game.getCurrentPlayer())));
			return false;
		}
		
		Councillor oldCouncillor=this.councilBalcony.substituteCouncillor(this.newCouncillor);
		game.getGameTable().getCouncilReserve().getCouncillors().add(oldCouncillor);
		for (Councillor councillor : game.getGameTable().getCouncilReserve().getCouncillors())
			if (councillor.getColour().getColour().equals(this.newCouncillor.getColour().getColour())) {
				game.getGameTable().getCouncilReserve().removeCouncillor(councillor);
				break;
			}
		
		game.getCurrentPlayer().decrementAssistants(necessaryAssistants);
		
		this.updateClients(game);
		this.nextState(game);

		return true;
	}

	
	@Override
	public void updateClients(Game game) {
		game.notifyObserver(new GameTableServerNotify(game, new ArrayList<Player>(game.getAllPlayers()), false));
		game.notifyObserver(new PlayerServerNotify(game, game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new MessageServerNotify("C succesfully!", 
				Arrays.asList(game.getCurrentPlayer())));
		List<Player> otherPlayers=new ArrayList<>();
		for (Player player : game.getAllPlayers())
			if (!player.equals(game.getCurrentPlayer()))
				otherPlayers.add(player);
		game.notifyObserver(new MessageServerNotify(game.getCurrentPlayer().getName()
				+ " sent an assistant to elect a " + this.newCouncillor.getColour() + " councillor in the " 
				+ this.councilBalcony.toString() + " balcony", otherPlayers));
	}
	
	@Override
	public ActionDTO map() {
		return new ElectCouncillorByAssistantDTO();
	}

}