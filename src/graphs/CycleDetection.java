package graphs;

import java.util.ArrayList;

/*
            1 ------- 3
           /          | \
          0           |  5 --- 6
           \          | /
            2 ------- 4
    two cycles 0-1-3-4-2-0, 3-4-5-3
    output true
    we will use dfs
 */
public class CycleDetection {
    static class Edge{
        int src; int des;
        public Edge(int s, int d){
            this.src = s; this.des = d;
        }
    }
    public static void createGraph(ArrayList<Edge>[]graph){
        for(int i=0; i<graph.length; i++)
            graph[i] = new ArrayList<>();
        graph[0].add(new Edge(0,1));
        graph[0].add(new Edge(0,2));
        graph[0].add(new Edge(0,3));
        graph[1].add(new Edge(1,0));
        graph[1].add(new Edge(1,2));
        graph[2].add(new Edge(2,0));
        graph[2].add(new Edge(2,1));
        graph[3].add(new Edge(3,0));
        graph[3].add(new Edge(3,4));
        graph[4].add(new Edge(4,3));
    }
    public static boolean detectCycle(ArrayList<Edge>[]graph){
        boolean[]vis = new boolean[graph.length];
        for(int i=0; i<graph.length; i++){ //this loop is for all the parts of the graph which may not be connected
            if(!vis[i]){
                if(detectCycleUtil(graph, vis, i, -1)){  // parent is -1 for the starting vertex
                    return true;
                    // cycle exists in one of the parts
                }
            }
        }
        return false;
    }
    public static boolean detectCycleUtil(ArrayList<Edge>[]graph, boolean[]vis, int curr, int par){
        vis[curr] = true;
        for(int i=0; i<graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            // case3
            if(!vis[e.des]){
                if(detectCycleUtil(graph, vis, e.des, curr)){
                    return true;
                }
            }
            // case1
            else if(vis[e.des] && e.des != par) return true;
            // case2 do nothing
        }
        return false;
    }
    public static void main(String[] args) {
        /*
                0 -------- 3
               /|          |
              / |          |
             1  |          4
              \ |
               2
         */
        int v = 5;
        ArrayList<Edge>[]graph = new ArrayList[v];
        createGraph(graph);
        System.out.println(detectCycle(graph));
    }
}
