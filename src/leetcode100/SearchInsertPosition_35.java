package leetcode100;
// accepted :)
public class SearchInsertPosition_35 {
    public static int searchInsert(int[] nums,int target){
        int st = 0; int end = nums.length - 1;

        while(st <= end){
            int mid = (st + end)/2;
            if(nums[mid] == target) return mid;
            else if(target>nums[mid]) st = mid + 1;
            else end = mid - 1;
        }
        return st; // insert position
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        System.out.println(searchInsert(nums,2));
    }
}
