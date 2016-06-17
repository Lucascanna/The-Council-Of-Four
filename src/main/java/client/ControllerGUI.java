package client;

import java.rmi.RemoteException;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.standardActions.AcquirePermitTileDTO;
import client.modelDTO.actionsDTO.standardActions.AddictionalMainActionDTO;
import client.modelDTO.actionsDTO.standardActions.BuildByKingDTO;
import client.modelDTO.actionsDTO.standardActions.BuildByPermitTileDTO;
import client.modelDTO.actionsDTO.standardActions.ChangePermitTilesDTO;
import client.modelDTO.actionsDTO.standardActions.ElectCouncillorByAssistantDTO;
import client.modelDTO.actionsDTO.standardActions.ElectCouncillorDTO;
import client.modelDTO.actionsDTO.standardActions.EngageAssistantDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.view.GUI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ControllerGUI {
	
	private GameDTO clientGame;
	private GUI view;

	public ControllerGUI(GameDTO clientGame, GUI view) {
		this.clientGame=clientGame;
		this.view=view;
	}


	@FXML
	private Pane seaRegion;
	
	@FXML
	private Pane hillRegion;
	  
	@FXML
	private Pane mountainRegion;
	
	@FXML
	private ImageView kingBalcony;
	
	@FXML
	private ImageView seaConcillor1;
	
	@FXML
	private ImageView seaConcillor2;
	
	@FXML
	private ImageView seaConcillor3;
	
	@FXML
	private ImageView seaConcillor4;
	
	@FXML
	private ImageView hillConcillor1;
	
	@FXML
	private ImageView hillConcillor2;
	
	@FXML
	private ImageView hillConcillor3;
	
	@FXML
	private ImageView hillConcillor4;
	
	@FXML
	private ImageView mountainConcillor1;
	
	@FXML
	private ImageView mountainConcillor2;
	
	@FXML
	private ImageView mountainConcillor3;
	
	@FXML
	private ImageView mountainConcillor4;
	
	@FXML
	private ImageView kingConcillor1;
	
	@FXML
	private ImageView kingConcillor2;
	
	@FXML
	private ImageView kingConcillor3;
	
	@FXML
	private ImageView kingConcillor4;
	
	@FXML
	private ImageView reserveConcillor1;
	
	@FXML
	private ImageView reserveConcillor2;
	
	@FXML
	private ImageView reserveConcillor3;
	
	@FXML
	private ImageView reserveConcillor4;
	
	@FXML
	private ImageView reserveConcillor5;
	
	@FXML
	private ImageView reserveConcillor6;
	
	@FXML
	private ImageView reserveConcillor7;
	
	@FXML
	private ImageView reserveConcillor8;
	
	@FXML
	private Button m1; 
	
	@FXML
	private Button m2;
	
	@FXML
	private Button m3;
	
	@FXML
	private Button m4;
	
	@FXML
	private Button q1;
	
	@FXML
	private Button q2; 
	
	@FXML
	private Button q3;
	
	@FXML
	private Button q4;
	
	@FXML
	private Button skip;
	
	@FXML
	private TextArea messageBox;
	
	public Pane getSeaRegion() {
		return seaRegion;
	}

	public Pane getHillRegion() {
		return hillRegion;
	}

	public Pane getMountainRegion() {
		return mountainRegion;
	}

	public ImageView getKingBalcony() {
		return kingBalcony;
	}

	public ImageView getSeaConcillor1() {
		return seaConcillor1;
	}

	public ImageView getSeaConcillor2() {
		return seaConcillor2;
	}

	public ImageView getSeaConcillor3() {
		return seaConcillor3;
	}

	public ImageView getSeaConcillor4() {
		return seaConcillor4;
	}

	public ImageView getHillConcillor1() {
		return hillConcillor1;
	}

	public ImageView getHillConcillor2() {
		return hillConcillor2;
	}

	public ImageView getHillConcillor3() {
		return hillConcillor3;
	}

	public ImageView getHillConcillor4() {
		return hillConcillor4;
	}

	public ImageView getMountainConcillor1() {
		return mountainConcillor1;
	}

	public ImageView getMountainConcillor2() {
		return mountainConcillor2;
	}

	public ImageView getMountainConcillor3() {
		return mountainConcillor3;
	}

	public ImageView getMountainConcillor4() {
		return mountainConcillor4;
	}

	public ImageView getKingConcillor1() {
		return kingConcillor1;
	}

	public ImageView getKingConcillor2() {
		return kingConcillor2;
	}

	public ImageView getKingConcillor3() {
		return kingConcillor3;
	}

	public ImageView getKingConcillor4() {
		return kingConcillor4;
	}

	public ImageView getReserveConcillor1() {
		return reserveConcillor1;
	}

	public ImageView getReserveConcillor2() {
		return reserveConcillor2;
	}

	public ImageView getReserveConcillor3() {
		return reserveConcillor3;
	}

	public ImageView getReserveConcillor4() {
		return reserveConcillor4;
	}

	public ImageView getReserveConcillor5() {
		return reserveConcillor5;
	}

	public ImageView getReserveConcillor6() {
		return reserveConcillor6;
	}

	public ImageView getReserveConcillor7() {
		return reserveConcillor7;
	}

	public ImageView getReserveConcillor8() {
		return reserveConcillor8;
	}

	public Button getM1() {
		return m1;
	}

	public Button getM2() {
		return m2;
	}

	public Button getM3() {
		return m3;
	}

	public Button getM4() {
		return m4;
	}

	public Button getQ1() {
		return q1;
	}

	public Button getQ2() {
		return q2;
	}

	public Button getQ3() {
		return q3;
	}

	public Button getQ4() {
		return q4;
	}

	public Button getSkip() {
		return skip;
	}

	public TextArea getMessageBox() {
		return messageBox;
	}
	
	public void startM1() {
		ElectCouncillorDTO action=new ElectCouncillorDTO();
		action.setParser(this.view, this.clientGame).setParameters();
	}
	
	public void startM2() {
		AcquirePermitTileDTO action=new AcquirePermitTileDTO();
		action.setParser(this.view, this.clientGame).setParameters();
	}
	
	public void startM3() {
		BuildByPermitTileDTO action=new BuildByPermitTileDTO();
		action.setParser(this.view, this.clientGame).setParameters();
	}
	
	public void startM4() {
		BuildByKingDTO action=new BuildByKingDTO();
		action.setParser(this.view, this.clientGame).setParameters();
	}
	
	public void startQ1() throws RemoteException {
		EngageAssistantDTO action=new EngageAssistantDTO();
		this.view.getConnection().sendAction(action);
	}
	
	public void startQ2() {
		ChangePermitTilesDTO action=new ChangePermitTilesDTO();
		action.setParser(this.view, this.clientGame).setParameters();
	}
	
	public void startQ3() {
		ElectCouncillorByAssistantDTO action=new ElectCouncillorByAssistantDTO();
		action.setParser(this.view, this.clientGame).setParameters();
	}
	
	public void startQ4() throws RemoteException {
		AddictionalMainActionDTO action=new AddictionalMainActionDTO();
		this.view.getConnection().sendAction(action);
	}
	
	
	
	public RegionDTO seaRegionClicked() {
		return null;
	}

	public Pane hillRegionClicked() {
		return null;
	}

	public Pane mountainRegionClicked() {
		return null;
	}

	public ImageView kingBalconyClicked() {
		return null;
	}

	public ImageView reserveConcillor1Clicked() {
		return null;
	}

	public ImageView reserveConcillor2Clicked() {
		return null;
	}

	public ImageView reserveConcillor3Clicked() {
		return null;
	}

	public ImageView reserveConcillor4Clicked() {
		return null;
	}

	public ImageView reserveConcillor5Clicked() {
		return null;
	}

	public ImageView reserveConcillor6Clicked() {
		return null;
	}

	public ImageView reserveConcillor7Clicked() {
		return null;
	}

	public ImageView reserveConcillor8Clicked() {
		return null;
	}
	
}
