package rmi.client.demo1;

import rmi.server.user.UserHandler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientEntry {
    public static void main(String []args){
        try {
            Registry registry = LocateRegistry.getRegistry("localhost",2004);
            UserHandler handler = (UserHandler) registry.lookup("user");
            int  count = handler.getUserCount();
            String name = handler.getUserName(1);
            System.out.println("name: " + name);
            System.out.println("count: " + count);
            System.out.println("user: " + handler.getUserByName("lmy86263"));
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
