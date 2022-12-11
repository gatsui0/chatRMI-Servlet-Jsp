package remote.client;

import java.rmi.RemoteException;
import java.util.List;

public interface InterfaceClient {
	public void retrieveMessage(String message) throws RemoteException;
	public void sendMessage(List<String> list) throws RemoteException;
	public String getName() throws RemoteException;
}
