package Data.Tree23.structure;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class TestTree23 {
    public static void main(String[] args) throws InterruptedException {
        Tree23 tree = new Tree23();
        tree.insert(new item(13));
        tree.insert(new item(24));
        tree.insert(new item(34));
        tree.insert(new item(23));
        tree.insert(new item(98));
        tree.insert(new item(45));
        tree.insert(new item(75));
        tree.insert(new item(46));
        tree.insert(new item(30));
        tree.insert(new item(12));
        tree.insert(new item(2));
        tree.insert(new item(2));

        tree.levelTraverse();
    }
}

class Tree23{
    private Node23 root = new Node23();

    public void insert(item dataitem){
        Node23 node = root;

        //如果叶子节点是根结点且是满节点
        if(node.isFull() && node.isLeaf()) {
            root = new Node23();
            item mid = insertAndGetMid(node, dataitem);
            if(mid == null){
                return;
            }
            root.insert(0, mid);
            Node23 newNode = new Node23();
            newNode.insert(0, node.removeItem(1));
            root.insertChild(0, node);
            root.insertChild(1, newNode);
            return;
        }

        //如果是非叶节点 一直向下找到叶子节点
        while(!node.isLeaf()){
            node = node.getNextChild(dataitem.getValue());
        }

        //如果叶子节点非满 直接插入
        if(!node.isFull()){
            node.insert(dataitem);
            return;
        }

        //如果叶子节点是满的 比较找出中间项
        item mid = insertAndGetMid(node, dataitem);
        if(mid == null){
            //插入重复值 直接结束
            return;
        }
        //对叶子节点进行分裂
        Node23 newNode = new Node23();
        newNode.insert(node.removeItem(1));

        Node23 parent = node.getParent();

        //从分裂的节点向上插入一处数据项
        while(node != root){
            if(parent.isFull()){
                //父节点也是满的 继续分裂
                // 找到是父节点的那个子节点分裂的
                int splitNodeLoc = getSplitNodeLoc(parent, node);
                mid = insertAndGetMid(parent, mid);
                if(mid == null){
                    return;
                }
                Node23 pNewNode = new Node23();
                pNewNode.insert(parent.removeItem(1));
                if(splitNodeLoc == 0){
                    pNewNode.insertChild(0, parent.removeChild(1));
                    pNewNode.insertChild(1, parent.removeChild(2));
                    node.insertChild(1, newNode);
                }else if(splitNodeLoc == 1){
                    pNewNode.insertChild(0, newNode);
                    pNewNode.insertChild(1, parent.removeChild(2));
                }else if(splitNodeLoc == 2){
                    pNewNode.insertChild(0, parent.removeChild(2));
                    pNewNode.insertChild(1, newNode);
                }
                node = parent;
                newNode = pNewNode;
                parent = node.getParent();
            }else{
                // 父节点非满 将一处数据项直接插入
                parent.insert(mid);
                int splitNodeLoc = getSplitNodeLoc(parent, node);
                if(splitNodeLoc == 0){
                    parent.insertChild(1, newNode);
                }else if(splitNodeLoc == 1){
                    parent.insertChild(2, newNode);
                }
                return;
            }
        }
        //根节点满 则根向上生长
        if(node == root){
            root = new Node23();
            root.insert(mid);
            root.insertChild(0, node);
            root.insertChild(1, newNode);
        }
    }

    public item insertAndGetMid(Node23 node, item dataitem){
        //如果插入了相同的值 返回空 以此作为结束标志
        if(dataitem.getValue() == node.getItem(0).getValue() || dataitem.getValue() == node.getItem(1).getValue()){
            return null;
        }
        if(dataitem.getValue() < node.getItem(0).getValue()){
            item t = node.getItem(0);
            node.insert(0, dataitem);
            return t;
        }else if(dataitem.getValue() < node.getItem(1).getValue()){
            return dataitem;
        }else{
            item t = node.getItem(1);
            node.insert(1, dataitem);
            return t;
        }
    }

    public void levelTraverse() throws InterruptedException {
        if(root == null){return;}
        //利用对列进行层序遍历
        Queue<Node23> que = new ArrayBlockingQueue<Node23>(20);
        ((ArrayBlockingQueue<Node23>) que).put(root);
        while(!que.isEmpty()){
            Node23 node = que.poll();
            node.displayNode();
            if(!node.isLeaf()){
                for(int i = 0; i <= node.getNumItems(); i++){
                    Node23 n = node.getChild(i);
                    if(n !=null){
                        ((ArrayBlockingQueue<Node23>) que).put(node.getChild(i));
                    }
                }
            }
        }
    }

    public int getSplitNodeLoc(Node23 parent, Node23 child){
        for(int i = 0; i <= parent.getNumItems(); i++){
            if(parent.getChild(i) == child){
                return i;
            }
        }
        return -1;
    }
}

class Node23{
    private static int ORDER = 3;
    private Node23 parent;
    private int numItems = 0;
    private item itemArray[] = new item[ORDER-1];
    private Node23 childArray[] = new Node23[ORDER];

    public boolean isFull(){
        return numItems == ORDER-1;
    }

    public void insertChild(int index, Node23 node){
        node.setParent(this);
        childArray[index] = node;
        return;
    }

    public void insert(item dataitem){
        int i;
        for(i = 0; i < numItems; i++){
            if(dataitem.getValue() < itemArray[i].getValue()){
                break;
            }
        }
        for(int j = numItems; j > i; j--){
            itemArray[j] = itemArray[j-1];
        }
        itemArray[i] = dataitem;
        numItems++;
    }

    public void setParent(Node23 node){
        this.parent = node;
    }

    public item removeItem(int index){
        item dataitem = itemArray[index];
        itemArray[index] = null;
        numItems--;
        return dataitem;
    }

    public Node23 getParent(){
        return parent;
    }

    public int getNumItems(){
        return numItems;
    }

    public item getItem(int index){
        return itemArray[index];
    }

    public Node23 getChild(int index){
        return childArray[index];
    }

    public boolean isLeaf(){
        return childArray[0] == null;
    }

    public Node23 getNextChild(int value){
        int i;
        for(i = 0; i < numItems; i++){
            if(value < itemArray[i].getValue()){
                break;
            }
        }
        return childArray[i];
    }

    public int getSplitLoc(Node23 node){
        for(int i = 0; i <= numItems; i++){
            if(childArray[i] == node){
                return i;
            }
        }
        return -1;
    }

    public Node23 removeChild(int index){
        Node23 child = childArray[index];
        childArray[index] = null;
        return child;
    }

    public void insert(int index, item dataitem){
        itemArray[index] = dataitem;
        if(index + 1 > numItems){
            numItems++;
        }
    }

    public void displayNode(){
        for(int i = 0; i < numItems; i++){
            System.out.print(itemArray[i]);
        }
        System.out.println();
    }
}

class item{
    private int value;

    public item(int v){
        value = v;
    }

    public int getValue(){
        return value;
    }

    @Override
    public String toString(){
        return "/" + value;
    }
}


