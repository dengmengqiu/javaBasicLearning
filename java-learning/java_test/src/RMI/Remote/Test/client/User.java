package RMI.Remote.Test.client;

import RMI.Remote.Test.server.UserHandler;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class User implements Serializable {
    // 与客户端的serialVersionUID字段数据一致
    private static final long serialVersionUID = 42L;
    // setter和getter可以没有
    String name;
    int id;

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", id=" + id + '}';
    }

    public static void main(String[] args){
        try {
            UserHandler handler = (UserHandler) Naming.lookup("user");
            int  count = handler.getUserCount();
            String name = handler.getUserName(1);
            System.out.println("name: " + name);
            System.out.println("count: " + count);
            System.out.println("user: " + handler.getUserByName("lmy86263"));
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
