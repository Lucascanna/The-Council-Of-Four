package client.view;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import client.ControllerGUI;
import client.connections.Connection;
import client.modelDTO.GameDTO;
import client.modelDTO.ModelDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.gameTableDTO.CardColourDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.CouncillorDTO;
import client.modelDTO.gameTableDTO.GameTableDTO;
import client.modelDTO.gameTableDTO.GenericPlayerDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.modelDTO.gameTableDTO.PoliticsCardDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.modelDTO.gameTableDTO.RewardTokenDTO;
import client.modelDTO.marketDTO.MarketDTO;
import client.modelDTO.marketDTO.MarketableDTO;
import client.modelDTO.marketDTO.OfferDTO;
import client.modelDTO.playerDTO.ClientPlayerDTO;
import client.view.notifies.ClientViewNotify;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import server.model.bonus.AssistantsBonus;
import server.model.bonus.CoinsBonus;
import server.model.bonus.MainActionBonus;
import server.model.bonus.NobilityBonus;
import server.model.bonus.PoliticsCardsBonus;
import server.model.bonus.ScoreBonus;


public class GUI extends ClientView{

	private final ControllerGUI controllerGUI;
	private final Map<ModelDTO, Image> imageMap;
	private Object currentParameter;
	
	public GUI(Connection connection, GameDTO clientGame, ControllerGUI controllerGUI) {
		super(connection, clientGame);
		this.controllerGUI=controllerGUI;
		this.controllerGUI.setClientGame(clientGame);
		this.controllerGUI.setView(this);
		imageMap=new HashMap<>();
		imageMap.put(new CouncillorDTO(new CardColourDTO("Black")),new Image(getClass().getResource("images/councillors/Black.png").toExternalForm()));
		imageMap.put(new CouncillorDTO(new CardColourDTO("Blue")),new Image(getClass().getResource("images/councillors/Blue.png").toExternalForm()));
		imageMap.put(new CouncillorDTO(new CardColourDTO("Orange")),new Image(getClass().getResource("images/councillors/Orange.png").toExternalForm()));
		imageMap.put(new CouncillorDTO(new CardColourDTO("Pink")),new Image(getClass().getResource("images/councillors/Pink.png").toExternalForm()));
		imageMap.put(new CouncillorDTO(new CardColourDTO("Violet")),new Image(getClass().getResource("images/councillors/Violet.png").toExternalForm()));
		imageMap.put(new CouncillorDTO(new CardColourDTO("White")),new Image(getClass().getResource("images/councillors/White.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new AssistantsBonus(1)))), new Image(getClass().getResource("images/token/Assistants+1.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new AssistantsBonus(1),new CoinsBonus(1)))), new Image(getClass().getResource("images/token/Assistants+1Coins+1.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new AssistantsBonus(2)))), new Image(getClass().getResource("images/token/Assistants+2.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new CoinsBonus(1)))), new Image(getClass().getResource("images/token/Coins+1.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new CoinsBonus(2)))), new Image(getClass().getResource("images/token/Coins+2.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new CoinsBonus(3)))), new Image(getClass().getResource("images/token/Coins+3.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new NobilityBonus(1)))), new Image(getClass().getResource("images/token/Nobility+1.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new PoliticsCardsBonus(1)))), new Image(getClass().getResource("images/token/Politics+1.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new AssistantsBonus(1),new PoliticsCardsBonus(1)))), new Image(getClass().getResource("images/token/Politics+1Assistants+1.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new PoliticsCardsBonus(1),new ScoreBonus(1)))), new Image(getClass().getResource("images/token/Politics+1Score+1.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new ScoreBonus(1)))), new Image(getClass().getResource("images/token/Score+1.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new ScoreBonus(2)))), new Image(getClass().getResource("images/token/Score+2.png").toExternalForm()));
		imageMap.put(new RewardTokenDTO(new HashSet<>(Arrays.asList(new ScoreBonus(3)))), new Image(getClass().getResource("images/token/Score+3.png").toExternalForm()));
		imageMap.put(new PoliticsCardDTO(new CardColourDTO("Black")), new Image(getClass().getResource("images/cards/Black.png").toExternalForm()));
		imageMap.put(new PoliticsCardDTO(new CardColourDTO("Blue")), new Image(getClass().getResource("images/cards/Blu.png").toExternalForm()));
		imageMap.put(new PoliticsCardDTO(new CardColourDTO("Orange")), new Image(getClass().getResource("images/cards/Orange.png").toExternalForm()));
		imageMap.put(new PoliticsCardDTO(new CardColourDTO("Pink")), new Image(getClass().getResource("images/cards/Pink.png").toExternalForm()));
		imageMap.put(new PoliticsCardDTO(new CardColourDTO("Rainbow")), new Image(getClass().getResource("images/cards/Rainbow.png").toExternalForm()));
		imageMap.put(new PoliticsCardDTO(new CardColourDTO("Violet")), new Image(getClass().getResource("images/cards/Violet.png").toExternalForm()));
		imageMap.put(new PoliticsCardDTO(new CardColourDTO("White")), new Image(getClass().getResource("images/cards/White.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Arkon"), new CityDTO("Dorful"), new CityDTO("Esti"))),new HashSet<>(Arrays.asList(new CoinsBonus(1),new ScoreBonus(2)))), new Image(getClass().getResource("images/seaPermitTile/3.1.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Burgen"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(3),new AssistantsBonus(1)))), new Image(getClass().getResource("images/seaPermitTile/3.2.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Dorful"), new CityDTO("Esti"))),new HashSet<>(Arrays.asList(new ScoreBonus(3),new AssistantsBonus(1)))), new Image(getClass().getResource("images/seaPermitTile/3.3.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Burgen"), new CityDTO("Castrum"))),new HashSet<>(Arrays.asList(new CoinsBonus(3),new ScoreBonus(3)))), new Image(getClass().getResource("images/seaPermitTile/3.4.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Castrum"), new CityDTO("Dorful"))),new HashSet<>(Arrays.asList(new AssistantsBonus(1),new ScoreBonus(3)))), new Image(getClass().getResource("images/seaPermitTile/3.5.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Arkon"))),new HashSet<>(Arrays.asList(new CoinsBonus(3),new ScoreBonus(4)))), new Image(getClass().getResource("images/seaPermitTile/3.6.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Castrum"))),new HashSet<>(Arrays.asList(new CoinsBonus(3),new AssistantsBonus(2)))), new Image(getClass().getResource("images/seaPermitTile/3.7.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Dorful"))),new HashSet<>(Arrays.asList(new ScoreBonus(7)))), new Image(getClass().getResource("images/seaPermitTile/3.8.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Arkon"), new CityDTO("Burgen"))),new HashSet<>(Arrays.asList(new CoinsBonus(3),new PoliticsCardsBonus(1)))), new Image(getClass().getResource("images/seaPermitTile/3.9.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Arkon"), new CityDTO("Esti"))),new HashSet<>(Arrays.asList(new NobilityBonus(2)))), new Image(getClass().getResource("images/seaPermitTile/3.10.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Arkon"), new CityDTO("Burgen"), new CityDTO("Esti"))),new HashSet<>(Arrays.asList(new CoinsBonus(3)))), new Image(getClass().getResource("images/seaPermitTile/3.11.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Arkon"), new CityDTO("Burgen"), new CityDTO("Castrum"))),new HashSet<>(Arrays.asList(new AssistantsBonus(2)))), new Image(getClass().getResource("images/seaPermitTile/3.12.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Burgen"), new CityDTO("Dorful"), new CityDTO("Castrum"))),new HashSet<>(Arrays.asList(new AssistantsBonus(1),new ScoreBonus(1)))), new Image(getClass().getResource("images/seaPermitTile/3.13.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Castrum"), new CityDTO("Dorful"), new CityDTO("Esti"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(1),new NobilityBonus(1)))), new Image(getClass().getResource("images/seaPermitTile/3.14.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Esti"))),new HashSet<>(Arrays.asList(new MainActionBonus(),new CoinsBonus(2 )))), new Image(getClass().getResource("images/seaPermitTile/3.15.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Framek"), new CityDTO("Graden"), new CityDTO("Hellar"))),new HashSet<>(Arrays.asList(new NobilityBonus(1),new CoinsBonus(1)))), new Image(getClass().getResource("images/hillPermitTile/1.1.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Indur"), new CityDTO("Hellar"))),new HashSet<>(Arrays.asList(new ScoreBonus(5)))), new Image(getClass().getResource("images/hillPermitTile/1.2.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Graden"))),new HashSet<>(Arrays.asList(new AssistantsBonus(2),new ScoreBonus(2),new NobilityBonus(1)))), new Image(getClass().getResource("images/hillPermitTile/1.3.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Framek"), new CityDTO("Indur"), new CityDTO("Juvelar"))),new HashSet<>(Arrays.asList(new NobilityBonus(1),new AssistantsBonus(1)))), new Image(getClass().getResource("images/hillPermitTile/1.4.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Indur"), new CityDTO("Graden"), new CityDTO("Hellar"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(1),new AssistantsBonus(1)))), new Image(getClass().getResource("images/hillPermitTile/1.5.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Hellar"))),new HashSet<>(Arrays.asList(new AssistantsBonus(4)))), new Image(getClass().getResource("images/hillPermitTile/1.6.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Framek"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(2),new CoinsBonus(4)))), new Image(getClass().getResource("images/hillPermitTile/1.7.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Framek"), new CityDTO("Juvelar"))),new HashSet<>(Arrays.asList(new AssistantsBonus(2),new CoinsBonus(1)))), new Image(getClass().getResource("images/hillPermitTile/1.8.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Framek"), new CityDTO("Graden"))),new HashSet<>(Arrays.asList(new CoinsBonus(5)))), new Image(getClass().getResource("images/hillPermitTile/1.9.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Indur"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(2),new AssistantsBonus(2)))), new Image(getClass().getResource("images/hillPermitTile/1.10.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Juvelar"))),new HashSet<>(Arrays.asList(new MainActionBonus(),new ScoreBonus(2)))), new Image(getClass().getResource("images/hillPermitTile/1.11.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Indur"), new CityDTO("Juvelar"), new CityDTO("Hellar"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(2)))), new Image(getClass().getResource("images/hillPermitTile/1.12.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Indur"), new CityDTO("Juvelar"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(2),new NobilityBonus(1)))), new Image(getClass().getResource("images/hillPermitTile/1.13.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Framek"), new CityDTO("Graden"), new CityDTO("Juvelar"))),new HashSet<>(Arrays.asList(new ScoreBonus(1),new CoinsBonus(2)))), new Image(getClass().getResource("images/hillPermitTile/1.14.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Hellar"), new CityDTO("Graden"))),new HashSet<>(Arrays.asList(new AssistantsBonus(3)))), new Image(getClass().getResource("images/hillPermitTile/1.15.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Kultos"), new CityDTO("Lyram"), new CityDTO("Merkatim"))),new HashSet<>(Arrays.asList(new AssistantsBonus(1),new CoinsBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.2.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Naris"))),new HashSet<>(Arrays.asList(new CoinsBonus(7)))), new Image(getClass().getResource("images/mountainPermitTile/2.1.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Osium"), new CityDTO("Kultos"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(1),new NobilityBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.3.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Kultos"), new CityDTO("Lyram"), new CityDTO("Osium"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(1),new CoinsBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.4.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Naris"), new CityDTO("Lyram"), new CityDTO("Merkatim"))),new HashSet<>(Arrays.asList(new ScoreBonus(3)))), new Image(getClass().getResource("images/mountainPermitTile/2.5.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Lyram"), new CityDTO("Merkatim"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(3)))), new Image(getClass().getResource("images/mountainPermitTile/2.6.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Naris"), new CityDTO("Osium"), new CityDTO("Merkatim"))),new HashSet<>(Arrays.asList(new NobilityBonus(1),new ScoreBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.7.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Kultos"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(4)))), new Image(getClass().getResource("images/mountainPermitTile/2.8.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Osium"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(3),new NobilityBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.9.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Lyram"))),new HashSet<>(Arrays.asList(new AssistantsBonus(3),new CoinsBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.10.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Merkatim"))),new HashSet<>(Arrays.asList(new ScoreBonus(5),new NobilityBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.11.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Kultos"), new CityDTO("Naris"), new CityDTO("Osium"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(1),new ScoreBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.12.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Kultos"), new CityDTO("Lyram"))),new HashSet<>(Arrays.asList(new PoliticsCardsBonus(1),new AssistantsBonus(1)))), new Image(getClass().getResource("images/mountainPermitTile/2.13.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Naris"), new CityDTO("Merkatim"))),new HashSet<>(Arrays.asList(new MainActionBonus()))), new Image(getClass().getResource("images/mountainPermitTile/2.14.png").toExternalForm()));
		imageMap.put(new PermitTileDTO(new HashSet<>(Arrays.asList(new CityDTO("Naris"), new CityDTO("Osium"))),new HashSet<>(Arrays.asList(new ScoreBonus(2),new PoliticsCardsBonus(2)))), new Image(getClass().getResource("images/mountainPermitTile/2.15.png").toExternalForm()));	
	}

	
	
	public Object getCurrentParameter() {
		return currentParameter;
	}



	public void setCurrentParameter(Object currentParameter) {
		this.currentParameter = currentParameter;
	}


	
	@Override
	public void update(ClientViewNotify notify) {
		notify.updateView(this);	
	}

	@Override
	public void input() throws RemoteException {
		return;
	}

	@Override
	public void displayMessage(String string) {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				controllerGUI.getMessageBox().appendText(string+"\n");	
			}
		});
	}
	
	@Override
	public void displayError(String string) {
		this.controllerGUI.getMessageBox().appendText("ERROR: "+string);		
	}
	
	@Override
	public void displayAvailableActions(List<ActionDTO> availableActions) {
		return;
	}

	@Override
	public void displayGameTable(GameTableDTO clientGame) {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				displayCouncillors(clientGame);
				displayTokens(clientGame);
				displayPlayers(clientGame.getClientPlayers());
				displayPermitTiles(clientGame);
			}
		});
		
	}
	
	private void displayPermitTiles(GameTableDTO clientGame){
		controllerGUI.getSeaPermitTile()[0].setImage(imageMap.get(clientGame.getClientRegions().get(0).getUncoveredPermitTiles()[0]));
		controllerGUI.getSeaPermitTile()[1].setImage(imageMap.get(clientGame.getClientRegions().get(0).getUncoveredPermitTiles()[1]));
		controllerGUI.getHillPermitTile()[0].setImage(imageMap.get(clientGame.getClientRegions().get(1).getUncoveredPermitTiles()[0]));
		controllerGUI.getHillPermitTile()[1].setImage(imageMap.get(clientGame.getClientRegions().get(1).getUncoveredPermitTiles()[1]));
		controllerGUI.getMountainPermitTile()[0].setImage(imageMap.get(clientGame.getClientRegions().get(2).getUncoveredPermitTiles()[0]));
		controllerGUI.getMountainPermitTile()[1].setImage(imageMap.get(clientGame.getClientRegions().get(2).getUncoveredPermitTiles()[1]));
	}
	
	private void displayPlayers(List<GenericPlayerDTO> players){
		for(int i=0; i<players.size();i++){
			controllerGUI.getScoreLabels().get(i).setText(String.valueOf(players.get(i).getScore()));
			controllerGUI.getCoinsLabels().get(i).setText(String.valueOf(players.get(i).getCoins()));
			controllerGUI.getAssistantsLabels().get(i).setText(String.valueOf(players.get(i).getAssistants()));
			controllerGUI.getNobilityLabels().get(i).setText(String.valueOf(players.get(i).getNobility()));
			controllerGUI.getRemainingEmporiumsLabels().get(i).setText(String.valueOf(players.get(i).getEmporiums()));
			
		}
/*		for(int i=0; i<players.size();i++){
			controllerGUI.getNamesLabels().get(i).setText(players.get(i).getName());
			controllerGUI.getScoreLabels().get(i).setText(String.valueOf(players.get(i).getScore()));
			controllerGUI.getCoinsLabels().get(i).setText(String.valueOf(players.get(i).getCoins()));
			controllerGUI.getAssistantsLabels().get(i).setText(String.valueOf(players.get(i).getAssistants()));
			controllerGUI.getNobilityLabels().get(i).setText(String.valueOf(players.get(i).getNobility()));
			controllerGUI.getRemainingEmporiumsLabels().get(i).setText(String.valueOf(players.get(i).getEmporiums()));
			
		}*/
	}
	
	private void displayCouncillors(GameTableDTO clientGame){
		for(RegionDTO region : clientGame.getClientRegions())
			for(int i=0; i<4; i++)
				controllerGUI.getCouncillors(region).get(i).setImage(imageMap.get(region.getBalcony()[i]));
		for(int i=0; i<4; i++)
			controllerGUI.getKingCouncillors().get(i).setImage(imageMap.get(clientGame.getClientKingBalcony()[i]));
//		for(CouncillorDTO councillor : clientGame.getClientCouncillorReserve())
	//		controllerGUI.getCouncillorReserve().getChildren().add(new ImageView(imageMap.get(coucillor)));			
	}
	
	private void displayTokens(GameTableDTO clientGame){
		for(RegionDTO region : clientGame.getClientRegions())
			for(CityDTO city : region.getCities())
				controllerGUI.getRewardToken(city).setImage(imageMap.get(city.getRewardToken()));
	}

	@Override
	public void displayPlayer(ClientPlayerDTO player) {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				controllerGUI.getHand().getChildren().clear();
				for (PoliticsCardDTO card : player.getHand()){
					controllerGUI.getHand().getChildren().add(new ImageView(imageMap.get(card)));
					((ImageView)controllerGUI.getHand().getChildren().get(controllerGUI.getHand().getChildren().size()-1)).setFitHeight(90);
					((ImageView)controllerGUI.getHand().getChildren().get(controllerGUI.getHand().getChildren().size()-1)).setFitWidth(50);
				}			
			}
		});
		
	}

	@Override
	public void displayMarket(MarketDTO market) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void displayFinalRanking(ArrayList<GenericPlayerDTO> finalRankingTable) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void displayChatMessage(String message) {
		// TODO Auto-generated method stub
		
	}
	
	

	@Override
	public RegionDTO askForRegionBoard(List<RegionDTO> acceptableRegions) {
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (RegionDTO) this.currentParameter;
	}

	@Override
	public PermitTileDTO askForPermitTile(List<PermitTileDTO> acceptablePermitTiles) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CouncillorDTO askForCouncillor(List<CouncillorDTO> acceptableCouncillors) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public CouncillorDTO[] askForCouncilBalcony(List<CouncillorDTO[]> acceptableCouncillors) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityDTO askForCity(List<CityDTO> acceptableCities) {
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (CityDTO) this.currentParameter;
	}

	@Override
	public List<PoliticsCardDTO> askForPoliticsCards(List<PoliticsCardDTO> acceptablePoliticsCards) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int askForNumberOfPermitTile(List<Integer> acceptableNumberOfPermitTile) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MarketableDTO askForMakingAnOffer(List<MarketableDTO> acceptableObjectsToOffer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int askForPrice() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean askForOtherSelling() {
		return false;
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public OfferDTO askForAcceptingAnOffer(List<OfferDTO> acceptableOffers) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	@Override
	public void ChooseCityBonus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PurchasedPermitTileBonus() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public Connection getConnection() {
		return this.connection;
	}
}
