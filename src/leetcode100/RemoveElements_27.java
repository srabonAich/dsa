package leetcode100;
//Accepted :)

public class RemoveElements_27 {
    public static int removeElement(int[] nums, int val){
        int newSize =0;
        for(int i=0; i<nums.length; i++){
            if(nums[i] != val){
                nums[newSize++] = nums[i];
            }
        }

        return newSize;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,2,3};
        System.out.println(removeElement(nums,3));
    }
}
