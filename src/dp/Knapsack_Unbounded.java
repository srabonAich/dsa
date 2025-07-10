package dp;
/*
same as 01 knapsack. just one key difference is we can add one item multiple times
Time complexity n*W
 */
public class Knapsack_Unbounded {
    public static int unbounded(int[] val, int[] wt, int w){
        int n = wt.length;
        int[][] dp = new int[n+1][w+1];
        for(int i=0; i<=n; i++){
            for(int j=0; j<=w; j++){
                if(i == 0 || j==0)
                    dp[i][j] = 0;
                else if(wt[i-1] <= j)
                    dp[i][j] = Math.max(val[i-1] + dp[i][j-wt[i-1]], dp[i-1][j]);
                else
                    dp[i][j] = dp[i-1][j];
            }
        }
        return dp[n][w];
    }

    public static void main(String[] args) {
        int[] val = {0,15,14,10,45,30};
        int[] wt = {0,2,5,1,3,4};
        int W = 7;
        System.out.println(unbounded(val, wt, W));
    }
}
