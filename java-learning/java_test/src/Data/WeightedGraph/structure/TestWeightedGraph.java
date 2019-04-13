package Data.WeightedGraph.structure;

import java.util.ArrayList;
import java.util.Stack;

public class TestWeightedGraph {
    public static void main(String[ ] args) throws  Exception{
        WeightedGraph g = new WeightedGraph();

        g.insert(new Vertex("A"));
        g.insert(new Vertex("B"));
        g.insert(new Vertex("C"));
        g.insert(new Vertex("D"));
        g.insert(new Vertex("E"));
        g.insert(new Vertex("F"));

        g.addWightedEdge(0, 1, 6);
        g.addWightedEdge(0, 3, 4);
        g.addWightedEdge(1, 3, 7);
        g.addWightedEdge(1, 2, 10);
        g.addWightedEdge(1, 4, 7);
        g.addWightedEdge(3, 2, 8);
        g.addWightedEdge(3, 4, 12);
        g.addWightedEdge(4, 2, 5);
        g.addWightedEdge(2, 5, 6);
        g.addWightedEdge(4, 5, 7);

        g.displayMiniTree();
    }
}
class WeightedGraph{
    private Vertex[] vertix = new Vertex[20];
    private int[][] adjMatrix = new int[20][20];
    private int vSize = 0;
    private int vMsize = 20;
    private int[][] miniSpanningTre;

    public void insert(Vertex v) throws Exception{
        if(vSize >+ vMsize){
            throw new Exception("能存储的顶点个数已超最大值");
        }
        vertix[vSize++] = v;
    }

    public void addWightedEdge(int from, int to, int wight){
        adjMatrix[from][to] = wight;
        adjMatrix[to][from] = wight;
    }

    public void MiniSpanningTreeByPrim(){
        miniSpanningTre = new int[vSize][vSize];
        // 利用普里姆算法求最小生成树
        // 已经连接的顶点集合
        ArrayList<Integer> u = new ArrayList<>();
        //未连接的顶点
        ArrayList<Integer> v = new ArrayList<>();
        u.add(0);
        for(int i = 1; i < vSize; i++){
            v.add(i);
        }

        // 寻找可以使 v 集合中某一顶点到树中的最小权重边连接
        int minWight; //当前最小权重
        int minFrom, minTo; // 当前最小边的两个端点
        while(v.size() > 0){
            minFrom = u.get(0);
            minTo = v.get(0);
            // 写一个比图上所有边权重都大的值
            minWight = 20;
            // 要连接的下一顶点在v中的索引
            int index = 0;
            for(int i = 0; i < u.size(); i++){
                for(int j = 0; j < v.size(); j++){
                    int from = u.get(i);
                    int to = v.get(j);
                    if(adjMatrix[from][to] > 0 && adjMatrix[from][to] < minWight){
                        minFrom = from;
                        minTo = to;
                        index = j;
                        minWight = adjMatrix[from][to];
                    }
                }
            }
            System.out.println("minWight" + minWight);
            miniSpanningTre[minFrom][minTo] = minWight;
            u.add(v.remove(index));
        }
    }

    public void displayMiniTree(){
        MiniSpanningTreeByPrim();
        for(int i = 0; i < vSize; i++){
            for(int j = 0; j < vSize; j++){
                System.out.print(adjMatrix[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
        for(int i = 0; i < vSize; i++){
            for(int j = 0; j < vSize; j++){
                System.out.print(miniSpanningTre[i][j] + "  ");
            }
            System.out.println();
        }

        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        s1.push(0);
        while(!s1.empty() || !s2.empty()){
            while(!s1.empty()){
                int from = s1.pop();
                System.out.print(vertix[from] + "   ");
                for(int i = 0; i < vSize; i++){
                    if(miniSpanningTre[from][i] > 0){
                        s2.push(i);
                    }
                }
            }
            System.out.println();
            while(!s2.empty()){
                int from = s2.pop();
                System.out.print(vertix[from] + "   ");
                for(int i = 0; i < vSize; i++){
                    if(miniSpanningTre[from][i] > 0){
                        s1.push(i);
                    }
                }
            }
            System.out.println();
        }
    }

//    public void MiniSpannningTreeByLruskal(){
//        // 利用克鲁斯卡尔算法求最小生成树
//        miniSpanningTre = new int[vSize][vSize];
//
//
//        int[][] adj = cloneAdjMa();
//        int minFrom, minTo;
//        int minWight = 20;
//        for(int i = 0; i < vSize; i++){
//            for (int j = 0; j < vSize; j++){
//                if(adj[i][j] > 0 && adj[i][j] < minWight){
//                    minWight = adj[i][j];
//                    from =
//                }
//            }
//        }
//    }

    public int[][] cloneAdjMa(){
        int[][] arr = new int[vSize][vSize];
        for(int i = 0; i < vSize; i++){
            for(int j = 0; j < vSize; j++){
                arr[i][j] = adjMatrix[i][j];
            }
        }
        return arr;
    }

}

class Vertex{
    private String label;
    boolean isVisited;

    public Vertex(String label){
        this.label = label;
        this.isVisited = false;
    }

    @Override
    public String toString(){
        return label;
    }
}