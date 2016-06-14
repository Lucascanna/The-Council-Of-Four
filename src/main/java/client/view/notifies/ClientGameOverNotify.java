package client.view.notifies;

import java.util.ArrayList;

import client.modelDTO.gameTableDTO.GenericPlayerDTO;
import client.view.ClientView;

public class ClientGameOverNotify implements ClientViewNotify {

	private final ArrayList<GenericPlayerDTO> finalRankingTable;
	
	public ClientGameOverNotify(ArrayList<GenericPlayerDTO> finalRankingTable) {
		this.finalRankingTable=finalRankingTable;
	}
	
	@Override
	public void updateView(ClientView view) {
		view.displayFinalRanking(this.finalRankingTable);
	}

}
