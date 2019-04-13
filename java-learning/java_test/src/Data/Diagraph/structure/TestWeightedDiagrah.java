package Data.Diagraph.structure;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class TestWeightedDiagrah {
    public static void main(String[] agrs) throws Exception{
        WrightedDiagrah g = new WrightedDiagrah();
        g.insert(new Vertex("v1"));


    }
}

class WrightedDiagrah{
    private Vertex[] vertix = new Vertex[20];
    private int[][] adjMatrix = new int[20][20];
    private int vSize = 0;
    private int vMsize = 20;
    private int[][] shortPathMatrix;
    Hashtable<Path, ArrayList<Vertex>> shortPath = null;

    public void insert(Vertex v)throws Exception{
        if(vSize >= vMsize){
            throw new Exception("超过可存储最大顶点数");
        }
        v.isVisited = false;
        vertix[vSize++] = v;
    }

    public void addEdge(int from, int to, int weight){
        adjMatrix[from][to] = weight;
    }

    public void countShortPath(){
         if(shortPath != null){ return; }
         for(int i = 0; i < vSize; i++){
             for(int j = 0; j < vSize; j++){
                 if(adjMatrix[i][j] > 0){
                     shortPathMatrix[i][j] = adjMatrix[i][j];

                 }
             }
         }
    }

    public void shortPath(Path p){
        countShortPath();
        for(Vertex v : shortPath.get(p)){
            System.out.print(v);
        }
        System.out.println();
    }

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

class Path{
    String label1;
    String label2;

    @Override
    public int hashCode() {
        return label1.hashCode() + label2.hashCode();
    }

    @Override
    public boolean equals(Object path){
        Path p = (Path)path;
        if(p == this){
            return true;
        }
        if(p.hashCode() == hashCode()){
            return true;
        }
        if(p.getLabel1() == this.label1 && p.getLabel2() == this.label2){
            return true;
        }
        return false;
    }

    public String getLabel1(){
        return label1;
    }

    public String getLabel2(){
        return label2;
    }
}
