package rmi.client.demo1;

import java.io.Serializable;

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
}