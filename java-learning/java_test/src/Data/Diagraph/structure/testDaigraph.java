package Data.Diagraph.structure;

import java.util.ArrayList;
import java.util.Stack;

public class testDaigraph {
    public static void main(String[] agrs)throws Exception{
        Diagraph g = new Diagraph();
        g.insert(new Vertex("A"));
        g.insert(new Vertex("B"));
        g.insert(new Vertex("C"));
        g.insert(new Vertex("D"));
        g.insert(new Vertex("E"));
        g.insert(new Vertex("G"));
        g.insert(new Vertex("F"));
        g.insert(new Vertex("H"));

        g.addEdge(0, 3);
        g.addEdge(0, 4);
        g.addEdge(1, 4);
        g.addEdge(3, 5);
        g.addEdge(4, 5);
        g.addEdge(2, 6);
        g.addEdge(5, 7);
        g.addEdge(6, 7);

        g.topo();
        g.displayWarShall();
    }
}

class Diagraph{
    private Vertex[] vertix = new Vertex[20];
    private int[][] adjMatrix = new int[20][20];
    private int vSize = 0;
    private int vMsize = 20;
    private int[][] warShallArr;

    public void insert(Vertex v)throws Exception{
        if(vSize >= vMsize){
            throw new Exception("超过可存储最大顶点数");
        }
        v.isVisited = false;
        vertix[vSize++] = v;
    }

    public void addEdge(int from, int to){
        adjMatrix[from][to] = 1;
    }

    // 拓扑排序
    public void topo() throws Exception{
        int[][] arr = cloneAdjMa();
        Stack<Integer> s = new Stack<>();
        ArrayList<Integer> temp = new ArrayList<>();

        int tail = findTail(arr);
        s.push(tail);
        temp.add(tail);
        temp = findPre(arr, temp);
        while(temp.size() > 0){
            for(int v : temp){
                if(vertix[v].isVisited == false){
                    vertix[v].isVisited = true;
                    s.push(v);
                }
            }
            temp = findPre(arr, temp);
        }
        while(!s.empty()){
            System.out.print(vertix[s.pop()] + " ");
        }
        System.out.println();
    }

    public ArrayList<Integer> findPre(int[][] arr, ArrayList<Integer> t){
        ArrayList<Integer> tailArr = new ArrayList<>();
        for(int v : t){
            for(int i = 0; i < vSize; i++){
                if(arr[i][v] == 1){
                    tailArr.add(i);
                    arr[i][v] = 0;
                }
            }
        }
        return tailArr;
    }

    public int findTail(int[][] adjMa) throws Exception{
        for(int i = 0; i < vSize; i++) {
            for (int j = i + 1; j < vSize; j++) {
                if (adjMa[i][j] == 1) {
                    int k = 0;
                    for (; k < vSize; k++) {
                        if (adjMa[j][k] == 1) {
                            break;
                        }
                    }
                    if (k >= vSize) {
                        return j;
                    }
                }
            }
        }
        throw new Exception("图中含有环！！！");
    }


    public void displayWarShall(){
        countWarShall();
        for(int i = 0; i < vSize; i++){
            for(int j = 0; j < vSize; j++){
                System.out.print(warShallArr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void countWarShall(){
        if(warShallArr != null){
            return;
        }
        warShallArr = cloneAdjMa();
        for(int i = 0; i < vSize; i++){
            for(int j = 0; j < vSize; j++){
                if(warShallArr[i][j] == 1){
                    for(int k = 0; k < vSize; k++){
                        if(warShallArr[j][k] == 1){
                            warShallArr[i][k] = 1;
                        }
                    }
                }
            }
        }
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
