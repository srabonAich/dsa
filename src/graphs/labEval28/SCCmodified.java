package graphs.labEval28;

import java.util.*;
/*
You said:
There are m teleporters connecting the n planets in a game. When there is a path from a to
b and from b to a, two planets, a and b, are in the same kingdom. You have to identify the
kingdom of each planet.
Input
The number of planets and teleporters is indicated by the two integers n and m on the first
input line. Numbers 1,2,…,n are the planets' numbers.
The teleporters are then described in m lines. There are two integers on each line, a and b.
You can use a teleporter to go from planet a to planet b.
Output
The number of kingdoms, k, should be printed first. Next, print a kingdom label between 1
and k for every planet. Any valid answer can be printed.
Constraints
● 1≤n≤10^5
● 1≤m≤2*10^5
● 1≤a,b≤n
Sample Input
5 6
1 2
2 3
3 1
3 4
4 5
5 4
Sample Output
2
1 1 1 2 2
 */
public class SCCmodified {
        List<List<Integer>> adj;
        Set<Integer> vis;
        Stack<Integer> s = new Stack<>();
        int V;
        int[] component;
        public SCCmodified(int V){
            this.V = V;
            adj = new ArrayList<>();
            vis = new HashSet<>();
            component = new int[V+1];
            for (int i = 0; i <= V; i++) {
                adj.add(new ArrayList<>());
            }
        }

        void addEdge(int src, int des){
            adj.get(src).add(des); //directed
        }
        public void printGraph(){
            for (int i = 1; i <= V; i++) {
                System.out.print(i + " -> ");
                System.out.println(adj.get(i));
            }
        }
        public void topsort(){
            vis.clear();
            for(int i = 1; i <= V; i++){
                if(!vis.contains(i)){
                    topoSortUtil(i);
                }
            }
        }
        public void topoSortUtil(int curr){
            vis.add(curr);
            for(int neighbor: adj.get(curr)){
                if(!vis.contains(neighbor)){
                    topoSortUtil(neighbor);
                }
            }
            s.push(curr);
        }
        public void dfs(int curr,List<List<Integer>> transpose,int label){
            vis.add(curr);
            component[curr] = label;
            for(int neighbor: transpose.get(curr)){
                if(!vis.contains(neighbor)){
                    dfs(neighbor,transpose,label);
                }
            }
        }
        public int SCC(){
            topsort();

            List<List<Integer>> transpose = new ArrayList<>();
            for (int i = 0; i <= V; i++) {
                transpose.add(new ArrayList<>());
            }
            for (int u = 1; u <= V; u++) {
                for (int v : adj.get(u)) {
                    transpose.get(v).add(u);
                }
            }

            vis.clear();
            int count =0;
            while(!s.isEmpty()){
                int curr = s.pop();
                if(!vis.contains(curr)){
                    count++;
                    dfs(curr,transpose,count);
                }
            }
            return count;
        }

        public static void main(String[] args) {
            int V,E;
            Scanner sc = new Scanner(System.in);
            V=sc.nextInt();
            E=sc.nextInt();
            SCCmodified graph = new SCCmodified(V);

            int A, B;
            for(int i=1; i<=E; i++){
                A = sc.nextInt();
                B = sc.nextInt();
                graph.addEdge(A,B);
            }
            System.out.println(graph.SCC());
            for(int i=1; i<=V; i++){
                System.out.print(graph.component[i]+" ");
            }
        }
}
