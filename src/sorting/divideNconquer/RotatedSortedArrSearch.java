package sorting.divideNconquer;
/*
sorted, rotated arraay with distinct numbers (in ascending order)
it is rotated at a pivot point. find the index of given element.
[4,5,6,7,0,1,2] target=0
output = 4
let arr = 1,2,3,4,5; pivot 3 ; after rotation: 4,5,1,2,3
** 2-1 dsa final qs **

=> take two lines 1 with 4567 2 with 012
case 1: mid on l1
        case a: search l1.left
        case b: search mid.right
case 2: mid on l2
        case c: search l2.right
        case d: search mid.left
 */

public class RotatedSortedArrSearch {  //modified binary search
    public static int search(int [] arr, int tar, int st, int end){
        if(st > end)
            return -1;

        int mid = (st + end)/2;
        if(arr[mid] == tar) return mid;
        //mid on l1
        if(arr[st] <= arr[mid]){
            //case a
            if(arr[st] <= tar && tar <= arr[mid])
                return search(arr, tar, st, mid); // search left
            //case b
            else
                return search(arr, tar, mid+1, end);//search right
        }
        //mid on l2
        else{
            //case c
            if(arr[mid] <= tar && tar <= arr[end])
                return search(arr, tar, mid+1, end);//search right
            //case d
            else
                return search(arr, tar, st, mid-1);//search left
        }
    }
    public static void main(String[] args) {
        int[] arr= {4,5,6,7,0,1,2};
        System.out.println(search(arr,6,0,arr.length-1));

    }
}
