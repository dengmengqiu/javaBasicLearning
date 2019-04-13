package RMI.Remote.Test.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserHandleImpl extends UnicastRemoteObject implements UserHandler {
    // 该构造期必须存在，因为集继承了UnicastRemoteObject类，其构造器要抛出RemoteException
    public UserHandleImpl() throws RemoteException {
        super();
    }

    @Override
    public String getUserName(int id) throws RemoteException {
        return "lmy86263";
    }
    @Override
    public int getUserCount() throws RemoteException{
        return 1;
    }
    @Override
    public User getUserByName(String name) throws RemoteException{
        return new User("lmy86263", 1);
    }

    public static void main(String[] args){
        UserHandler userHandler = null;
        try {
            userHandler = new UserHandleImpl();
            Naming.rebind("user", userHandler);
            System.out.println(" rmi server is ready ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
