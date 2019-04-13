package Data.Tree234.structure;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Tree234Code{
    public static void main(String[] args) throws Exception{
        Tree234 tree = new Tree234();
        tree.insert(new DataItem(13));
        tree.insert(new DataItem(24));
        tree.insert(new DataItem(34));
        tree.insert(new DataItem(23));
        tree.insert(new DataItem(98));
        tree.insert(new DataItem(45));
        tree.insert(new DataItem(75));
        tree.insert(new DataItem(46));
        tree.insert(new DataItem(30));
        tree.insert(new DataItem(12));
        tree.insert(new DataItem(2));

        tree.levelTraverse();

//        /13/24/45
//        /2/12
//        /23
//        /30/34
//        /46/75/98
    }
}

class Tree234{
    Node root = new Node();

    public DataItem find(int value){
        Node node = root;
        while(node != null){
            DataItem item = node.findItem(value);
            if(item == null){
                node = node.getNextNode(value);
            }else{
                return item;
            }
        }
        return null;
    }

    public void insert(DataItem item){
        Node node = root;
        while(node != null){
            if(node.isFull()){
                // 当遇到满节点时 进行分裂
                split(node);
                //重新找应该往左还是右子树查找
                node = node.getParent().getNextNode(item.getValue());
            }else if(node.isLeaf()){
                //找到叶子了
                break;
            }else{
                //继续往下找
                node = node.getNextNode(item.getValue());
            }
        }
        node.insertItem(item);
    }

    public void split(Node node){
        DataItem item1 = node.remove(1);
        DataItem item2 = node.remove(2);
        Node child2 = node.disconnect(2);
        Node child3 = node.disconnect(3);

        Node newRight = new Node();
        newRight.insertItem(item2);
        newRight.connect(0, child2);
        newRight.connect(1, child3);

        if(node == root){
            //当根节点满时
           root = new Node();
           root.insertItem(item1);
           root.connect(0, node);
           root.connect(1, newRight);
           node.setParent(root);
           newRight.setParent(root);
        }else {
            //子节点
            int loc = node.getParent().insertItem(item1);
            Node parent = node.getParent();
            int i;
            for(i = node.getParent().getNumItems(); i > loc + 1; i--){
                parent.connect(i, parent.getChild(i-1));
            }
            node.getParent().connect(i, newRight);
            newRight.setParent(parent);
        }
        //可以发现插入时  树是向上长高的
    }

    public void levelTraverse() throws InterruptedException {
        if(root == null){return;}
        //利用对列进行层序遍历
        Queue<Node> que = new ArrayBlockingQueue<Node>(20);
        ((ArrayBlockingQueue<Node>) que).put(root);
        while(!que.isEmpty()){
            Node node = que.poll();
            node.displayNode();
            if(!node.isLeaf()){
                for(int i = 0; i <= node.getNumItems(); i++){
                    ((ArrayBlockingQueue<Node>) que).put(node.getChild(i));
                }
            }
        }
    }
}

class Node{
    private static final int ORDER = 4;
    private int numItems = 0;
    private Node parent;
    private Node childArray[] = new Node[ORDER];
    private DataItem itemArray[] = new DataItem[ORDER-1];

    public DataItem findItem(int value){
        for(DataItem item : itemArray){
           if(item.getValue() == value){
               return item;
           }
        }
        return null;
    }

    public void displayNode(){
        for(int i = 0; i < numItems; i++){
            System.out.print(itemArray[i]);
        }
        System.out.println();
    }

    public int getNumItems(){
        return numItems;
    }

    public Node getChild(int index){
        return childArray[index];
    }

    public DataItem remove(int index){
        DataItem item = itemArray[index];
        itemArray[index] = null;
        numItems--;
        return item;
    }

    public Node disconnect(int index){
        Node node = childArray[index];
        childArray[index] = null;
        return node;
    }

    public void connect(int index, Node node){
        childArray[index] = node;
    }

    public Node getParent(){
        return parent;
    }

    public void setParent(Node node){
        parent = node;
    }

    public boolean isFull(){
        return numItems == 3;
    }

    public int insertItem(DataItem item){
        int i = 0;
        for(; i < numItems; i++){
            if(item.getValue() < itemArray[i].getValue()){
                for(int j = numItems; j > i; j--){
                    itemArray[j] = itemArray[j-1];
                }
                break;
            }else if(item.getValue() == itemArray[i].getValue()){
                return -1;
            }
        }
        itemArray[i] = item;
        numItems++;
        //返回插入节点的位置
        return i;
    }

    public Node getNextNode(int value){
        for(int i = 0; i < numItems; i++){
            if(value < itemArray[i].getValue()){
                return childArray[i];
            }
        }
        return childArray[numItems];
    }

    public boolean isLeaf(){
        return childArray[0] == null;
    }
}

class DataItem{
    private int value;

    public DataItem(int v){
        value = v;
    }

    @Override
    public String toString(){
        return "/" + value;
    }

    public int getValue(){
        return value;
    }
}
