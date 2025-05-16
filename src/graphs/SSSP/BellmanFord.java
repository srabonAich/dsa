package graphs.SSSP;

/*
run dijkstra v-1 times

will use Arraylist of edges instead of Array of arraylist
 */
import java.util.ArrayList;
import java.util.Scanner;

public class BellmanFord {
    static class Edge{
        int src, dest, wt;
        public Edge(int src, int dest, int wt){
            this.src = src; this.dest = dest; this.wt = wt;
        }
    }
    ArrayList<Edge> graph;
    int[] dis;
    int V;
    public BellmanFord(int V){
        this.V = V;
        graph = new ArrayList<>();
        dis = new int[V];
    }
    public void createGraph(int u,int v,int w){
        graph.add(new Edge(u,v,w));
    }
    public void bellmanFord(int src){
        for(int i=0; i<V; i++){
            if(i!=src)
                dis[i] = Integer.MAX_VALUE;
        }
        for(int i=0; i<V-1; i++){
            for (Edge e : graph) {
                if ((dis[e.src] + e.wt) < dis[e.dest]) {
                    dis[e.dest] = dis[e.src] + e.wt;
                }
            }
        }
        for(int i: dis) System.out.print(i+" ");
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        BellmanFord graph = new BellmanFord(V);
        for(int i=0; i<E; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph.createGraph(u,v,w);
        }
        graph.bellmanFord(0);
    }
}
