package remote.server.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import remote.client.InterfaceClient;

public interface InterfaceServer extends Remote {

	public void sendMessageToEveryone(String message, List<String> list) throws RemoteException;
	public void addClient(InterfaceClient client) throws RemoteException;
	public List<String> getNameClients() throws RemoteException;
}
