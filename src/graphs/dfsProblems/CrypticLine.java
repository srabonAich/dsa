package graphs.dfsProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CrypticLine {
    static class Edge{
        int src, dest;
        public Edge(int src, int dest){
            this.src = src; this.dest = dest;
        }
    }
    int V; boolean[] vis;
    int[] prev;
    ArrayList<Edge>[] graph;
    public CrypticLine(int V){
        this.V = V;
        graph = new ArrayList[V+1];
        for(int i=0; i<=V; i++){
            graph[i] = new ArrayList<>();
        }
        vis = new boolean[V+1];
        prev = new int[V+1];
        Arrays.fill(prev,-1);
    }
    public void addEdge(int a, int b){
        graph[a].add(new Edge(a, b));
    }
    public void dfs(){
        for(int i=1; i<=V; i++){
            if (!vis[i]) {
                dfsUtil(i);
            }
        }
    }
    public void dfsUtil(int curr){
        vis[curr] = true;
        for(Edge e: graph[curr]){
            if(!vis[e.dest]){
                prev[e.dest] = curr;
                dfsUtil(e.dest);
            }
        }
    }
    public boolean isAncestor(int x, int y){
        while(y != -1){
            if(y == x) return true;
            y = prev[y];
        }
        return false;
    }

    public static void main(String[] args) {
        int N, Q;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Q = sc.nextInt();
        CrypticLine graph = new CrypticLine(N);
        for(int i=1; i<N; i++){
            int a=sc.nextInt();
            int b=sc.nextInt();
            graph.addEdge(a, b);
        }
        graph.dfs();
        List<String> ans = new ArrayList<>();
        for(int i=0; i<Q; i++){
            int x=sc.nextInt();
            int y=sc.nextInt();
            if(graph.isAncestor(x,y)) ans.add("YES");
            else ans.add("NO");
        }
        for(String s: ans){
            System.out.println(s);
        }
    }
}
