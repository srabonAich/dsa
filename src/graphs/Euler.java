package graphs;
/*
Euler path = a path that visits every Edge once
Euler circuit = a euler path that starts and ends at the same vertex
EC -> every vertex has Even degree
   -> all nonzero nodes should be connected
EP -> zero or two nodes can have odd degree
   -> all nonzero nodes should be connected

 */
import java.util.*;
// this is 0 based indexing implementation
/*
public class Euler {
    static class Edge {
        int src, dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    ArrayList<Edge>[] graph;
    int V;
    int oddDeg = 0;
    Set<Integer> visited; // changed from boolean[] vis
    int[] degree;

    public Euler(int V) {
        this.V = V;
        graph = new ArrayList[V];
        visited = new HashSet<>();
        degree = new int[V];
        for (int i = 0; i < V; i++) graph[i] = new ArrayList<>();
    }

    public void createGraph(int u, int v) {
        graph[u].add(new Edge(u, v));
        graph[v].add(new Edge(v, u));
    }

    public void calcDeg() {
        for (int i = 0; i < V; i++) {
            degree[i] = graph[i].size();
            if (degree[i] % 2 != 0) oddDeg++;
        }
    }

    public void dfs(int curr) {
        visited.add(curr);
        for (Edge e : graph[curr]) {
            if (!visited.contains(e.dest)) {
                dfs(e.dest);
            }
        }
    }

    public void isEuler() {
        calcDeg();

        if (oddDeg != 0 && oddDeg != 2) {
            System.out.println("not euler path or circuit");
            return;
        }

        for (int i = 0; i < V; i++) {
            if (degree[i] != 0) {
                dfs(i);
                break;
            }
        }

        for (int i = 0; i < V; i++) {
            if (degree[i] != 0 && !visited.contains(i)) {
                System.out.println("not euler path or circuit");
                return;
            }
        }

        if (oddDeg == 0) System.out.println("Euler Circuit");
        else System.out.println("Euler path");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        Euler graph = new Euler(V);
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.createGraph(u, v);
        }
        graph.isEuler();
    }
}
*/

// this is 1 based indexing
import java.util.*;

public class Euler {
    static class Edge {
        int src, dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    ArrayList<Edge>[] graph;
    int V;
    int oddDeg = 0;
    Set<Integer> visited;
    int[] degree;

    public Euler(int V) {
        this.V = V;
        graph = new ArrayList[V + 1]; // CHANGED
        visited = new HashSet<>();
        degree = new int[V + 1]; // CHANGED
        for (int i = 1; i <= V; i++) graph[i] = new ArrayList<>(); // CHANGED
    }

    public void createGraph(int u, int v) {
        graph[u].add(new Edge(u, v));
        graph[v].add(new Edge(v, u));
    }

    public void calcDeg() {
        for (int i = 1; i <= V; i++) { // CHANGED
            degree[i] = graph[i].size();
            if (degree[i] % 2 != 0) oddDeg++;
        }
    }

    public void dfs(int curr) {
        visited.add(curr);
        for (Edge e : graph[curr]) {
            if (!visited.contains(e.dest)) {
                dfs(e.dest);
            }
        }
    }

    public void isEuler() {
        calcDeg();

        if (oddDeg != 0 && oddDeg != 2) {
            System.out.println("not euler path or circuit");
            return;
        }

        for (int i = 1; i <= V; i++) { // CHANGED
            if (degree[i] != 0) {
                dfs(i);
                break;
            }
        }

        for (int i = 1; i <= V; i++) { // CHANGED
            if (degree[i] != 0 && !visited.contains(i)) {
                System.out.println("not euler path or circuit");
                return;
            }
        }

        if (oddDeg == 0) System.out.println("Euler Circuit");
        else System.out.println("Euler path");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        Euler graph = new Euler(V);
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.createGraph(u, v);
        }
        graph.isEuler();
    }
}
