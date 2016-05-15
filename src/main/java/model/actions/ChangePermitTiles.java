package model.actions;

import model.Game;
import model.gameTable.RegionBoard;
import model.parser.ActionParserVisitor;
import model.parser.ChangePermitTilesParser;
import view.GameNotify;

/**
 * It's the quick action "change permit tiles" it operates on the 
 * protected attribute game through the method executeAction.
 * The controller will pass to the constructor the region where to change the two uncovered permit tiles  
 * @author Luca
 *
 */
public class ChangePermitTiles extends QuickAction implements NeedParameters{
	
	private RegionBoard selectedRegion;
	private static final int necessaryAssistants=1;
	
	public  ChangePermitTiles(Game game){
		super(game);
	}
	
	
	public void setSelectedRegion(RegionBoard selectedRegion) {
		this.selectedRegion = selectedRegion;
	}
	
	
	private boolean checkAssistant(){
		return this.game.getCurrentPlayer().getAssistants()>=necessaryAssistants;
	}
	
	
	/**
	 * Changes the two uncovered permit tiles of the selected region
	 * and decrements the necessary assistant to the current player.
	 * @return TRUE if the action ends well; FALSE otherwise.
	 */
	public boolean executeAction() throws NullPointerException{
		
		if(this.selectedRegion==null)
			throw new NullPointerException("Paramters not setted");
		if(!this.checkAssistant()){
			this.sendErrorNotify();
			return false;
		}
		for(RegionBoard region : this.game.getGameTable().getRegionBoards())
			if(region.equals(selectedRegion))
				region.substitutePermitTiles();
		this.game.getCurrentPlayer().decrementAssistants(necessaryAssistants);
		
		this.nextState();
		this.game.notifyObserver(new GameNotify(this.game));
		return true;
	}

	@Override
	public String toString() {
		return "q2: change the permit tiles of a region";
	}


	@Override
	public ActionParserVisitor setParser() {
		return new ChangePermitTilesParser(this);
	}
				
}