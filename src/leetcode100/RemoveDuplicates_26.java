package leetcode100;
// accepted :)
public class RemoveDuplicates_26 {
    public static int removeDuplicates(int[] nums){
        int slow = 0; // pointer for the new array size
        int fast = 1;
        for(fast=1; fast<nums.length; fast++){
            if(nums[slow] != nums[fast]){ //new element found
                slow++;
                nums[slow] = nums[fast]; // overwrite with the unique element
            }
        }
        return slow+1;
    }

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(nums));
    }
}
