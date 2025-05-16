package graphs;

import java.util.ArrayList;

/*
bridges is an edge whose deletion increases the graph's number of connected components
- for undirected graphs
- solve by Tarjan's algorithm
- uses modified DFS
- this algo is based on discovery time
- steps
 there will be two arrays
 dt[] = new int[v]
 discovery time of node
 low[] = new int[v]
 lowest discovery time of all neighbours(including node)

pseudocode:
dt[], low[]
dfs()
    vis[curr] = true
    dis[curr] = low[curr] = ++time
    for(all neigh)
        Edge e // src, des
    1 neigh = par - ignore
    2 !vis[neigh] - if the neighbor isn't visited, it might be bridge
        dfs(neigh)
        low[curr] = min(low[curr],low[neigh])
        if(dt[curr] < low[neigh])
            print(bridge(curr,neigh))
    3 vis[neigh] - in this case, edge can't be bridge. We will update low[v]
        low[curr] = min(low[curr],dt[neigh])


 */
public class Bridges {
    static class Edge{
        int src; int des;
        public Edge(int s, int d){
            this.src = s; this.des = d;
        }
    }
    public static void createGraph(ArrayList<Edge>[]graph){
        for(int i=0; i<graph.length; i++){
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0,2));
        graph[0].add(new Edge(0,3));
        graph[0].add(new Edge(0,1));
        graph[1].add(new Edge(1,0));
        graph[1].add(new Edge(1,2));
        graph[2].add(new Edge(2,1));
        graph[2].add(new Edge(2,0));
        graph[3].add(new Edge(3,0));
        graph[3].add(new Edge(3,4));
        graph[3].add(new Edge(3,5));
        graph[4].add(new Edge(4,3));
        graph[4].add(new Edge(4,5));
        graph[5].add(new Edge(5,3));
        graph[5].add(new Edge(5,4));
    }
    public static void dfs(ArrayList<Edge>[]graph, int curr,
                           int par, int[] dt, int[] low,
                           boolean[] vis, int time){
        vis[curr] = true;
        dt[curr] = low[curr] = ++time;

        for(Edge e: graph[curr]){
            if(e.des == par){
                continue;
            }else if(!vis[e.des]){
                dfs(graph,e.des,curr,dt,low,vis,time);
                low[curr] = Math.min(low[curr],low[e.des]);
                if(dt[curr] < low[e.des]){
                    System.out.println("Bridge : "+curr+"--- "+e.des);
                }
            }else{
                low[curr] = Math.min(low[curr],dt[e.des]);
            }
        }
    }
    public static void tarjanBridge(ArrayList<Edge>[]graph,int v){
        int dt[] = new int[v];
        int low[] = new int[v];
        int time = 0;
        boolean[] vis = new boolean[v];

        for(int i=0; i<v; i++){
            if(!vis[i]){
                dfs(graph,i,-1,dt,low,vis,time);
            }
        }
    }

    public static void main(String[] args) {
        int v=6;
        ArrayList<Edge>[]graph = new ArrayList[v];
        createGraph(graph);
        tarjanBridge(graph,v);
    }
}
