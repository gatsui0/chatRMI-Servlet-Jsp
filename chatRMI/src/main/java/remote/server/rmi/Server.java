package remote.server.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import remote.client.InterfaceClient;

public class Server extends UnicastRemoteObject implements InterfaceServer {

	private static final long serialVersionUID = 1L;
	private final ArrayList<InterfaceClient> clients;
	
	public Server() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		this.clients = new ArrayList<>();
	}
	
	public synchronized void sendMessageToEveryone(String message, List<String> list) throws RemoteException {
        if(list.isEmpty()){
            int i= 0;
            while (i < clients.size()){
                clients.get(i++).retrieveMessage(message);
            }
        }else{
            for (InterfaceClient client : clients) {
                for(int i=0;i<list.size();i++){
                    if(client.getName().equals(list.get(i))){
                        client.retrieveMessage(message);
                    }
                }
            }
	}
	}
	@Override
    public void addClient(InterfaceClient client) throws RemoteException {
        this.clients.add(client);
    }
	public List<String> getNameClients() throws RemoteException{
		
		if(clients.isEmpty()) return null;
		
		List<String> clientsOn = new ArrayList<>();
		for (InterfaceClient client : clients) {
			clientsOn.add(client.getName());
		}
		
		return clientsOn;
	}
}

