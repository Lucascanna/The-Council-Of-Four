package client.view.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import client.view.Connection;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.clientNotifies.ClientNotify;


public class SocketConnection extends Connection implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7760277542526033095L;
	private transient final Socket socket;
	private transient final ObjectOutputStream socketOut;
	private transient final ObjectInputStream socketIn;
	
	public SocketConnection(Socket socket) throws IOException {
		
		this.socket=socket;
		this.socketOut=new ObjectOutputStream(socket.getOutputStream());
		this.socketIn=new ObjectInputStream(socket.getInputStream());
	} 

	public ObjectOutputStream getSocketOut() {
		return socketOut;
	}
	
	@Override
	public void sendAction(ActionDTO action){
		try {
			
			socketOut.writeObject(action);
			socketOut.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
					break;
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
			ClientNotify clientNotify=(ClientNotify) object;
			this.notifyObserver(clientNotify);
		}
	}

}