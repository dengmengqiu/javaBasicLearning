package Data.Graph.structure;

import java.lang.Exception;


import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

public class TestGraph1 {
    public static void main(String[] args) throws Exception{
        Graph1 g = new Graph1();
        g.insert(new Vertex("A"));
        g.insert(new Vertex("B"));
        g.insert(new Vertex("C"));
        g.insert(new Vertex("D"));
        g.insert(new Vertex("E"));
        g.insert(new Vertex("F"));
        g.insert(new Vertex("G"));
        g.insert(new Vertex("H"));
        g.insert(new Vertex("I"));

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(0, 4);
        g.addEdge(1, 5);
        g.addEdge(3, 6);
        g.addEdge(5, 7);
        g.addEdge(6, 8);

        g.deepFirstSearch();
        System.out.println();
        g.breadthDeepFirst();
    }
}

class Graph1{
    private Vertex[] vertix = new Vertex[20];
    private int[][] adjMatrix = new int[20][20];
    private int vSize = 0;
    private int vMszie = 20;

    public void insert(Vertex v) throws Exception{
        if(vSize >= vMszie){
            throw new Exception("可存储顶点个数已达上限");
        }
        v.isVisited = false;
        vertix[vSize++] = v;
    }

    public void addEdge(int start, int end){
        adjMatrix[start][end] = 1;
        adjMatrix[end][start] = 1;
    }

    public void deepFirstSearch(){
        for(int i = 0; i < vSize; i++){
            vertix[i].isVisited = false;
        }
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < vSize; i++){
            if(vertix[i].isVisited == false){
                stack.push(i);
                while(!stack.empty()){
                    int v = stack.pop();
                    int adjV = seekAdjVerix(v);
                    while(vertix[v].isVisited && adjV < 0){
                        if(stack.empty()){
                            return;
                        }
                        v = stack.pop();
                        adjV = seekAdjVerix(v);
                    }
                    System.out.print(vertix[v]);
                    vertix[v].isVisited = true;
                    if(adjV > -1) {
                        stack.push(v);
                        stack.push(adjV);
                    }else{
                        System.out.println();
                    }
                }
            }
        }
    }

    public void breadthDeepFirst(){
        for(int i = 0; i < vSize; i++){
            vertix[i].isVisited = false;
        }
        Queue<Integer> que = new PriorityQueue<>(vSize);
        que.add(0);
        while(!que.isEmpty()){
            int v = que.poll();
            System.out.print(vertix[v]);
            vertix[v].isVisited = true;
            for(int i = 0; i < vSize; i++){
                if(adjMatrix[v][i] == 1 && vertix[i].isVisited == false){
                    que.add(i);
                }
            }
        }
    }

    public int seekAdjVerix(int v){
        for(int i = 0; i < vSize; i++){
            if(adjMatrix[v][i] == 1){
                if(vertix[i].isVisited == false){
                    return i;
                }
            }
        }
        return -1;
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
