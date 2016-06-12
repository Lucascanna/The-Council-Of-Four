package client.view.notifies;

import java.util.ArrayList;
import java.util.Scanner;

import modelDTO.gameTableDTO.GenericPlayerDTO;

public class ClientGameOverNotify implements ClientViewNotify {

	private final ArrayList<GenericPlayerDTO> finalRankingTable;
	
	public ClientGameOverNotify(ArrayList<GenericPlayerDTO> finalRankingTable) {
		this.finalRankingTable=finalRankingTable;
	}
	
	@Override
	public void stamp(Scanner scanner) {
		System.out.println("GAME OVER\n FINAL RANKING TABLE: \n"+finalRankingTable);
	}

}