 package client.view;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import client.connections.Connection;
import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsDTO.ChatMessageDTO;
import client.modelDTO.actionsDTO.ChooseMapDTO;
import client.modelDTO.actionsDTO.QuitDTO;
import client.modelDTO.gameTableDTO.CardColourDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.CouncillorDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.modelDTO.gameTableDTO.PoliticsCardDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.modelDTO.marketDTO.MarketableDTO;
import client.modelDTO.marketDTO.OfferDTO;
import client.view.notifies.ClientGameOverNotify;
import client.view.notifies.ClientViewNotify;
import server.model.gameTable.CouncilBalcony;

public class CLI extends ClientView {

	private final Scanner scanner;

	public CLI(Connection connection, GameDTO clientGame) {
		super(connection, clientGame);
		this.scanner=new Scanner(System.in);
	}
	
	
	/**
	 * This method gets from the current state of play the list of available actions and convert them to strings,
	 * then cuts the initials of available actions to let the user choose more easily
	 * @return the list of strings of initials
	 */
	public List<String> availableActions() {
		List<String> acceptableStrings=new ArrayList<>();
		for (ActionDTO action : this.clientGame.getAvailableActions())
			acceptableStrings.add(action.toString().substring(0,2));
		return acceptableStrings;
	}
	
	/**
	 * This method reads the string inserted by the user and returns the corresponding action
	 * @param selectedAction is the sigle of the action inserted by the user
	 * @return the corresponding available action
	 */
	private ActionDTO actionParser(String selectedAction) {
		for (ActionDTO availableAction : this.clientGame.getAvailableActions())
			if (selectedAction.equals(availableAction.toString().substring(0,2)))
				return availableAction;
		return null;
	}
	
	
	@Override
	public void input() throws RemoteException {
		String input="";
		while (!"quit".equals(input)) {
			try {
				input=this.scanner.nextLine();
			} catch (IllegalStateException e){
				return;
			}
			if (this.availableActions().contains(input)) {
				ActionDTO selectedAction=this.actionParser(input);
				if (selectedAction instanceof ActionWithParameters) {
					ActionWithParameters actionWithParameters=(ActionWithParameters) selectedAction;
					this.insertParametersAndSend(actionWithParameters);
				}
				else
					connection.sendAction(selectedAction);
			}
			else if (input.startsWith("chat:"))
				connection.sendAction(new ChatMessageDTO(this.clientGame.getClientPlayer().getName() 
						+ ": " + input.substring(5)));
			else if ("quit".equals(input))
				connection.sendAction(new QuitDTO());
			else
				println("Sorry, action not available!");	
		}		
	}
	
	/**
	 * Inserts the parameters of the selected action, then sends it to the server 
	 * using the appropriate connection
	 * @param actionWithParameters is the action whose parameters has to be set
	 * @throws RemoteException if something goes wrong with the RMI connection
	 */
	private void insertParametersAndSend(ActionWithParameters actionWithParameters) throws RemoteException {
		actionWithParameters.setParser().setParameters(this, this.clientGame);
		if (actionWithParameters.checkIfParametersSet())
			connection.sendAction(actionWithParameters);
	}
	
	

	@Override
	public void update(ClientViewNotify notify) {
		notify.updateView(this);
		if (notify instanceof ClientGameOverNotify)
			scanner.close();
	}
	
	/**
	 * Prints a generic game update or message using System.out
	 * @param message
	 */
	public void println(String message){
		System.out.println(message);
	}
	

	@Override
	public void displayMessage(String message) {
		println(message);
	}
	
	@Override
	public void displayError(String error) {
		println(error);
	}

	@Override
	public void displayAvailableActions() {
		println(this.clientGame.getAvailableActions().toString());
	}

	@Override
	public void displayGameTable() {
		println(this.clientGame.getClientGameTable().toString());
	}

	@Override
	public void displayPlayer() {
		println(this.clientGame.getClientPlayer().toString());
	}

	@Override
	public void displayMarket() {
		println(this.clientGame.getMarket().toString());
	}
	
	@Override
	public void displayFinalRanking() {
		println("GAME OVER\n FINAL RANKING TABLE: \n" + 
				this.clientGame.getClientGameTable().getClientPlayers());
	}
	
	@Override
	public void displayChatMessage(String message) {
		println(message);
	}
	
	@Override
	public void startMarket() {
		println("Market is started!");
	}
	
	@Override
	public void closeMarket() {
		println("Market is finished!");
	}
	
	
	@Override
	public RegionDTO askForRegionBoard() {
		List<String> acceptableRegionNames=this.clientGame.getClientGameTable().getClientRegions().stream()
                .map(RegionDTO::getName)
                .collect(Collectors.toCollection(ArrayList::new));
		println(acceptableRegionNames.toString());
		String regionToTranslate=this.scanner.nextLine();
		while (!acceptableRegionNames.contains(regionToTranslate)) {
			this.displayError("Wrong parameter. Try again");
			regionToTranslate=scanner.nextLine();
		}
		for (RegionDTO region : clientGame.getClientGameTable().getClientRegions())
			if (regionToTranslate.equals(region.getName()))
				return region;
		throw new IllegalArgumentException("regionToTranslate is not a colour name");
	}

	@Override
	public PermitTileDTO askForPermitTile() {
		List<String> indexes=new ArrayList<>();
		List<String> permitTilesWithIndex=new ArrayList<>();
		int i=1;
		for (PermitTileDTO permitTile : this.clientGame.getClientPlayer().getAvailablePermitTiles()) {
			indexes.add(Integer.toString(i));
			permitTilesWithIndex.add(i+": "+permitTile.toString());
			i++;
		}
		println(permitTilesWithIndex.toString());
		String permitTileToTranslate=scanner.nextLine();
		while (!indexes.contains(permitTileToTranslate)) {
			this.displayError("Wrong parameter. Try again");
			permitTileToTranslate=scanner.nextLine();
		}
		return this.clientGame.getClientPlayer().getAvailablePermitTiles().get
				(Integer.parseInt(permitTileToTranslate)-1);
	}

	@Override
	public CouncillorDTO askForCouncillor() {
		List<String> acceptableCouncillorsColours=this.clientGame.getClientGameTable().getClientCouncillorReserve().stream()
            .map(CouncillorDTO::getColour).map(CardColourDTO::getName)
            .collect(Collectors.toCollection(ArrayList::new));
		println(acceptableCouncillorsColours.toString());
		String newCouncillorToTranslate=this.scanner.nextLine();
		while (!acceptableCouncillorsColours.contains(newCouncillorToTranslate)) {
			this.displayError("Wrong parameter. Try again");
			newCouncillorToTranslate=scanner.nextLine();
		}
		for (CouncillorDTO councillor : this.clientGame.getClientGameTable().getClientCouncillorReserve())
			if (newCouncillorToTranslate.equals(councillor.getColour().getName()))
				return councillor;
		throw new IllegalArgumentException("newCouncillorToTranslate is not a colour name");
	}
	
	@Override
	public CouncillorDTO[] askForCouncilBalcony() {
		List<String> acceptableCouncilBalconiyNames=Arrays.asList("Sea", "Hill", "Mountain", "King balcony");
		println(acceptableCouncilBalconiyNames.toString());
		String councilBalconyToTranslate=this.scanner.nextLine();
		while (!acceptableCouncilBalconiyNames.contains(councilBalconyToTranslate)) {
			this.displayError("Wrong parameter. Try again");
			councilBalconyToTranslate=scanner.nextLine();
		}
		if (councilBalconyToTranslate.equals(acceptableCouncilBalconiyNames.get(0)))
			return this.clientGame.getClientGameTable().getClientRegions().get(0).getBalcony();
		if (councilBalconyToTranslate.equals(acceptableCouncilBalconiyNames.get(1)))
			return this.clientGame.getClientGameTable().getClientRegions().get(1).getBalcony();
		if (councilBalconyToTranslate.equals(acceptableCouncilBalconiyNames.get(2)))
			return this.clientGame.getClientGameTable().getClientRegions().get(2).getBalcony();
		if (councilBalconyToTranslate.equals(acceptableCouncilBalconiyNames.get(3)))
			return this.clientGame.getClientGameTable().getClientKingBalcony();
		throw new IllegalArgumentException("councilBalconyToTranslate is not a valid balcony");
	}

	@Override
	public CityDTO askForCity(List<CityDTO> acceptableCities) {
		List<String> acceptableCityNames=acceptableCities.stream()
                .map(CityDTO::getName)
                .collect(Collectors.toCollection(ArrayList::new));
		println(acceptableCityNames.toString());
		String cityToTranslate="";
		cityToTranslate=this.scanner.nextLine();
			while (!acceptableCityNames.toString().contains(cityToTranslate)) {
				this.displayError("Wrong parameter. Try again");
				cityToTranslate=scanner.nextLine();
			}
		for (CityDTO city : acceptableCities)
			if (cityToTranslate.equals(city.getName()))
				return city;
		throw new IllegalArgumentException("cityToTranslate is not a city name");
	}

	@Override
	public List<PoliticsCardDTO> askForPoliticsCards() {
		List<String> acceptableCardsColours=this.clientGame.getClientPlayer().getHand().stream()
                .map(PoliticsCardDTO::getColour).map(CardColourDTO::getName)
                .collect(Collectors.toCollection(ArrayList::new));
		println(acceptableCardsColours.toString());
		String cardsToTranslate=scanner.nextLine();
		StringTokenizer cardsToTranslateTokenized=new StringTokenizer(cardsToTranslate);
		while (!this.checkCards(cardsToTranslate, acceptableCardsColours)) {
			cardsToTranslate=scanner.nextLine();
			cardsToTranslateTokenized=new StringTokenizer(cardsToTranslate);
		}
		List<PoliticsCardDTO> cardsTranslated=new ArrayList<>();
		while (cardsToTranslateTokenized.hasMoreTokens()) {
			String currentCard=cardsToTranslateTokenized.nextToken();
			for (PoliticsCardDTO cardTranslated : this.clientGame.getClientPlayer().getHand())
				if (cardTranslated.getColour().getName().equals(currentCard)) {
					cardsTranslated.add(cardTranslated);
					break;
				}
		}
		return cardsTranslated;
	}
	
	private boolean checkCards(String cardsToTranslate, List<String> acceptableCardsColours) {
		List<String> temporaryAcceptablePoliticsCards=new ArrayList<>(acceptableCardsColours);
		StringTokenizer cardsToCheckTokenized=new StringTokenizer(cardsToTranslate);
		if (!(cardsToCheckTokenized.countTokens()>0 && cardsToCheckTokenized.countTokens()<=CouncilBalcony.getNumberofcouncillors())) {
			this.displayError("Remember: you must descard at least 1 card and a maximum of "+ CouncilBalcony.getNumberofcouncillors() +" cards");
			return false;
		}
		while (cardsToCheckTokenized.hasMoreTokens()) {
			String currentCard=cardsToCheckTokenized.nextToken();
			if (temporaryAcceptablePoliticsCards.contains(currentCard))
				temporaryAcceptablePoliticsCards.remove(currentCard);
			else {
				this.displayError("Wrong cards. Try again");
				return false;
			}
		}
		return true;
	}

	@Override
	public int askForNumberOfPermitTile(RegionDTO selectedRegion) {
		println("[0, 1]");
		String numberOfPermitTileToTranslate=this.scanner.nextLine();
		while (!("0".equals(numberOfPermitTileToTranslate) || "1".equals(numberOfPermitTileToTranslate))) {
			this.displayError("Wrong parameter. Try again");
			numberOfPermitTileToTranslate=scanner.nextLine();
		}
		return Integer.parseInt(numberOfPermitTileToTranslate);
	}

	@Override
	public MarketableDTO askForMakingAnOffer(List<MarketableDTO> acceptableObjectsToOffer) {
		List<String> indexes=new ArrayList<>();
		List<String> offersWithIndex=new ArrayList<>();
		int i=1;
		for (MarketableDTO offeringObject : acceptableObjectsToOffer) {
			indexes.add(Integer.toString(i));
			offersWithIndex.add(i+": "+offeringObject.toString());
			i++;
		}
		System.out.println(offersWithIndex);
		String offeringObjectToTranslate=scanner.nextLine();
		while (!indexes.contains(offeringObjectToTranslate)) {
			this.displayError("Wrong parameter. Try again");
			offeringObjectToTranslate=scanner.nextLine();
		}
		return acceptableObjectsToOffer.get(Integer.parseInt(offeringObjectToTranslate)-1);
	}
	
	@Override
	public int askForPrice() {
		String price=this.scanner.nextLine();
		return Integer.parseInt(price);
	}
	
	@Override
	public boolean askForOtherSelling() {
		println("[yes, no])");
		String input=this.scanner.nextLine();
		while (!("yes".equals(input) || "no".equals(input))) {
			this.displayError("I didn't understand...");
			input=this.scanner.nextLine();
		}
		if ("yes".equals(input))
			return true;
		else if ("no".equals(input))
			return false;
		else
			throw new NullPointerException("Wrong input");
	}

	@Override
	public OfferDTO askForAcceptingAnOffer() {
		List<String> indexes=new ArrayList<>();
		List<String> offersWithIndex=new ArrayList<>();
		int i=1;
		for (OfferDTO offer : this.clientGame.getMarket().getOffersList()) {
			indexes.add(""+i);
			offersWithIndex.add(i+": "+offer.toString());
			i++;
		}
		System.out.println(offersWithIndex);
		String offerToTranslate=scanner.nextLine();
		while (!indexes.contains(offerToTranslate)) {
			this.displayError("Wrong parameter. Try again");
			offerToTranslate=scanner.nextLine();
		}
		return this.clientGame.getMarket().getOffersList().get(Integer.parseInt(offerToTranslate)-1);
	}
	
	@Override
	public PermitTileDTO askForPermitTileUncoveredAndCovered() {
		List<PermitTileDTO> availablePermitTiles=new ArrayList<>();
		availablePermitTiles.addAll(this.clientGame.getClientPlayer().getAvailablePermitTiles());
		availablePermitTiles.addAll(this.clientGame.getClientPlayer().getCoveredPermitTiles());
		List<String> indexes=new ArrayList<>();
		List<String> permitTilesWithIndex=new ArrayList<>();
		int i=1;
		for (PermitTileDTO permitTile : availablePermitTiles) {
			indexes.add(Integer.toString(i));
			permitTilesWithIndex.add(i+": "+permitTile.toString());
			i++;
		}
		System.out.println(permitTilesWithIndex);
		String permitTileToTranslate=scanner.nextLine();
		while (!indexes.contains(permitTileToTranslate)) {
			this.displayError("Wrong parameter. Try again");
			permitTileToTranslate=scanner.nextLine();
		}
		return availablePermitTiles.get(Integer.parseInt(permitTileToTranslate)-1);
	}


	@Override
	public void askForMap() throws RemoteException {
		this.displayMessage("Choosing a random map...");
		this.connection.sendAction(new ChooseMapDTO(1));
	}


	@Override
	public void startGame() {
		return;
	}


}
