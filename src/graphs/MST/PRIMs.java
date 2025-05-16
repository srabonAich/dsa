package graphs.MST;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
A minimum spanning tree or minimum WEIGHT spanning tree is a
subset of the edges of a connected, edge-weighted undirected
graph that connects all the vertices together, without any cycles
and with the minimum possible total edge weight

three cond to be a mst
-> subgraph
-> cycle
-> vertices connect
-> weight = minimum

PRIM's Algorithm
vis[]
(priority queue) pq<vertex,cost> --> min pair(cost)
pq(0,0)
while(pq is not empty)
    curr -> (v, cost)
    if(not vis)
        visit
        MST(ans) => mst(edge) /mst(cost)
        neigh ans -> add pq
 */
public class PRIMs {
    static class Edge{
        int src, dest, wt;
        public Edge(int src, int dest, int wt){
            this.src = src; this.dest = dest; this.wt = wt;
        }
    }
    int V;
    ArrayList<Edge>[] graph;
    PriorityQueue<Pair> pq;
    boolean[] vis;

    public PRIMs(int V){
        this.V=V;
        graph = new ArrayList[V];
        for(int i=0; i<V; i++)
            graph[i] = new ArrayList<>();
        pq = new PriorityQueue<>();
        vis = new boolean[V];
    }
    void createGraph(int u, int v, int w){
        graph[u].add(new Edge(u,v,w));
        graph[v].add(new Edge(v,u,w));
    }
    static class Pair implements Comparable<Pair>{
        int v, cost;
        public Pair(int v, int cost){
            this.v = v; this.cost = cost;
        }
        public int compareTo(Pair p2){
            return this.cost - p2.cost;
        }
    }
    public void prims(){
        pq.add(new Pair(0,0));
        int finalCost = 0;
        while(!pq.isEmpty()){
            Pair curr = pq.remove();
            if(!vis[curr.v]){
                vis[curr.v] = true;
                finalCost += curr.cost;

                for(int i=0; i<graph[curr.v].size(); i++){
                    Edge e = graph[curr.v].get(i);
                    pq.add(new Pair(e.dest, e.wt));
                }
            }
        }
        System.out.println("final cost of mst="+finalCost);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        PRIMs graph = new PRIMs(V);
        for(int i=0; i<E; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph.createGraph(u,v,w);
        }
        graph.prims();
    }
}
