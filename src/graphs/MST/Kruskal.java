package graphs.MST;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
kruskal algo
for(0 -> v-1)
    edge e -> a(src),b(des)
    par(a), par(b)
    same -> cycle -> X
    diff -> union(par(a), par(b))

will use ArrayList of edges instead of
array of arraylist
 */
public class Kruskal {
    int V;
    int[] par;
    int[] rank;
    ArrayList<Edge> edges;
    public Kruskal(int V){
        this.V = V;
        par = new int[V];
        rank = new int[V];
        edges = new ArrayList<>();
    }
    // disjoint set
    public void init(){
        for(int i=0; i<V; i++)
            par[i]=i;
    }
    public int find(int x){
        if(par[x] == x) return x;
        return par[x] = find(par[x]);
    }
    public void union(int a, int b){
        int parA = find(a);
        int parB = find(b);
        if(rank[parA] ==  rank[parB]){
            par[parB] = parA;
            rank[parA]++;
        }
        else if(rank[parA] > rank[parB])
            par[parB] = parA;
        else
            par[parA] = parB;
    }

    static class Edge implements Comparable<Edge>{
        int src, dest, wt;
        public Edge(int src,int dest, int wt){
            this.src=src; this.dest=dest; this.wt=wt;
        }
        public int compareTo(Edge e2){
            return this.wt - e2.wt;
        }
    }
    public void createGraph(int u, int v, int w){
        edges.add(new Edge(u,v,w));
    }
    public void kruskal(){
        init();
        Collections.sort(edges);
        int cost = 0;
        //int count =0;
        for(int i=0; i<V-1; i++){
            Edge e = edges.get(i);
            if(find(e.src) != find(e.dest)){
                union(e.src,e.dest);
                cost+= e.wt;
            }
        }
        System.out.println("total cost"+cost);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        Kruskal graph = new Kruskal(V);
        for(int i=0; i<E; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph.createGraph(u,v,w);
        }
        graph.kruskal();
    }
}
