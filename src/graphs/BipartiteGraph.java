package graphs;
import java.util.*;
/*
IF GRAPH DOESN'T HAVE CYCLES-> BIPARTITE
             bipartite
         Acyclic ---> True
      Even Cycle ---> True
       Odd Cycle ---> False
                0----2
               /    /
              1    4
               \  /
                3
                output = false
        using modified bfs
 */
public class BipartiteGraph {
    static class Edge{
        int src; int des;
        public Edge(int s, int d){
            this.src=s; this.des=d;
        }
    }
    public static void createGraph(ArrayList<Edge>[]graph){
        for(int i=0; i<graph.length; i++){
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0,1));
        graph[0].add(new Edge(0,2));
        graph[1].add(new Edge(1,0));
        graph[1].add(new Edge(1,3));
        graph[2].add(new Edge(2,0));
        graph[2].add(new Edge(2,4));
        graph[3].add(new Edge(3,1));
        //graph[3].add(new Edge(3,4));
        graph[4].add(new Edge(4,2));
        //graph[4].add(new Edge(4,3));
    }
    public static boolean isBipartite(ArrayList<Edge>[]graph){
        int[] col = new int[graph.length];
        for(int i=0; i<col.length; i++){
            col[i] = -1;
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<graph.length; i++){
            if(col[i]==-1){// will do bfs
                q.add(i);
                col[i]=0;
                while(!q.isEmpty()){
                    int curr = q.remove();
                    for(int j=0; j<graph[curr].size(); j++){
                        Edge e= graph[curr].get(j);
                        if(col[e.des] == -1){
                            int nextCol=col[curr]==0?1:0;
                            col[e.des] = nextCol;
                            q.add(e.des);
                        }
                        else if(col[e.des] == col[curr]){
                            return false; // NOT BIPARTITE
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int v=5;
        ArrayList<Edge>[]graph = new ArrayList[v];
        createGraph(graph);
        System.out.println(isBipartite(graph));
    }
}
