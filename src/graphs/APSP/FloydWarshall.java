package graphs.APSP;

import java.util.Scanner;

public class FloydWarshall {
    int V;
    int[][] matrix;
    int[][] parent;
    public FloydWarshall(int V){
        this.V=V;
        matrix = new int[V][V];
        parent = new int[V][V];
        for(int i=0; i<V; i++){
            for(int j=0; j<V; j++){
                if(i!=j) {
                    matrix[i][j] = Integer.MAX_VALUE;
                    parent[i][j] = -1;
                }
                else {
                    matrix[i][j] = 0;
                    parent[i][j] = -1;
                }
            }
        }
    }
    public void createMatrix(int u, int v, int w){
        matrix[u][v] = w;
        parent[u][v] = u;
    }
    public int[][] floydWarshall(){
        for(int k=0; k<V; k++){
            for(int i=0; i<V; i++){
                for(int j=0; j<V; j++){
                    if(matrix[i][k] != Integer.MAX_VALUE && matrix[k][j]!= Integer.MAX_VALUE) {
                        if(matrix[i][j]>matrix[i][k] + matrix[k][j]){
                            matrix[i][j] =  matrix[i][k] + matrix[k][j];
                            parent[i][j] = parent[k][j];
                        }
                    }
                }
            }
        }

        for(int i=0; i<V; i++){
            if(matrix[i][i] < 0){
                System.out.println("Negetive Weight Cycle Detected");
                return null;
            }
        }

        return matrix;
    }
    public void printMatrix(int[][] dist){
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == Integer.MAX_VALUE)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void printPath(int u, int v) {
        if (u == v) {
            System.out.print(u + " ");
        } else if (parent[u][v] == -1) {
            System.out.println("No path from " + u + " to " + v);
        } else {
            printPath(u, parent[u][v]);
            System.out.print(v + " ");
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        FloydWarshall graph = new FloydWarshall(V);
        for(int i=0; i<E; i++){
            int u = sc.nextInt();
            //int u = sc.nextInt()-1;
            int v = sc.nextInt();
            //int v = sc.nextInt()-1; 1 based indexing
            int w = sc.nextInt();
            graph.createMatrix(u,v,w);
        }
        int[][] result = graph.floydWarshall();
        if(result != null){
            graph.printMatrix(result);
            System.out.println();
            graph.printPath(0,3);

        }
    }
}
