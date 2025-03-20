package leetcode100;
import java.util.*;
class Solution {
    // using adjacency list(array of arraylist)
    /*
    static class Edge{
        int src; int des;
        public Edge(int s, int d){
            this.src = s; this.des = d;
        }
    }
    public static void createGraph(ArrayList<Edge>[]graph, int A, int B){
        graph[B].add(new Edge(B,A));
    }
    public static void calcIndeg(ArrayList<Edge>[]graph,int[] indeg){
        for(int i=0; i<graph.length; i++){
            for(int j=0; j<graph[i].size(); j++){
                Edge e = graph[i].get(j);
                indeg[e.des]++;
            }
        }
    }
    public static int[] findOrder(ArrayList<Edge>[]graph) {
        //ArrayList<Integer>ans = new ArrayList<>();
        int[] ans = new int[graph.length];
        int idx = 0;
        int[] indeg = new int[graph.length];
        calcIndeg(graph,indeg);
        Queue<Integer>q = new LinkedList<>();
        for(int i=0; i<indeg.length; i++){
            if(indeg[i] == 0){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int curr = q.remove();
            //ans.add(curr);
            ans[idx++] = curr;
            for(int i=0; i<graph[curr].size(); i++){
                Edge e = graph[curr].get(i);
                indeg[e.des]--;
                if(indeg[e.des]==0){
                    q.add(e.des);
                }
            }
        }

        return ans;
    }
    public static void main(String[] args){
        int numCourses;
        Scanner sc= new Scanner(System.in);
        numCourses= sc.nextInt();
        ArrayList<Edge>[]graph = new ArrayList[numCourses];
        for(int i=0; i<graph.length; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<numCourses; i++){
            int x; int y;
            x=sc.nextInt();
            y=sc.nextInt();
            createGraph(graph, x, y);
        }
        //System.out.print(findOrder(graph));// prints memory address
        System.out.println(Arrays.toString(findOrder(graph)));
    }
 */
    public static void createGraph(int[][]graph, int A, int B){
        graph[B][A] = 1; // there is an edge from B to A
    }
    public static void calcIndeg(int[][]graph, int[] indeg){
        for(int i=0; i<graph.length; i++){
            for(int j=0; j<graph.length; j++){
                if(graph[i][j] == 1){
                    indeg[j]++;
                }
            }
        }
    }
    public static int[] findOrder(int[][] graph){
        int[] ans = new int[graph.length];
        int[] indeg= new int[graph.length];
        calcIndeg(graph, indeg);
        int idx = 0;

        Queue<Integer>q = new LinkedList<>();
        for(int i=0; i<graph.length; i++){
            if(indeg[i] == 0){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int curr = q.remove();
            ans[idx++] = curr;
            for(int i=0; i<graph.length; i++){
                if(graph[curr][i] == 1){
                    indeg[i] --;
                    if(indeg[i] == 0){
                        q.add(i);
                    }
                }
            }
        }
        return ans;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numCourses = sc.nextInt();

        int graph[][] = new int[numCourses][numCourses];
        for(int i=0; i<numCourses; i++){
            int x=sc.nextInt();
            int y=sc.nextInt();
            createGraph(graph,x,y);
        }
        System.out.println(Arrays.toString(findOrder(graph)));
    }
}

