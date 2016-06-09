package client;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;


public class Client {

	public static void main(String[] args) throws NotBoundException, UnknownHostException, IOException {
		String connection="";
		String name="";
		Scanner scanner=new Scanner(System.in);
		System.out.println("Welcome to CoF, please enter your name!");
		name=scanner.nextLine();
		System.out.println("Hi, "+name+" do you want to use Socket or RMI?");
		while(!"Socket".equals(connection) && !"RMI".equals(connection)){
			connection=scanner.nextLine();
			if(!"Socket".equals(connection) && !"RMI".equals(connection))
				System.out.println("Wrong input. Try again.");
		}
		System.out.println("Please insert the server address");
		while(true){
			String ip=scanner.nextLine();
			try{
				if("RMI".equals(connection)){
					ClientRMI clientRMI=new ClientRMI(ip,name);
					clientRMI.startClient();
					break;
				}
				else{
					ClientSocket clientSocket=new ClientSocket(ip,name);
					clientSocket.startClient();
					break;
				}
			}catch(SocketException | RemoteException e){
				System.out.println("Server Unreachable. Try again");
			}
		}
		scanner.close();
	}

}
