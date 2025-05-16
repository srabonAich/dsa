package graphs;

import java.util.ArrayList;

/*
AP = a vertex in an undirected graph if removing it increases
    the connected components of graph
used for designing more efficient & reliable networks
solves point of failure/vulnerability of network
use Tarjan's algorithm
modified dfs
uses Ancestor & child concept
discovery time = dt[]
low array = lowest of all neighbors= low[v]
ancestor = dt[v] < dt[curr]

a vertex is AP if it satisfies any of three cases:
case1: starting point of dfs (par == -1) is AP
    child ->> unvisited
    number of disconnected children > 1 ->> AP
case2: if there's a bridge u -> v, u is a AP
    par != -1 && dt[u] < low[v]
case3: if u is starting point/root of cycle, u-> AP
    par != -1 && dt[u] == low[v]
    for a cycle, the dt of root node is equal to low of any
    children
for 2 & 3 AP
    par != -1 && dt[src] <= low[des]

pseudocode
dfs
vis[curr] = true
dt[curr] = low[curr] = ++time
children = 0
for(neighbor)
    Edge e
    1 neigh == par -> ignore
    2 vis[neigh] == true //backedge
        low[curr] = min(low[curr], dt[neigh])
    3 !vis[neigh] == true //disconnected children
        dfs -> visit
        low[curr]=min(low[curr], low[neigh])
        if(par != -1 && dt[curr] <= low[neigh])
            print AP
        children++
    //ap condition 1
    if(par == -1 && children > 1)
        print AP
 */
public class ArticulationPoint {
    ArrayList<Edge>[] graph;
    int[] dt;
    int[] low;
    int time=0;
    boolean[] vis;
    int V;
    public ArticulationPoint(int V){
        this.V=V;
        graph = new ArrayList[V];
        for(int i=0; i<V; i++){
            graph[i] = new ArrayList<>();
        }
        dt = new int[V];
        low = new int[V];
        vis = new boolean[V];
    }
    static class Edge{
        int src, dest;
        public Edge(int src, int dest){
            this.src = src; this.dest = dest;
        }
    }
    public void createGraph(int a, int b){
        graph[a].add(new Edge(a,b));
    }
    public void dfs(int curr, int par){
        vis[curr]=true;
        dt[curr] = low[curr] = ++time;
        int children = 0;
        for(int i=0; i<graph[curr].size(); i++){
            Edge e = graph[curr].get(i);

            if(e.dest == par) continue;
            else if(vis[e.dest]) low[curr] = Math.min(low[curr],dt[e.dest]);
            else{
                dfs(e.dest, curr);
                low[curr]=Math.min(low[curr],low[e.dest]);
                if(par != -1 && dt[curr] <= low[e.dest]){
                    System.out.println("AP: "+curr);
                }
                children++;
            }
        }

        if(par == -1 && children > 1){
            System.out.println("AP: "+curr);
        }
    }
    public void getAP(){
        for(int i=0; i<V; i++){
            if(!vis[i]){
                dfs(i,-1);
            }
        }
    }

    public static void main(String[] args) {
        int V = 5;
        ArticulationPoint graph = new ArticulationPoint(V);
        graph.createGraph(0,1);
        graph.createGraph(0,2);
        graph.createGraph(0,3);
        graph.createGraph(1,0);
        graph.createGraph(1,2);
        graph.createGraph(2,0);
        graph.createGraph(2,1);
        graph.createGraph(3,0);
        graph.createGraph(3,4);
        graph.createGraph(4,3);
        graph.getAP();
    }
}
