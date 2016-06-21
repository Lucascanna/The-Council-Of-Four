package client.view;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import client.ControllerGUI;
import client.connections.Connection;
import client.modelDTO.GameDTO;
import client.modelDTO.ModelDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.gameTableDTO.BonusTileDTO;
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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import server.model.bonus.AssistantsBonus;
import server.model.bonus.CoinsBonus;
import server.model.bonus.MainActionBonus;
import server.model.bonus.NobilityBonus;
import server.model.bonus.PoliticsCardsBonus;
import server.model.bonus.ScoreBonus;
import server.model.gameTable.CouncilBalcony;


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
		imageMap.put(new BonusTileDTO("Sea", new ScoreBonus(5)), new Image(getClass().getResource("images/bonusTiles/bonusMare.png").toExternalForm()));
		imageMap.put(new BonusTileDTO("Hill", new ScoreBonus(5)), new Image(getClass().getResource("images/bonusTiles/bonusColline.png").toExternalForm()));
		imageMap.put(new BonusTileDTO("Mountain", new ScoreBonus(5)), new Image(getClass().getResource("images/bonusTiles/bonusMontagna.png").toExternalForm()));
		imageMap.put(new BonusTileDTO("King", new ScoreBonus(25)), new Image(getClass().getResource("images/kingBonus/kingRewardTile1.png").toExternalForm()));
		imageMap.put(new BonusTileDTO("King", new ScoreBonus(18)), new Image(getClass().getResource("images/kingBonus/kingRewardTile2.png").toExternalForm()));
		imageMap.put(new BonusTileDTO("King", new ScoreBonus(12)), new Image(getClass().getResource("images/kingBonus/kingRewardTile3.png").toExternalForm()));
		imageMap.put(new BonusTileDTO("King", new ScoreBonus(7)), new Image(getClass().getResource("images/kingBonus/kingRewardTile4.png").toExternalForm()));
		imageMap.put(new BonusTileDTO("King", new ScoreBonus(3)), new Image(getClass().getResource("images/kingBonus/kingRewardTile5.png").toExternalForm()));
		imageMap.put(new BonusTileDTO("Blue", new ScoreBonus(5)), new Image(getClass().getResource("images/colourBonus/BlueBonus.png").toExternalForm()));
		imageMap.put(new BonusTileDTO("Bronze", new ScoreBonus(8)), new Image(getClass().getResource("images/colourBonus/BronzeBonus.png").toExternalForm()));
		imageMap.put(new BonusTileDTO("Gold", new ScoreBonus(20)), new Image(getClass().getResource("images/colourBonus/GoldBonus.png").toExternalForm()));
		imageMap.put(new BonusTileDTO("Silver", new ScoreBonus(12)), new Image(getClass().getResource("images/colourBonus/SilverBonus.png").toExternalForm()));
	}

	
	public Object getCurrentParameter() {
		return currentParameter;
	}

	public void setCurrentParameter(Object currentParameter) {
		this.currentParameter = currentParameter;
	}
	
	
	public Connection getConnection() {
		return this.connection;
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
				displayRegions(clientGame);
				displayCities(clientGame);
				diplayBalconies(clientGame);
			}
		});
		
	}
	
	private void displayRegions(GameTableDTO clientGame) {
		controllerGUI.getSeaRegion().setUserData(clientGame.getClientRegions().get(0));
		controllerGUI.getHillRegion().setUserData(clientGame.getClientRegions().get(1));
		controllerGUI.getMountainRegion().setUserData(clientGame.getClientRegions().get(2));
	}
	
	private void displayCities(GameTableDTO clientGame) {
		for (CityDTO city : clientGame.getClientRegions().get(0).getCities()) {
			if ("Arkon".equals(city.getName()))
				controllerGUI.getArkon().setUserData(city);
			if ("Burgen".equals(city.getName()))
				controllerGUI.getBurgen().setUserData(city);
			if ("Castrum".equals(city.getName()))
				controllerGUI.getCastrum().setUserData(city);
			if ("Dorful".equals(city.getName()))
				controllerGUI.getDorful().setUserData(city);
			if ("Esti".equals(city.getName()))
				controllerGUI.getEsti().setUserData(city);
		}
		for (CityDTO city : clientGame.getClientRegions().get(0).getCities()) {
			if ("Framek".equals(city.getName()))
				controllerGUI.getFramek().setUserData(city);
			if ("Graden".equals(city.getName()))
				controllerGUI.getGraden().setUserData(city);
			if ("Hellar".equals(city.getName()))
				controllerGUI.getHellar().setUserData(city);
			if ("Indur".equals(city.getName()))
				controllerGUI.getIndur().setUserData(city);
			if ("Juvelar".equals(city.getName()))
				controllerGUI.getJuvelar().setUserData(city);
		}
		for (CityDTO city : clientGame.getClientRegions().get(0).getCities()) {
			if ("Kultos".equals(city.getName()))
				controllerGUI.getKultos().setUserData(city);
			if ("Lyram".equals(city.getName()))
				controllerGUI.getLyram().setUserData(city);
			if ("Merkatim".equals(city.getName()))
				controllerGUI.getMerkatim().setUserData(city);
			if ("Naris".equals(city.getName()))
				controllerGUI.getNaris().setUserData(city);
			if ("Osium".equals(city.getName()))
				controllerGUI.getOsium().setUserData(city);
		}
	}

	private void diplayBalconies(GameTableDTO clientGame) {
	
	}
	
	private void displayPermitTiles(GameTableDTO clientGame){
		controllerGUI.getSeaPermitTile()[0].setImage(imageMap.get(clientGame.getClientRegions().get(0).getUncoveredPermitTiles()[0]));
		controllerGUI.getSeaPermitTile()[0].setUserData(new Integer(0));
		controllerGUI.getSeaPermitTile()[1].setImage(imageMap.get(clientGame.getClientRegions().get(0).getUncoveredPermitTiles()[1]));
		controllerGUI.getSeaPermitTile()[1].setUserData(new Integer(1));
		controllerGUI.getHillPermitTile()[0].setImage(imageMap.get(clientGame.getClientRegions().get(1).getUncoveredPermitTiles()[0]));
		controllerGUI.getHillPermitTile()[0].setUserData(new Integer(0));
		controllerGUI.getHillPermitTile()[1].setImage(imageMap.get(clientGame.getClientRegions().get(1).getUncoveredPermitTiles()[1]));
		controllerGUI.getHillPermitTile()[1].setUserData(new Integer(1));
		controllerGUI.getMountainPermitTile()[0].setImage(imageMap.get(clientGame.getClientRegions().get(2).getUncoveredPermitTiles()[0]));
		controllerGUI.getMountainPermitTile()[0].setUserData(new Integer(0));
		controllerGUI.getMountainPermitTile()[1].setImage(imageMap.get(clientGame.getClientRegions().get(2).getUncoveredPermitTiles()[1]));
		controllerGUI.getMountainPermitTile()[1].setUserData(new Integer(1));
	}
	
	private void displayPlayers(List<GenericPlayerDTO> players){
		List<GenericPlayerDTO> orderedPlayers = new ArrayList<>(players);
		Collections.sort(orderedPlayers, new Comparator<GenericPlayerDTO>() {

			@Override
			public int compare(GenericPlayerDTO o1, GenericPlayerDTO o2) {
				if(o1.getPlayerNumber()<o2.getPlayerNumber())
					return -1;
				else 
					return 1;
			}
		});
		
		for(int i=0; i<orderedPlayers.size();i++){
			controllerGUI.getNamesLabels().get(i).setText(orderedPlayers.get(i).getName());
			controllerGUI.getScoreLabels().get(i).setText(String.valueOf(orderedPlayers.get(i).getScore()));
			controllerGUI.getCoinsLabels().get(i).setText(String.valueOf(orderedPlayers.get(i).getCoins()));
			controllerGUI.getAssistantsLabels().get(i).setText(String.valueOf(orderedPlayers.get(i).getAssistants()));
			controllerGUI.getNobilityLabels().get(i).setText(String.valueOf(orderedPlayers.get(i).getNobility()));
			controllerGUI.getRemainingEmporiumsLabels().get(i).setText(String.valueOf(orderedPlayers.get(i).getEmporiums()));	
			controllerGUI.getNumberOfPoliticsCards().get(i).setText(String.valueOf(orderedPlayers.get(i).getHand()));
			controllerGUI.getNumberOfCoveredPermitTiles().get(i).setText(String.valueOf(orderedPlayers.get(i).getNumberOfCoveredTiles()));
			for(PermitTileDTO permitTileDTO : orderedPlayers.get(i).getAvailablePermitTiles()){
				ImageView imageView=new ImageView(imageMap.get(permitTileDTO));
				controllerGUI.getPermitTilesOtherPlayers().get(i).getChildren().add(imageView);
				imageView.setFitHeight(60);
				imageView.setPreserveRatio(true);
			}
		}
	}
	
	private void displayCouncillors(GameTableDTO clientGame){
		for(RegionDTO region : clientGame.getClientRegions())
			for(int i=0; i<4; i++)
				controllerGUI.getCouncillors(region).get(i).setImage(imageMap.get(region.getBalcony()[i]));
		for(int i=0; i<4; i++)
			controllerGUI.getKingCouncillors().get(i).setImage(imageMap.get(clientGame.getClientKingBalcony()[i]));
			for(int i=0; i<8; i++)
			controllerGUI.getCouncillorReserve().get(i).setImage(imageMap.get(clientGame.getClientCouncillorReserve().get(i)));			
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
				controllerGUI.getPermitTilesTurnedUpOwned().getChildren().clear();
				for (PoliticsCardDTO card : player.getHand()){
					ImageView imageView=new ImageView(imageMap.get(card));
					controllerGUI.getHand().getChildren().add(imageView);
					imageView.setFitWidth(50);
					imageView.setPreserveRatio(true);
					imageView.setOnMouseClicked(new EventHandler<Event>() {

						@Override
						public void handle(Event event) {
							controllerGUI.handlePoliticsCard(card);	
							
						}
					});
					imageView.setDisable(true);
				}
				for(PermitTileDTO permitTileDTO : player.getAvailablePermitTiles()){
					ImageView imageView=new ImageView(imageMap.get(permitTileDTO));
					controllerGUI.getPermitTilesTurnedUpOwned().getChildren().add(imageView);
					imageView.setFitHeight(70);
					imageView.setPreserveRatio(true);
				}
				controllerGUI.getPlayerCoins().setText(String.valueOf(player.getCoins()));
				controllerGUI.getPlayerAssistants().setText(String.valueOf(player.getAssistants().size()));
				controllerGUI.getPlayerNobility().setText(String.valueOf(player.getNobility()));
				controllerGUI.getPlayerScore().setText(String.valueOf(player.getScore()));
				controllerGUI.getPlayersBonuses().getChildren().clear();
				for(BonusTileDTO bonusTileDTO : player.getFinalBonuses()){
					ImageView imageView =new ImageView(imageMap.get(bonusTileDTO));
					controllerGUI.getPlayersBonuses().getChildren().add(imageView);
					imageView.setFitWidth(40);
					imageView.setPreserveRatio(true);
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
		this.disableClickOnRegions(false);
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RegionDTO region=(RegionDTO) this.currentParameter;
		this.currentParameter=null;
		this.disableClickOnRegions(true);
		return region;
	}

	@Override
	public PermitTileDTO askForPermitTile(List<PermitTileDTO> acceptablePermitTiles) {
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		PermitTileDTO permitTile=(PermitTileDTO) this.currentParameter;
		this.currentParameter=null;
		return permitTile;
	}

	@Override
	public CouncillorDTO askForCouncillor(List<CouncillorDTO> acceptableCouncillors) {
		this.disableClickOnCouncillorsInReserve(false);
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		CouncillorDTO councillor=(CouncillorDTO) this.currentParameter;
		this.currentParameter=null;
		this.disableClickOnCouncillorsInReserve(true);
		return councillor;
	}
	
	@Override
	public CouncillorDTO[] askForCouncilBalcony(List<CouncillorDTO[]> acceptableCouncillors) {
		this.disableClickOnCouncilBalconies(false);
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		CouncillorDTO[] councilBalcony=(CouncillorDTO[]) this.currentParameter;
		System.out.println(councilBalcony);
		this.currentParameter=null;
		this.disableClickOnCouncilBalconies(true);
		return councilBalcony;
	}

	@Override
	public CityDTO askForCity(List<CityDTO> acceptableCities) {
		this.disableClickOnCities(false);
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		CityDTO city=(CityDTO) this.currentParameter;
		this.currentParameter=null;
		this.disableClickOnCities(true);
		return city;
	}

	@Override
	public List<PoliticsCardDTO> askForPoliticsCards(List<PoliticsCardDTO> acceptablePoliticsCards) {
		this.disableClickOnPoliticsCards(false);
		List<PoliticsCardDTO> selectedCards=new ArrayList<>();
		while (selectedCards.size()<CouncilBalcony.getNumberofcouncillors()) {
			synchronized (this.controllerGUI) {
				try {
					while (currentParameter==null)
						this.controllerGUI.wait();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (this.currentParameter instanceof String) {
				this.currentParameter=null;
				break;
			}
			else
				selectedCards.add((PoliticsCardDTO) this.currentParameter);
			this.currentParameter=null;
			this.disableClickOnDescardButton(false);
		}
		this.disableClickOnPoliticsCards(true);
		this.disableClickOnDescardButton(true);
		return selectedCards;
	}

	@Override
	public int askForNumberOfPermitTile(List<Integer> acceptableNumberOfPermitTile) {
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int numberOfPermitTile=(int) this.currentParameter;
		this.currentParameter=null;
		return numberOfPermitTile;
	}

	@Override
	public MarketableDTO askForMakingAnOffer(List<MarketableDTO> acceptableObjectsToOffer) {
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		MarketableDTO offeringObject=(MarketableDTO) this.currentParameter;
		this.currentParameter=null;
		return offeringObject;
	}

	@Override
	public int askForPrice() {
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int price=(int) this.currentParameter;
		this.currentParameter=null;
		return price;
	}
	
	@Override
	public boolean askForOtherSelling() {
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		boolean choice=(boolean) this.currentParameter;
		this.currentParameter=null;
		return choice;
	}
	
	@Override
	public OfferDTO askForAcceptingAnOffer(List<OfferDTO> acceptableOffers) {
		synchronized (this.controllerGUI) {
			try {
				while (currentParameter==null)
					this.controllerGUI.wait();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		OfferDTO offer=(OfferDTO) this.currentParameter;
		this.currentParameter=null;
		return offer;
	}

	
	
	
	@Override
	public void ChooseCityBonus(int numberOfCities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PurchasedPermitTileBonus() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	private void disableClickOnRegions(boolean disabled) {
		this.controllerGUI.getSeaRegion().setDisable(disabled);
		this.controllerGUI.getHillRegion().setDisable(disabled);
		this.controllerGUI.getMountainRegion().setDisable(disabled);
	}
	
	private void disableClickOnPermitTilesInHand(boolean disabled) {
		
	}

	private void disableClickOnCouncillorsInReserve(boolean disabled) {
		this.controllerGUI.getCouncillorReserve().get(0).setDisable(disabled);
		this.controllerGUI.getCouncillorReserve().get(1).setDisable(disabled);
		this.controllerGUI.getCouncillorReserve().get(2).setDisable(disabled);
		this.controllerGUI.getCouncillorReserve().get(3).setDisable(disabled);
		this.controllerGUI.getCouncillorReserve().get(4).setDisable(disabled);
		this.controllerGUI.getCouncillorReserve().get(5).setDisable(disabled);
		this.controllerGUI.getCouncillorReserve().get(6).setDisable(disabled);
		this.controllerGUI.getCouncillorReserve().get(7).setDisable(disabled);
	}
	
	private void disableClickOnCouncilBalconies(boolean disabled) {
		this.controllerGUI.getSeaBalcony().setDisable(disabled);
		this.controllerGUI.getHillBalcony().setDisable(disabled);
		this.controllerGUI.getMountainBalcony().setDisable(disabled);
		this.controllerGUI.getKingBalcony().setDisable(disabled);
	}
	
	private void disableClickOnCities(boolean disabled) {
		this.controllerGUI.getArkon().setDisable(disabled);
		this.controllerGUI.getBurgen().setDisable(disabled);
		this.controllerGUI.getCastrum().setDisable(disabled);
		this.controllerGUI.getDorful().setDisable(disabled);
		this.controllerGUI.getEsti().setDisable(disabled);
		this.controllerGUI.getFramek().setDisable(disabled);
		this.controllerGUI.getGraden().setDisable(disabled);
		this.controllerGUI.getHellar().setDisable(disabled);
		this.controllerGUI.getIndur().setDisable(disabled);
		this.controllerGUI.getJuvelar().setDisable(disabled);
		this.controllerGUI.getKultos().setDisable(disabled);
		this.controllerGUI.getLyram().setDisable(disabled);
		this.controllerGUI.getMerkatim().setDisable(disabled);
		this.controllerGUI.getNaris().setDisable(disabled);
		this.controllerGUI.getOsium().setDisable(disabled);
	}
	
	private void disableClickOnPoliticsCards(boolean disabled) {
		for (Object object : controllerGUI.getHand().getChildren()){
			ImageView imageView=(ImageView) object;
			imageView.setDisable(disabled);
		}
	}
	
	private void disableClickOnDescardButton(boolean disabled) {
		this.controllerGUI.getDescardPoliticsCards().setDisable(disabled);
	}
	
	
	
	private void disableClickOnPermitTilesInRegions(boolean disabled) {
		
	}

	
}
