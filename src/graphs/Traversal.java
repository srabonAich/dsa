package graphs;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.*;

/*
        1-----3
       /      | \
      0       |  5 ---- 6
       \      | /
        2-----4
 */
public class Traversal {
    static class Edge{
        int src; int des; int wt;
        public Edge(int s, int d, int w){
            this.src = s;
            this.des = d;
            this.wt = w;
        }
    }
    public static void createGraph(ArrayList<Edge>[]graph){
        for(int i=0; i<graph.length; i++)
            graph[i] = new ArrayList<>();

        graph[0].add(new Edge(0,1,1));
        graph[0].add(new Edge(0,2,1));

        graph[1].add(new Edge(1,0,1));
        graph[1].add(new Edge(1,3,1));

        graph[2].add(new Edge(2,0,1));
        graph[2].add(new Edge(2,4,1));

        graph[3].add(new Edge(3,1,1));
        graph[3].add(new Edge(3,4,1));
        graph[3].add(new Edge(3,5,1));

        graph[4].add(new Edge(4,2,1));
        graph[4].add(new Edge(4,3,1));
        graph[4].add(new Edge(4,5,1));

        graph[5].add(new Edge(5,3,1));
        graph[5].add(new Edge(5,4,1));
        graph[5].add(new Edge(5,6,1));

        graph[6].add(new Edge(6,5,1));
    }
    public static void bfs(ArrayList<Edge>[]graph){//O(V+E)
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];
        q.add(0); //source = 0

        while(!q.isEmpty()){
            int curr = q.remove();

            if(!visited[curr]){
                System.out.print(curr+" ");
                visited[curr] = true;
                for(int i =0 ; i<graph[curr].size();i++){
                    Edge e = graph[curr].get(i);
                    q.add(e.des);
                }
            }
        }
    }
    public static void dfs(ArrayList<Edge>[]graph, int curr,boolean[]vis){
        //visit
        System.out.print(curr+" ");
        vis[curr] = true;

        //neighbours find
        for(int i=0; i<graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.des]){//if neighbour visited or not
                dfs(graph, e.des, vis);
            }
        }

    }
    public static void main(String[] args) {
        int v = 7;
        ArrayList<Edge>[]graph = new ArrayList[v];
        createGraph(graph);
        bfs(graph);
        dfs(graph,0,new boolean[v]);
    }
}
