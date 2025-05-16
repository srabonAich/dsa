package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphUsingMap {
    Map<Integer, List<Integer>> adj;
    public GraphUsingMap(){
        adj = new HashMap<>();
    }
    void addVetex(int src){
        adj.putIfAbsent(src,new ArrayList<>());
    }
    void addEdge(int src, int des){
       adj.putIfAbsent(src, new ArrayList<>());
       adj.putIfAbsent(des, new ArrayList<>());
       adj.get(src).add(des); //directed
       adj.get(des).add(src); //undirected
    }
    public void printGraph(){
        for(Integer i: adj.keySet()){
            System.out.print(i+" -> ");
            System.out.println(adj.get(i));
        }
    }

    public static void main(String[] args) {
        GraphUsingMap graph = new GraphUsingMap();
        graph.addVetex(0);
        graph.addEdge(1,0);
        graph.addVetex(2);
        graph.addEdge(0,2);
        graph.printGraph();
    }
}
