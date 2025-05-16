package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
Directed Acyclic graph(DAG) is a directed graph with no cycles
topological sorting is used only for DAG's
it is a linear order of vertices such that every directed edge u->v
the vertex u comes before v in the order
maintains dependency

use DFS
[use normal dfs also maintain a stack to store which value comes first]


using BFS
by calculating in degree of the vertices
a DAG must have at least one vertex (u) with in degree 0
                at least one vertex (v) with out degree 0
maintain a queue including vertices only 0 in degree
this is called "KAHN's Algorithm"
 */
public class TopologicalSorting {
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
        graph[2].add(new Edge(2,3));
        graph[3].add(new Edge(3,1));
        graph[4].add(new Edge(4,0));
        graph[4].add(new Edge(4,1));
        graph[5].add(new Edge(5,0));
        graph[5].add(new Edge(5,2));
    }
    public static void topSortDFS(ArrayList<Edge>[]graph){
        boolean[]vis = new boolean[graph.length];
        Stack<Integer> s = new Stack<>();

        for(int i=0; i<graph.length; i++){
            if(!vis[i]){
                topSortdfsUtil(graph, i , vis, s); // modified dfs
            }
        }

        //print elements from stack
        while(!s.isEmpty()){
            System.out.print(s.pop()+" ");
        }
    }
    // modified dfs
    public static void topSortdfsUtil(ArrayList<Edge>[]graph, int curr, boolean[] vis, Stack<Integer>s){
        vis[curr] = true;
        for(int i=0; i<graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.des]){
                topSortdfsUtil(graph, e.des, vis, s);
            }
        }
        s.push(curr);
    }

    public static void calcIndeg(ArrayList<Edge>[]graph, int[] indeg){
        // traverse over the graph, increase the indeg of destination,
        // as edges enter to the des
        for(int i=0; i<graph.length; i++){
            for(int j=0; j<graph[i].size(); j++){
                Edge e = graph[i].get(j);
                indeg[e.des]++;
            }
        }
    }

    public static void topSortBFS(ArrayList<Edge>[]graph){
        // calculate Indegree
        int[] indeg = new int[graph.length];
        calcIndeg(graph,indeg);
        Queue<Integer> q = new LinkedList<>();
        // add elements to the queue, which have indeg = 0
        for(int i=0; i<indeg.length; i++){
            if(indeg[i] == 0){
                q.add(i);
            }
        }
        // bfs
        while(!q.isEmpty()){
            int curr = q.remove();
            System.out.print(curr+" ");

            for(int i=0; i<graph[curr].size(); i++){
                Edge e = graph[curr].get(i);
                indeg[e.des]--;
                if(indeg[e.des] == 0){
                    q.add(e.des);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        /*
                 5 ----> 0 <---- 4-------
                 |                       |
                  \                     /
                   --> 2 --> 3 --> 1 <--

         */
        int v=6;
        ArrayList<Edge>[]graph = new ArrayList[v];
        createGraph(graph);
        //topSortDFS(graph);
        topSortBFS(graph);
    }
}
