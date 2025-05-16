package graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class lab_2 {
    static class Edge{
        int s, d;
        public Edge(int s, int d){
            this.s=s; this.d=d;
        }
    }
    static class Graph{
        int V,E;
        ArrayList<Edge>[]graph;
        boolean[] vis;
        Stack<Integer> s = new Stack<>();
        public Graph(File file){
            try {
                Scanner sc = new Scanner(file);
                if(sc.hasNextLine()){
                    String[] firstLine = sc.nextLine().split(" ");
                    V = Integer.parseInt(firstLine[0]);
                    E = Integer.parseInt(firstLine[1]);
                    graph = new ArrayList[V];
                    vis = new boolean[V];
                    for(int i=0; i<V; i++){
                        graph[i] = new ArrayList<>();
                    }

                    while(sc.hasNextLine()){
                        String[] edgeData = sc.nextLine().split(" ");
                        int src = Integer.parseInt(edgeData[0]);
                        int dest = Integer.parseInt(edgeData[1]);

                        graph[src].add(new Edge(src,dest));
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        public void displayGraph(){
            for(int i=0; i<graph.length; i++){
                System.out.print(i+"-> ");
                for(int j=0; j<graph[i].size(); j++){
                    Edge e = graph[i].get(j);
                    System.out.print(e.d+" ");
                }
                System.out.println();
            }
        }
        public void DFS(int v){
            vis[v] = true;
            System.out.print(v+" ");
            for(int i=0; i<graph[v].size(); i++){
                Edge e = graph[v].get(i);
                if(!vis[e.d]){
                    DFS(e.d);
                }
            }
        }
        public List<Integer> topologicalSort(){
            Arrays.fill(vis,false);
            for(int i=0; i<graph.length; i++){
                if(!vis[i]){
                    topoSortUtil(i);
                }
            }
            List<Integer>result = new ArrayList<>();
            while(!s.isEmpty()){
                result.add(s.pop());
            }
            return result;
        }
        public void topoSortUtil(int curr){
            vis[curr]=true;
            for(int i=0; i<graph[curr].size(); i++){
                Edge e = graph[curr].get(i);
                if(!vis[e.d]){
                    topoSortUtil(e.d);
                }
            }
            s.push(curr);
        }
    }
    public static void main(String[] args) throws IOException {
        File file = new File("/home/srabon/IntelliJProjects/com.DSA/src/graphs/input.txt");

        Graph graph = new Graph(file);

        //System.out.println("Graph adjacency list:");
        //graph.displayGraph();

        //System.out.println("\nPerforming DFS starting from vertex 5:");
        //graph.DFS(5);

        System.out.println("\nPerforming Topological Sort:");
        List<Integer> topoOrder = graph.topologicalSort();
        System.out.println("Topological Sort order: " + topoOrder);
    }

}
