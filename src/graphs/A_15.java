package graphs;

import java.util.*;

public class A_15 {
    static class Edge{
        int src; int des;
        public Edge(int s, int d){
            this.src=s; this.des=d;
        }
    }
    public static void createGraph(ArrayList<Edge>[]graph, int A, int B){
        graph[A].add(new Edge(A,B));
    }
    public static int[] bfsShortestPath(ArrayList<Edge>[]graph, int start){
        int[] distance = new int[graph.length];
        for(int i=0; i<distance.length; i++){
            distance[i] = -1;
        }
        Queue<Integer>q = new LinkedList<>();
        q.add(start);
        distance[start] = 0;
        while(!q.isEmpty()){
            int curr = q.remove();
                for(int i=0; i<graph[curr].size(); i++){
                    Edge e = graph[curr].get(i);
                    if(distance[e.des] == -1){
                        distance[e.des] = distance[curr] + 1;
                        q.add(e.des);
                    }
                }
        }
        return distance;
    }

    public static void main(String[] args) {
        int N; int E;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        E = sc.nextInt();
        ArrayList<Edge>[]graph = new ArrayList[N+1];
        for(int i=0; i<graph.length; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<E; i++){
            int a = sc.nextInt(); int b = sc.nextInt();
            createGraph(graph, a, b);
        }
        int x = sc.nextInt();
        System.out.println(graph[x].size());
        int[] distance = bfsShortestPath(graph, x);
        for(int i=1; i<=N; i++){
            System.out.print(distance[i]);
            if(i<N) System.out.print(" ");
        }
    }
}
