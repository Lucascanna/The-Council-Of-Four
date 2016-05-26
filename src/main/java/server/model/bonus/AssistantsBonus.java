package server.model.bonus;

import server.model.Game;

/**
 * AssistantsBonus implements the Bonus Class
 * @author Andrea, Emanuele
 *
 */

public class AssistantsBonus implements Bonus{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2791888304194029622L;
	private final int assistantsIcreasement;

	/**
	 * Constructor of AssistantsBonus
	 * @param assistantsIcreasement is the increasement of assistants
	 */
	public  AssistantsBonus(int assistantsIcreasement){
		this.assistantsIcreasement=assistantsIcreasement;
	}
	
	/**
	 * Assigns to the current player a number assistantsIcreasement of assistants
	 * @param currentPlayer is the player who is playing the turn
	 */
	public void assignBonus(Game game) {
		game.getCurrentPlayer().incrementAssistants(assistantsIcreasement);
	}

	@Override
	public String toString() {
		return "assistants+" + assistantsIcreasement;
	}

}