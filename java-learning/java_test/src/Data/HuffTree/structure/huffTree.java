package Data.HuffTree.structure;

import java.nio.charset.Charset;
import java.util.*;

public class huffTree{
    public static void main(String[] args){
        String oriStr = "cwdav casdc csadcadav casdc csadcadav casdc csadcadav casdc csadcadav casdc csadcadav casdc csadca" +
                "cwdav casdc csadcadav casdc csadcadav casdc csadcadav casdc csadcadav casdc csadcadav casdc csadca";
        long start = System.currentTimeMillis();
        Map<Character, Integer> statistics = statistics(oriStr.toCharArray());
        String encodedBinariStr = encode(oriStr, statistics);
        System.out.println(encodedBinariStr);
        System.out.println(System.currentTimeMillis() - start);
        String decodedStr = decode(encodedBinariStr, statistics);
        System.out.println(decodedStr);
    }

    public static Map<Character, Integer> statistics(char[] charArray) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : charArray) {
            Character character = new Character(c);
            if (map.containsKey(character)) {
                map.put(character, map.get(character) + 1);
            } else {
                map.put(character, 1);
            }
        }

        return map;
    }

    public static String encode(String originalStr,
                                Map<Character, Integer> statistics) {
        if (originalStr == null || originalStr.equals("")) {
            return "";
        }

        char[] charArray = originalStr.toCharArray();
        List<TNode> leafNodes = new ArrayList<TNode>();
        buildTree(statistics, leafNodes);
        Map<Character, String> encodInfo = buildEncodingInfo(leafNodes);

        StringBuffer buffer = new StringBuffer();
        for (char c : charArray) {
            Character character = new Character(c);
            buffer.append(encodInfo.get(character));
        }

        return buffer.toString();
    }

    private static Tree buildTree(Map<Character, Integer> statistics,
                                  List<TNode> leafs) {
        Character[] keys = statistics.keySet().toArray(new Character[0]);
        PriorityQueue<TNode> priorityQueue = new PriorityQueue<TNode>();
        for (Character character : keys) {
            TNode node = new TNode();
            node.chars = character.toString();
            node.frequence = statistics.get(character);
            priorityQueue.add(node);
            leafs.add(node);
        }

        int size = priorityQueue.size();
        for (int i = 1; i <= size - 1; i++) {
            TNode node1 = priorityQueue.poll();
            TNode node2 = priorityQueue.poll();
//            System.out.print(node1.chars + "权重 ： " + node1.frequence + "       ");
//            System.out.println(node2.chars + "权重 ： " + node2.frequence);
            TNode sumNode = new TNode();
            sumNode.chars = node1.chars + node2.chars;
            sumNode.frequence = node1.frequence + node2.frequence;

            sumNode.leftNode = node1;
            sumNode.rightNode = node2;

            node1.parent = sumNode;
            node2.parent = sumNode;

            priorityQueue.add(sumNode);
        }

        Tree tree = new Tree();
        tree.root = priorityQueue.poll();
        return tree;
    }

    private static Map<Character, String> buildEncodingInfo(List<TNode> leafNodes) {
        Map<Character, String> codewords = new HashMap<Character, String>();
        for (TNode leafNode : leafNodes) {
            Character character = new Character(leafNode.getChars().charAt(0));
            String codeword = "";
            TNode currentNode = leafNode;

            do {
                if (currentNode.isLeftChild()) {
                    codeword = "0" + codeword;
                } else {
                    codeword = "1" + codeword;
                }

                currentNode = currentNode.parent;
            } while (currentNode.parent != null);

            System.out.println(character + " : " + codeword + " ");
            codewords.put(character, codeword);
        }

        return codewords;
    }

    public static String decode(String binaryStr,
                                Map<Character, Integer> statistics) {
        if (binaryStr == null || binaryStr.equals("")) {
            return "";
        }

        char[] binaryCharArray = binaryStr.toCharArray();
        LinkedList<Character> binaryList = new LinkedList<>();
        int size = binaryCharArray.length;
        for (int i = 0; i < size; i++) {
            binaryList.addLast(new Character(binaryCharArray[i]));
        }

        List<TNode> leafNodes = new ArrayList<TNode>();
        Tree tree = buildTree(statistics, leafNodes);

        StringBuffer buffer = new StringBuffer();

        while (binaryList.size() > 0) {
            TNode node = tree.root;

            do {
                Character c = binaryList.removeFirst();
                if (c.charValue() == '0') {
                    node = node.leftNode;
                } else {
                    node = node.rightNode;
                }
            } while (!node.isLeaf());

            buffer.append(node.chars);
        }

        return buffer.toString();
    }
}

class Tree {
    TNode root;

    public TNode getRoot() {
        return root;
    }

    public void setRoot(TNode root) {
        this.root = root;
    }
}

class TNode implements Comparable<TNode> {
    String chars = "";
    int frequence = 0;
    TNode parent;
    TNode leftNode;
    TNode rightNode;

    @Override
    public int compareTo(TNode n) {
        return frequence - n.frequence;
    }

    public boolean isLeaf() {
        return chars.length() == 1;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeftChild() {
        return parent != null && this == parent.leftNode;
    }

    public int getFrequence() {
        return frequence;
    }

    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }

    public String getChars() {
        return chars;
    }

    public void setChars(String chars) {
        this.chars = chars;
    }

    public TNode getParent() {
        return parent;
    }

    public void setParent(TNode parent) {
        this.parent = parent;
    }

    public TNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TNode leftNode) {
        this.leftNode = leftNode;
    }

    public TNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TNode rightNode) {
        this.rightNode = rightNode;
    }
}