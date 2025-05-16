package graphs;import java.util.*;

public class labEval {
    ArrayList<Edge>[] graph;
    int[] dt;
    int[] low;
    int time=0;
    boolean[] vis;
    Stack<Integer> s;
    int V;
    public labEval(int V){
        this.V=V;
        graph = new ArrayList[V+1];
        for(int i=1; i<=V; i++){
            graph[i] = new ArrayList<>();
        }
        dt = new int[V+1];
        low = new int[V+1];
        vis = new boolean[V+1];
        s = new Stack<>();
    }
    static class Edge{
        int src, dest;
        public Edge(int src, int dest){
            this.src = src; this.dest = dest;
        }
    }
    public void createGraph(int a, int b){
        graph[a].add(new Edge(a,b));
        graph[b].add(new Edge(b,a));
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
                    s.push(curr);
                }
                children++;
            }
        }

        if(par == -1 && children > 1){
            s.push(curr);
        }
    }
    public void getAP(){
        for(int i=1; i<=V; i++){
            if(!vis[i]){
                dfs(i,-1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        labEval graph = new labEval(V);
        for(int i=0; i<E; i++){
            int u=sc.nextInt();
            int v = sc.nextInt();
            graph.createGraph(u,v);
        }
        graph.getAP();
        System.out.print(graph.s.size());
    }
}
