package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.AskActionPack;
import controller.AskParameterPack;
import controller.ErrorSignal;
import controller.GameLogic;
import model.Game;
import observerPattern.Observable;

public class CLI extends View{

	private final Scanner scanner;
	
	public CLI(GameLogic gameLogic, Scanner scanner) {
		super(gameLogic);
		this.scanner=scanner;
	}

	@Override
	public <C> void update(Observable o, C change) {
		if(change instanceof AskActionPack){
			AskActionPack notify=(AskActionPack) change;
			stamp(notify.getGame());
			//richiesta all'utente dell'azione
			System.out.println("Player "+notify.getGame().getCurrentPlayer().getName()+
					", it's your turn!\n Choose one of the following actions:\n ");
			for(String action : notify.getAcceptableString())
				System.out.println(action);
			String selectedAction=scanner.nextLine();
			while(!notify.cutStrings().contains(selectedAction)){
				System.out.println("You have chosen a wrong action. Retry");
				selectedAction=scanner.nextLine();
				}
			//preparo give action pack
			GiveActionPack gap=new GiveActionPack(selectedAction);
			
			//invio give action pack
			notifyObservers(gap);							
		}
		
		
		if(change instanceof AskParameterPack){
			AskParameterPack notify=(AskParameterPack) change;
			//chiedo all'utente di inserire i parametri e li salvo in una lista
			List<String> selectedParameters=new ArrayList<String>();
			for(int i=0; i<notify.getParameters().size(); i++){
				System.out.println("Insert Parameter: "+notify.getParameters().get(i)+
						". Choose among:\n");
				for(String possibleString : notify.getAcceptableParameters().get(i))
					System.out.print(possibleString + "\t");
				String parameter=scanner.nextLine();
				while(!notify.getAcceptableParameters().get(i).contains(parameter)){
					System.out.println("Wrong parameter. Retry.");
					parameter=scanner.nextLine();
				}
				selectedParameters.add(parameter);
			}		
			//preparo pacchetto
			GiveParameterPack gpp = new GiveParameterPack(selectedParameters);
			notifyObservers(gpp);
		}
		
		if(change instanceof ErrorSignal)
			System.out.println("Sorry, but you cannot do this action.\n"
					+ "Check well your game state before choosing action\n\n");
	}
	
	/**
	 * TODO
	 */
	public void stamp(Game game){
		System.out.println(game);
	}


}