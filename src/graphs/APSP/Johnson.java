package graphs.APSP;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Johnson {
    static class Edge{
        int src, destination, weight;
        public Edge(int s, int d, int w){
            this.src = s;
            this.destination = d;
            this.weight = w;
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
    void createGraph(int u, int v, int w){
        graph[u].add(new Edge(u,v,w));
        graph[v].add(new Edge(v,u,w));
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        Johnson graph = new Johnson(V);
        for(int i=0; i<E; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph.createGraph(u,v,w);
        }
    }
}
