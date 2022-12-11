package remote.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import remote.server.rmi.InterfaceServer;

public class Client extends UnicastRemoteObject implements InterfaceClient {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Client(String name) throws RemoteException, MalformedURLException, NotBoundException {
		super();
		// TODO Auto-generated constructor stub
		this.name = name;
		this.server = (InterfaceServer) Naming.lookup("rmi://localhost:4321/remote");
	}

	public String name;
	public String input = null;
	public String output = null;
	
	public InterfaceServer server;

	@Override
	public void retrieveMessage(String message) throws RemoteException {
		output = output + "\n" + message;
		
	}
	public void sendMessage(List<String> list) throws RemoteException{
		try {
			server.sendMessageToEveryone(name + ": " + input, list);
		} catch(Exception error) {
			error.printStackTrace();
		}
	}
	public String getName() throws RemoteException{
		return name;
	}
}
