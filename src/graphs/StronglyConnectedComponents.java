package graphs;

import java.util.ArrayList;
import java.util.Stack;

/*
SCC is a component in which we can reach every vertex of
the component from every other vertex in that component
      1 -----> 0 ----> 3
       \      /        |
        \    /         |
          2            4
 1->0, 0->2, 2->1, 0->3, 3->4
 there will be 3 scc (1,2,0), (3), (4)
solve Kosaraju's algo
modified dfs => REVERSED DFS

Three Steps
1.get nodes in stack -> using topological sort
2.transpose the graph -> reverse every edge
3.do dfs according to stack nodes on the transpose graph
 */
public class StronglyConnectedComponents {
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
        graph[1].add(new Edge(1,0));
        graph[2].add(new Edge(2,1));
        graph[3].add(new Edge(3,4));
    }

    public static void topSort(ArrayList<Edge>[]graph, int curr, boolean[] vis, Stack<Integer>s){
        vis[curr] = true;
        for(int i=0; i<graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.des]){
                topSort(graph, e.des, vis, s);
            }
        }
        s.push(curr);
    }

    public static void dfs(ArrayList<Edge>[]graph, int curr, boolean[] vis){
        vis[curr] = true;
        System.out.print(curr+" ");
        for(int i=0; i<graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.des]){
                dfs(graph, e.des, vis);
            }
        }
    }

    public static void scc(ArrayList<Edge>[]graph, int v){
        //step 1
        Stack<Integer>s = new Stack<>();
        boolean[] vis = new boolean[v];
        for(int i=0; i<v; i++){
            if(!vis[i]){
                topSort(graph, i, vis, s);
            }
        }

        //step 2
        ArrayList<Edge>[] transpose = new ArrayList[v];
        for(int i=0; i<v; i++){
            vis[i] = false;
            transpose[i] =  new ArrayList<>();
        }
        for(int i=0; i<v; i++){
            for(int j=0; j<graph[i].size(); j++){
                Edge e = graph[i].get(j); // e.src -> e.des
                transpose[e.des].add(new Edge(e.des, e.src));
                // e.des -> e.src // reverse
            }
        }

        //step 3
        while(!s.isEmpty()){
            int curr = s.pop();
            if(!vis[curr]){
                System.out.println("SCC -> ");
                dfs(transpose, curr,vis);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        int v=5;
        ArrayList<Edge>[]graph = new ArrayList[v];
        createGraph(graph);
        scc(graph,v);
    }
}
