package graphs.APSP;

import java.util.*;

public class Johnson {
    static class Edge{
        int src, dest, wt;
        public Edge(int s, int d, int w){
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }
    int V;
    ArrayList<Edge>[] graph;
    boolean[] vis;
    public Johnson(int V){
        this.V=V;
        graph = new ArrayList[V];
        for(int i=0; i<V; i++)
            graph[i] = new ArrayList<>();
        vis = new boolean[V];
    }
    static class Pair implements Comparable<Pair>{
        int n; int path;
        public Pair(int n, int path){
            this.n = n; this.path = path;
        }
        public int compareTo(Pair p2){
            return this.path - p2.path;
        }
    }
    void createGraph(int u, int v, int w){
        graph[u].add(new Edge(u,v,w));
    }
    int[] bellmanFord(int src, List<Edge> allEdges, int V) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 0; i < V - 1; i++) {
            for (Edge e : allEdges) {
                if (dist[e.src] != Integer.MAX_VALUE && dist[e.src] + e.wt < dist[e.dest]) {
                    dist[e.dest] = dist[e.src] + e.wt;
                }
            }
        }

        // Check for negative-weight cycle
        for (Edge e : allEdges) {
            if (dist[e.src] != Integer.MAX_VALUE && dist[e.src] + e.wt < dist[e.dest]) {
                return null;
            }
        }
        return dist;
    }
    int[] dijkstra(int src, List<Edge>[] newGraph) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int u = current.n;
            int d = current.path;

            if (d > dist[u]) continue;

            for (Edge e : newGraph[u]) {
                if (dist[u] + e.wt < dist[e.dest]) {
                    dist[e.dest] = dist[u] + e.wt;
                    pq.add(new Pair(e.dest, dist[e.dest]));
                }
            }
        }

        return dist;
    }
    int[][] johnson() {
        // Step 1: Add extra node 's' (V-th index) and connect to all nodes with 0-weight edges
        int newV = V + 1;
        List<Edge> allEdges = new ArrayList<>();
        for (int u = 0; u < V; u++) {
            allEdges.addAll(graph[u]);
        }
        for (int v = 0; v < V; v++) {
            allEdges.add(new Edge(V, v, 0));  // s -> v with weight 0
        }

        // Step 2: Run Bellman-Ford from 's'
        int[] h = bellmanFord(V, allEdges, newV);
        if (h == null) {
            System.out.println("Graph contains a negative-weight cycle");
            return null;
        }

        // Step 3: Reweight all edges
        List<Edge>[] newGraph = new ArrayList[V];
        for (int i = 0; i < V; i++) newGraph[i] = new ArrayList<>();

        for (Edge e : allEdges) {
            if (e.src == V) continue; // Skip virtual node edges
            int newWeight = e.wt + h[e.src] - h[e.dest];
            newGraph[e.src].add(new Edge(e.src, e.dest, newWeight));
        }

        // Step 4: Run Dijkstra for each vertex
        int[][] D = new int[V][V];
        for (int u = 0; u < V; u++) {
            int[] dist = dijkstra(u, newGraph);
            for (int v = 0; v < V; v++) {
                if (dist[v] != Integer.MAX_VALUE) {
                    D[u][v] = dist[v] + h[v] - h[u];
                } else {
                    D[u][v] = Integer.MAX_VALUE;  // No path
                }
            }
        }
        return D;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        Johnson g = new Johnson(V);
        for(int i=0; i<E; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            g.createGraph(u,v,w);
        }
        int[][] result = g.johnson();
        if (result != null) {
            System.out.println("All-Pairs Shortest Paths:");
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (result[i][j] == Integer.MAX_VALUE)
                        System.out.print("INF ");
                    else
                        System.out.print(result[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
