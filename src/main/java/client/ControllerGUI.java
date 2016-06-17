package client;

import java.util.Arrays;
import java.util.List;

import client.modelDTO.gameTableDTO.RegionDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ControllerGUI {


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
	
	public List<ImageView> getCouncillors(RegionDTO balcony){
		switch(balcony.getName()){
		case "Sea":
			return Arrays.asList(seaConcillor1,seaConcillor2,seaConcillor3,seaConcillor4);
		case "Hill":
			return Arrays.asList(hillConcillor1,hillConcillor2,hillConcillor3,hillConcillor4);
		case "Mountain":
			return Arrays.asList(mountainConcillor1,mountainConcillor2,mountainConcillor3,mountainConcillor4);
		default:
			throw new IllegalArgumentException("Region does not exist!");
		}
	}
	
	public List<ImageView> getKingCouncillors(){
		return Arrays.asList(kingConcillor1,kingConcillor2,kingConcillor3,kingConcillor4);
	}

	
}
