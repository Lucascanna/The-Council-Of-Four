package client.view.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import client.view.ClientView;
import client.view.notifies.ClientViewNotify;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ActionWithParameters;
import modelDTO.actionsDTO.AddPlayerDTO;
import modelDTO.actionsDTO.QuitDTO;
import modelDTO.clientNotifies.ClientNotify;
import modelDTO.clientNotifies.EndGameDTONotifies;
import modelDTO.parser.Parser;


public class CLIsocket extends ClientView implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8153931800059106395L;
	private final Parser parser;
	private transient final Scanner scanner;
	private transient final Socket socket;
	private transient final ObjectOutputStream socketOut;
	private transient final ObjectInputStream socketIn;
	
	public CLIsocket(Parser parser, Socket socket) throws IOException {
		
		this.parser=parser;
		this.socket=socket;
		this.socketOut=new ObjectOutputStream(socket.getOutputStream());
		this.socketIn=new ObjectInputStream(socket.getInputStream());
		this.scanner=new Scanner(System.in);
	}
	
	public Scanner getScanner() {
		return this.scanner;
	}
	
	

	public ObjectOutputStream getSocketOut() {
		return socketOut;
	}

	@Override
	public void update(ClientViewNotify notify) {
		notify.stamp(scanner);
	}
	
	@Override
	public void input() throws IOException {
		String input="";
		while (!"quit".equals(input)) {
			try{
				input=this.scanner.nextLine();
			}catch(IllegalStateException e){
				break;
			}
			if (this.parser.availableActions().contains(input)) {
				ActionDTO selectedAction=this.parser.actionParser(input);
				if (this.parametersNeeded(selectedAction)) {
					ActionWithParameters actionWithParameters=(ActionWithParameters) selectedAction;
					this.insertParametersAndSend(actionWithParameters);
				}
				else
					sendAction(selectedAction);
			}
			
			else if("quit".equals(input))
				
				sendAction(new QuitDTO(this));
			
				else
					System.out.println("Sorry, action not available!");	
		}
	}
	
	private boolean parametersNeeded(ActionDTO selectedAction) {
		return (selectedAction instanceof ActionWithParameters);		
	}
	
	private void insertParametersAndSend(ActionWithParameters selectedAction) {
		this.parser.parametersParser(selectedAction);
		if (selectedAction.checkIfParametersSetted()){
			sendAction(selectedAction);
		}
	}
	
	private void sendAction(ActionDTO action){
		try {
			
			socketOut.writeObject(action);
			socketOut.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void welcome(String name){
		AddPlayerDTO actionDTO=new AddPlayerDTO();
		actionDTO.setPlayerName(name);
		sendAction(actionDTO);
	}

	@Override
	public void run() {
		Object object=null;
		while (true){
			try {
				
				object = socketIn.readObject();
				
			} catch (ClassNotFoundException | IOException e) {
				try {
					socket.close();
					scanner.close();
					break;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(object instanceof EndGameDTONotifies)
				System.out.println("GAME OVER\n FINAL RANKING TABLE: \n"+((EndGameDTONotifies)object).getPlayers());
			else{
				ClientNotify clientNotify=(ClientNotify) object;
				this.notifyObserver(clientNotify);
			}
		}
	}

}
