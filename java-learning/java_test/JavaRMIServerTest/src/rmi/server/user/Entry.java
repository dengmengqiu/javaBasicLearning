package rmi.server.user;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Entry {
    public static void main(String[] args) throws RemoteException {
        UserHandler userHandler = null;
        try {
            UserHandlerImpl user = new UserHandlerImpl();
            UserHandler userM = (UserHandler) UnicastRemoteObject.exportObject(user, 0);
            Registry registry = LocateRegistry.createRegistry(2003);
            registry.rebind("user", userHandler);
            System.out.println(" rmi server is ready ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
