package recursion;
/*
Tiling Problem
given a "2*n" floor and tiles of size "2*1", count the number
of ways to tile the given floor using the 2*1 tiles
(A tile can either be placed horizontally or vertically)
E.g. n = 3 floor = 2*3
width = 3 height = 2
way 1: 3 tiles vertically; 1*3 = 3
way 2: 1st 2 tile horizontally last vertically
way 3: 1st tile vertically last 2 horizontally
 */


public class TilingProblem {
    public static int tilingProblem(int n){
        if(n == 0 || n == 1) return 1;
        return tilingProblem(n-1) + tilingProblem(n-2);
    }

    public static void main(String[] args) {
        System.out.println(tilingProblem(4));
    }
}
