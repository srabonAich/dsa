package graphs.labEval28;

import java.util.*;
/*
Sylhet is one of the most beautiful cities in Bangladesh during the monsoon season and
Sharara has decided to explore Sylhet in the monsoon season. But it is difficult for her to
see as she navigates Sylhet in her car on a cloudy day. She has 𝐾 units of petrol left in her
car, and she can go one unit farther with each unit of fuel.
Given Sylhet’s layout, which is made up of several interconnected cities, and her current
location, your goal is to calculate and print the maximum number of cities she can visit until
she runs out of fuel.
Input
● The input starts with four integers:
○ C: The number of cities in Sylhet.
○ R: The number of roads connecting different cities in Sylhet
○ 𝐾: The remaining fuel units in Sharara's car.
○ L : Sharara's current city
● The following R lines describe the road connections between cities as pairs of
integers 𝑢,𝑣, indicating a bidirectional road between cities 𝑢 and 𝑣
Output
Output a single integer representing the total possible number of cities Sharara can reach if
she uses up all 𝐾 units of her fuel.
Constraints:
2≤C≤10^3
0≤R≤(C∗(C−1))/2
1≤𝐾≤10^6
1≤L≤C
Sample Input
5 5 2 1
5 3
2 4
3 4
1 5
2 5
sample output 4
Explanation:
1. Sharara starts in city 1 with 2 units of fuel.
2. She can move to city 5 using 1 unit of fuel (remaining fuel = 1).
○ From city 5, she can reach city 3 using 1 unit of fuel (remaining fuel = 0).
3. Alternatively, from city 5, she can reach city 2 using 1 unit of fuel (remaining fuel =
0).
At this point, Sharara has 0 units of fuel and has reached cities 3 and 2. There are no
further cities she can reach with her remaining fuel.
The total number of cities she can reach is {1, 5, 3, 2} through all possible paths.
So, the answer for the sample input, considering all possible paths, is 4.
 */
public class BFSmodified {
    int V;
    Set<Integer> vis ;
    Queue<int[]> q;
    List<List<Integer>> graph = new ArrayList<>();
    public BFSmodified(int V){
        this.V = V;
        q = new LinkedList<>();
        vis = new HashSet<>();
        for(int i=0; i<=V; i++){
            graph.add(new ArrayList<>());
        }
    }
    public void addEdge(int u, int v){
        graph.get(u).add(v);
        graph.get(v).add(u);
    }
    public void bfs(int L, int K){
        q.add(new int[]{L,K});
        vis.add(L);

        while(!q.isEmpty()){
            int[] curr = q.remove();
            int currCity = curr[0];
            int fuelLeft = curr[1];

            if(fuelLeft == 0) continue;

            for(int neighbor: graph.get(currCity)){
                if(!vis.contains(neighbor)){
                    vis.add(neighbor);
                    q.add(new int[]{neighbor,fuelLeft-1});
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int C = sc.nextInt();
        int R = sc.nextInt();
        int K = sc.nextInt();
        int L = sc.nextInt();
        BFSmodified graph = new BFSmodified(C);
        for(int i=0; i<R; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.addEdge(u,v);
        }
        graph.bfs(L,K);
        System.out.println(graph.vis.size());
    }
}
