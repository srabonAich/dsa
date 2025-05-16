package graphs;

import java.util.ArrayList;
import java.util.Comparator;

/*
run dfs in lexicographic order
solution : sort the adjacency list before calling dfs
 */
public class DFS {
    int V;
    boolean[] vis;
    ArrayList<Edge>[] graph;
    public DFS(int V){
        this.V = V;
        graph = new ArrayList[V];
        for(int i=0; i<V; i++){
            graph[i] = new ArrayList<>();
        }
        vis = new boolean[V];
    }
    static class Edge{
        char src, dest;
        public Edge(char src, char dest){
            this.src = src; this.dest = dest;
        }
    }
    public void addEdge(char src, char dest){
        graph[src-'A'].add(new Edge(src,dest));
    }

    public void sort(){
        for(int i=0; i<V; i++){
            graph[i].sort(Comparator.comparing(e -> e.dest));
        }
    }

    public void dfs(char curr){
        int idx = curr - 'A';
        vis[idx] = true;
        System.out.print(curr+" ");
        for(Edge e: graph[idx]){
            if(!vis[e.dest - 'A']){
                dfs(e.dest);
            }
        }
    }
}
