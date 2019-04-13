package Data.HuffTree.structure;

import java.util.*;

public class testHuffTree{
    HuffCodeTree tree = new HuffCodeTree();

    public static void main(String[] args){
        String str = "cwdav casdc csadcadav casdc csadcadav casdc csadcadav casdc csadcadav casdc csadcadav casdc csadca" +
                "cwdav casdc csadcadav casdc csadcadav casdc csadcadav casdc csadcadav casdc csadcadav casdc csadca";
        testHuffTree huffcode = new testHuffTree();
        long start = System.currentTimeMillis();
        String encode = huffcode.encode(str);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(encode);
        System.out.println(huffcode.decode(encode));
    }

    public String encode(String str) {
        char[] chars = str.toCharArray();

        // 首先整理一遍所有字符 统计他们出现的频率 也就是个字节点的权重
        Map<Character, Integer> charsInfoMap = countCharFrequence(chars);

        // 根据权重由小到大的顺序排列各结点 采用用优先级队列
        PriorityQueue<Node> nodeQue = arrangeByFre(charsInfoMap);

        // 通过这些节点 来创建哈夫曼树  得到所有字符的编码信息
        buildHuffCodeTree(nodeQue);

        //使用对所有的字符进行编码
        return this.tree.encode(chars);
    }

    public String decode(String str){
        return this.tree.decode(str);
    }

    public Map<Character, Integer> countCharFrequence(char[] chars){
        // 用键值对来存放字符以及其出现的频率
        Map<Character, Integer> charsInfoMap = new Hashtable<Character, Integer>();
        for (char c : chars) {
            Character cs = new Character(c);
            if(charsInfoMap.containsKey(cs)) {
                charsInfoMap.put(cs, charsInfoMap.get(cs) + 1);
            } else {
                charsInfoMap.put(cs, new Integer(1));
            }
        }

//        for(Character c : charsInfoMap.keySet()){
//            System.out.println(c + " 的权重: " + charsInfoMap.get(c).toString());
//        }
        return charsInfoMap;
    }

    public PriorityQueue<Node> arrangeByFre(Map<Character, Integer> charsInfoMap){
        //通过优先队列 有序存放节点   当然节点必须重写compareTo方法
        PriorityQueue<Node> nodeQue= new PriorityQueue<>(charsInfoMap.size());
        for (Character key : charsInfoMap.keySet()) {
            Node node = new Node(key.toString(), charsInfoMap.get(key));
            nodeQue.add(node);
            this.tree.addleaf(node);
        }

//       for(Node node : nodeQue){
////           System.out.println(node.chars + "权重 ： " + node.frequence);
//       }
        return nodeQue;
    }

    public void buildHuffCodeTree(PriorityQueue<Node> nodeQue){
        if(nodeQue == null || nodeQue.size() == 0){
            return;
        }

        //构造哈夫曼树 小的节点为左子树 大的节点为右
        while(nodeQue.size() > 1){
            Node node1 = nodeQue.poll();
            Node node2 = nodeQue.poll();
//            System.out.print(node1.chars + "权重 ： " + node1.frequence + "       ");
//            System.out.println(node2.chars + "权重 ： " + node2.frequence);
            Node parent = new Node(node1.chars + node2.chars,
                node1.frequence + node2.frequence);
            parent.leftChild = node1;
            parent.rightChild = node2;

            node1.parentChild = parent;
            node2.parentChild = parent;

            nodeQue.add(parent);
        }
        this.tree.root = nodeQue.poll();
    }
}

class HuffCodeTree{
    Node root = null;
    /**
     * 所有叶子节点 即所有进行编码的字符所在的节点
     * */
    public List<Node> leaves = new LinkedList<>();

    public String encode(char[] chars){
        if(chars.equals("")){return "";}

        // 对所有出现的字符进行编码
        Map<Character, String> chara_encoding = new HashMap<>();
        for(Node leaf : this.leaves){
            Node node = leaf;
            String charcode = "";
            while(node != root) {
                if (node.isLeftChild()) {
                    charcode = '0' + charcode;
                } else {
                    charcode = '1' + charcode;
                }
                node = node.parentChild;
            }
//            System.out.print(leaf.chars+ " : " + charcode + "  ");
            chara_encoding.put(leaf.chars.charAt(0), charcode);
        }

        StringBuffer code = new StringBuffer();
        for(char c : chars){
            code.append(chara_encoding.get(c));
        }
        return code.toString();
    }

    public void addleaf(Node e){
        Node node = e;
        this.leaves.add(node);
    }

//    public Node seekNode(char c){
//        for(Node node : leaves){
//            if(node.chars.charAt(0) == c){
//                return node;
//            }
//        }
//        return null;
//    }

    public void preOrderTra(){
        preOrderTra(root);
    }

    //递归 先序遍历
    public void preOrderTra(Node localRoot) {
        if(localRoot != null) {
            localRoot.displayNode();
            preOrderTra(localRoot.leftChild);
            preOrderTra(localRoot.rightChild);
        }
    }

    public String decode(String str){
        if(root == null){
            return null;
        }
        String deocodeString = "";
        Node node = root;
        for(char c : str.toCharArray()){
            if(node.leftChild == null && node.rightChild == null){
                deocodeString += node.chars;
                node = root;
            }
            if(c == '0'){
                node = node.leftChild;
            }else if(c == '1') {
                node = node.rightChild;
            }
        }
        return deocodeString;
    }
}

class Node implements Comparable<Node>{
    public String chars = "";
    public int frequence;
    public Node leftChild;
    public Node rightChild;
    public Node parentChild;

    public Node(String str, int fre){
        this.chars = str;
        this.frequence = fre;
    }

    public void displayNode(){
        System.out.println("节点： " + chars + " " + frequence);
    }

//    @Override
//    public boolean equals(Object obj) {
//        return this.chars == ((Node)obj).chars;
//    }

    public boolean isLeftChild(){
        return this.parentChild != null && this.parentChild.leftChild == this;
    }

    @Override
    public int compareTo(Node o) {
        return this.frequence - o.frequence;
    }
}