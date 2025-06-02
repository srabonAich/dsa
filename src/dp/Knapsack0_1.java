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

recursive solution
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

    public static void main(String[] args) {
        int[] val = {15,14,10,45,30};
        int[] wt = {2,5,1,3,4};
        int W = 7;
        System.out.println(knapsack(val,wt,W,val.length));
    }
}
