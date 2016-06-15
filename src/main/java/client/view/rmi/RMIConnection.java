package client.view.rmi;

import java.rmi.RemoteException;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.AddPlayerDTO;
import client.modelDTO.actionsDTO.QuitDTO;
import client.modelDTO.actionsDTO.QuitDTORMI;
import client.modelDTO.clientNotifies.ClientNotify;
import client.view.Connection;
import server.view.RMIViewRemote;

public class RMIConnection extends Connection implements ClientRMIViewRemote{
	

	private final RMIViewRemote serverStub;
	
	public RMIConnection(RMIViewRemote serverStub) {
		this.serverStub=serverStub;
	}

	
	@Override
	public void updateClient(ClientNotify clientNotify) throws RemoteException {
		this.notifyObserver(clientNotify);		
	}
	
	@Override
	public void sendAction(ActionDTO action) throws RemoteException{
		if(action instanceof QuitDTO)
			this.serverStub.receiveAction(new QuitDTORMI(this));
		else if(action instanceof AddPlayerDTO)
			this.serverStub.registerClient(this, ((AddPlayerDTO)action).getPlayerName());
		else
			this.serverStub.receiveAction(action);
	}
	
}
