package graphs;

import java.util.*;

public class lab_3 {
    Map<Integer, List<Integer>> adj;
    boolean[] vis;
    Stack<Integer> s = new Stack<>();
    public lab_3(int V){
        adj = new HashMap<>();
        vis = new boolean[V];
    }
    void addVetex(int src){
        adj.putIfAbsent(src,new ArrayList<>());
    }
    void addEdge(int src, int des){
        adj.putIfAbsent(src, new ArrayList<>());
        adj.putIfAbsent(des, new ArrayList<>());
        adj.get(src).add(des); //directed
    }
    public void printGraph(){
        for(Integer i: adj.keySet()){
            System.out.print(i+" -> ");
            System.out.println(adj.get(i));
        }
    }
    public void topsort(){
        Arrays.fill(vis,false);
        for(int i: adj.keySet()){
            if(!vis[i]){
                topoSortUtil(i);
            }
        }
    }
    public void topoSortUtil(int curr){
        vis[curr]=true;
        if(!adj.containsKey(curr)) return;
        for(int neighbor: adj.get(curr)){
            if(!vis[neighbor]){
                topoSortUtil(neighbor);
            }
        }
        s.push(curr);
    }
    public void dfs(int curr,Map<Integer,List<Integer>> transpose){
        vis[curr] = true;
        if(!transpose.containsKey(curr)) return;
        for(int neighbor: transpose.get(curr)){
            if(!vis[neighbor]){
                dfs(neighbor,transpose);
            }
        }
    }
    public int SCC(){
        topsort();
        Map<Integer, List<Integer>> transpose = new HashMap<>();
        for(Integer v: adj.keySet()){
            transpose.putIfAbsent(v,new ArrayList<>());
        }
        for(Map.Entry<Integer,List<Integer>> entry: adj.entrySet()){
            Integer des = entry.getKey();
            for(Integer src: entry.getValue()){
                transpose.putIfAbsent(src, new ArrayList<>());
                transpose.get(src).add(des);
            }
        }
        Arrays.fill(vis,false);
        int count =0;
        while(!s.isEmpty()){
            int curr = s.pop();
            if(!vis[curr]){
                dfs(curr,transpose);
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int V,E;
        Scanner sc = new Scanner(System.in);
        V=sc.nextInt();
        E=sc.nextInt();
        lab_3 graph = new lab_3(V);
        for(int i=0; i<V; i++){
            graph.addVetex(i);
        }
        int A, B;
        for(int i=0; i<E; i++){
            A = sc.nextInt();
            B = sc.nextInt();
            graph.addEdge(A,B);
        }
        System.out.println("# of strongly Connected Components: "+graph.SCC());
    }
}
