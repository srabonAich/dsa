package dp;
/*
statement:
numbers[] = 4, 2, 7, 1, 3 target sum = 10 is there exists any subset of numbers whose sum of elements is 10

variation of 0/1 knapsack
similarities between knapsack and targetsum
    -choice of elements : means that an element wants to include or not
    -consider the targetsum as the maximum weight of knapsack
    -assume the value and weight of an element is the number itself
create a table. row 5+1 column 10+1
each cell (i, j) means that including i items can their sum be j? store true of false
 */
public class TargetSum {
    public static boolean targetSum(int[] nums, int sum){
        boolean[][] dp = new boolean[nums.length + 1][sum+1];
        for(int i=0; i<=nums.length; i++)
            dp[i][0] = true;
        for(int i=1; i<=nums.length; i++){
            for(int j=1; j<=sum; j++){
                if(nums[i-1] <= j && dp[i-1][j-nums[i-1]] == true){
                    dp[i][j] = true;
                }
                else if(dp[i-1][j]) dp[i][j] = true;
            }
        }
        return dp[nums.length][sum];
    }
    public static void main(String[] args) {
        int[] numbers = {4,2,7,1,3};
        int Sum = 10;
        System.out.println(targetSum(numbers,Sum));
    }
}
