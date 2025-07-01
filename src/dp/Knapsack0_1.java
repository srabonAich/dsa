package dp;
/*
knapsack means a bag, which I need to fill with products having
certain values. unlike fractional knapsack, only full products can
be added in the bag. so the challenge is to fill the bag with
products having maximum profit
val[]=15,14,10,45,30
wt[]=2,5,1,3,4
W(total allowed weight) = 7
ans = max profit

===========__recursive solution__=========
knapsack(val[],wt[],W,idx i)
for each product I have two choices: include , exclude
        if(wt <= W) // valid product
            ->include - call for W=W-wt, i-1
            ->exclude - call for W, i-1
        else // invalid product
            ->exclude - call for W, i-1
base case
    two things are changing W(capacity) and index i
    if W==0 -> ans = 0
    if i==0 -> ans = 0 //recursion will go from n to 0


=========____solve using Memoization____===========
in my knapsack function, there are 4 parameters. Among them only 2 are variable
So I have to create a 2D array to memoize.
        int[][] dp = new int[n+1][W+1]
why n+1 and W+1? not n and W?
cz the Base Case checks if n == 0 and W == 0, in memoization,
the base case also gets included as Initialization step

****_What does the 2D matrix signifies?_****
every cell (i,j) bole je oi W er bag size jnno shob possible item theke
maximum koto profit ber hobe.

-> initialize every cell with -1
-> final answer will be at cell (n+1,W+1)



=======____solve using Tabulation____======
->make a 2D matrix
    int[][] dp = new int[n+1][W+1] ---- same logic
->meaning of the table ---- same
    every i,j cell tells the max profit of
    'i' items having 'j' size left in the bag
->init the table - base case
    0th row and 0th column initialized by 0
->fill (bottom to up)
    for(int i=1 to n+1) -- dp array is from 0 to n+1
                        -- 0 is initialized
      for(int j=1 to j+1)
           if(wt<=j) // valid items
              case1 include the item
                val[i-1] + dp[i-1][j-wt]
                 // why i-1? dp er ith index
                    val er i-1 th index er shoman
                 // include korar por ja wt baki thakbe
                    tar jnno max profit koto ta oi item
                    er value er shathe add koro
              case2 exclude the item
                dp[i-1][j]
                 // exclude korle wt change hobe na

            Max of case1 and case2
            dp[i][j]=max(val[i-1]+dp[i-1][j-wt],dp[i-1][j])

            else  // invalid


 */
public class Knapsack0_1 {
    public static int knapsack(int[]val,int[]wt,int W,int n){
        if(W==0 || n==0) return 0;
        if(wt[n-1] <= W) {
            //include
            int ans1=val[n-1]+knapsack(val, wt,W - wt[n - 1],n - 1);
            //exclude
            int ans2=knapsack(val,wt,W,n-1);
            return Math.max(ans1,ans2);
        }else // not valid
            return knapsack(val,wt,W,n-1);
    }

    // memoization
    public static int knapsack(int[]val,int[]wt,int W,int n,int[][] dp){
        if(W==0 || n==0) return 0;

        if(dp[n][W] != -1) return dp[n][W];

        if(wt[n-1] <= W) {
            //include
            int ans1=val[n-1]+knapsack(val,wt,W-wt[n-1],n-1,dp);
            //exclude
            int ans2=knapsack(val,wt,W,n-1,dp);
            dp[n][W] = Math.max(ans1,ans2);
            return dp[n][W];
        }else {// not valid
            dp[n][W] = knapsack(val,wt,W,n-1,dp);
            return dp[n][W];
        }
    }

    // tabulation
    public static int knapsackTab(int[]val, int[]wt, int W){
        int n = val.length;
        int [][] dp = new int[n+1][W+1];
//        for(int i=0; i<dp.length; i++){
//            dp[i][0] = 0;
//        }
//        for(int j=0; j<dp[0].length; j++){
//            dp[0][j] = 0;
//        }
        for(int i=0; i<=n; i++){
            for(int j=0; j<=W; j++){

                if(i == 0 || j==0) dp[i][j] = 0;
                else if(wt[i-1] <= j){
                    dp[i][j] = Math.max(val[i-1] + dp[i-1][j-wt[i-1]] , dp[i-1][j]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][W];
    }

    public static void main(String[] args) {
        int[] val = {0,15,14,10,45,30};
        int[] wt = {0,2,5,1,3,4};
        int W = 7;
//        int[][] dp = new int[val.length+1][W+1];
//        for(int i=0; i< dp.length; i++){
//            for(int j=0; j<dp[0].length;j++){
//                dp[i][j] = -1;
//            }
//        }
        //System.out.println(knapsack(val,wt,W,val.length,dp));
        System.out.println(knapsackTab(val,wt,W));
    }
}
