package graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class GraphUsingFile_Map {
    static class Graph{
        int V,E;
        Map<Integer,List<Integer>> adjList;
        boolean[] vis;
        Stack<Integer> s = new Stack<>();
        public Graph(File file){
            try {
                Scanner sc = new Scanner(file);
                if(sc.hasNextLine()){
                    String[] firstLine = sc.nextLine().split(" ");
                    V = Integer.parseInt(firstLine[0]);
                    E = Integer.parseInt(firstLine[1]);
                    adjList = new HashMap<>();
                    vis = new boolean[V];

                    while(sc.hasNextLine()){
                        String[] edgeData = sc.nextLine().split(" ");
                        int src = Integer.parseInt(edgeData[0]);
                        int dest = Integer.parseInt(edgeData[1]);
                        addVertex(src);
                        addVertex(dest);
                        addEdge(src,dest);
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        public void addVertex(int src){
            adjList.putIfAbsent(src, new ArrayList<>());
        }

        public void addEdge(int src, int des){
            adjList.putIfAbsent(src, new ArrayList<>());
            adjList.get(src).add(des);
        }

        public int getVertices(){
            return adjList.size();
        }

        public List<Integer> getAdjVertices(int v){
            if(!adjList.containsKey(v)) return null;
            List<Integer>result = new ArrayList<>();
            for(int i: adjList.get(v)){
                result.add(i);
            }
            return result;
        }

        public void displayGraph(){
            for(Integer i: adjList.keySet()){
                System.out.print(i+" -> ");
                System.out.println(adjList.get(i));
            }
        }
        public void DFS(int v){
            if(!adjList.containsKey(v)) return;
            vis[v] = true;
            System.out.print(v+" ");
            for(int neighbor: adjList.get(v)){
                if(!vis[neighbor]){
                    DFS(neighbor);
                }
            }
        }
        public List<Integer> topologicalSort(){
            Arrays.fill(vis,false);
            for(int i:adjList.keySet()){
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
            if(!adjList.containsKey(curr)) return;
            for(int neighbor: adjList.get(curr)){
                if(!vis[neighbor]){
                    topoSortUtil(neighbor);
                }
            }
            s.push(curr);
        }
    }
    public static void main(String[] args) throws IOException {
        File file = new File("/home/srabon/IntelliJProjects/com.DSA/src/graphs/input.txt");

        Graph graph = new Graph(file);

//        System.out.println("Graph adjacency list:");
//        graph.displayGraph();
//
//        System.out.println("\nPerforming DFS starting from vertex 5:");
//        graph.DFS(5);
//
//        System.out.println("\nPerforming Topological Sort:");
//        List<Integer> topoOrder = graph.topologicalSort();
//        System.out.println("Topological Sort order: " + topoOrder);
        System.out.println(graph.getVertices());
        System.out.println(graph.getAdjVertices(5));
    }
}