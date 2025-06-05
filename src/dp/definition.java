package dp;
/*
DP is optimized recursion
how to identify?
->Optimal problem(min,max,largest,best solution)
->some choice is given(multiple branches in recursion tree)
 def: is a technique in programming that helps to
    efficiently solve a class of problems that have
    overlapping subproblems and optimal substructure
    property
two ways to perform DP
-> memoization / top down
    - Recursion is used
    - to solve subproblems, use storage
-> tabulation / bottom up
    -Iteration / loop is used
    -storage

to do tabulation
    -initialize(base case)
    -meaning of the index
    -filling the table small to big
the table is created based on how many variables are changing the
result.
the table can be an array, matrix, hashmap anything


to do memoization
    -1:create an array
    -2:before returning add the ans to dp array
    -3:check if the value for an index is pre computed or not.

 */


public class definition {
    // normal fibonacci using recursion
    public static int fib(int x){
        if(x <= 1 ) return x;
        return fib(x-1)+fib(x-2);
    }

    //using dp - memoization
    public static int fib(int x, int[]dp){
        if(x <= 1) return dp[x]=x;
        if(dp[x] != 0){ // fib[x] has already been calculated
            return dp[x];
        }
        dp[x] = fib(x-1,dp) + fib(x-2,dp);
        return dp[x];
    }

    public static void main(String[] args) {
        int[] dp = new int[5+1];
        //System.out.println(fib(5));
        System.out.println(fib(5,dp));
    }
}
