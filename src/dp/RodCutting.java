package dp;

/*
statement : given a rod of length n and an array of prices that includes prices of all pieces of size smaller than n.
determine the maximum value obtainable by cutting up the rod and selling the pieces

* can be done applying the knapsack strategy.
think the length is the max weight we have to fill. the lengths of the array are the weight of each element.
the prices are the values of elements.
so now find the lengths to fill the n length so that the price is maximum. this indeed is the knapsack problem

this is unbounded knapsack

 */
public class RodCutting {
    public static int rodcut(int[] length, int[] price, int rod){
        int n = length.length;
        int[][] dp = new int[n+1][rod+1];
        for(int i=0; i<=n; i++){
            for(int j=0; j<=rod; j++){
                if(i == 0 || j == 0)
                    dp[i][j] = 0;
                else if(length[i-1] <= j)
                    dp[i][j] = Math.max(price[i-1] + dp[i][j-length[i-1]] , dp[i-1][j]);
                else
                    dp[i][j] = dp[i-1][j];
            }
        }
        return dp[n][rod];
    }
    public static void main(String[] args) {
        int[] length = {1,2,3,4,5,6,7,8};
        int[] price = {1,5,8,9,10,17,17,20};
        int rod = 8;
        System.out.println(rodcut(length,price,rod));
    }
}
