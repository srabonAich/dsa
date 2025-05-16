package sorting;

import java.util.Arrays;

public class InbuiltSort {
    public static void main(String[] args) {
        int arr[] = {5,4,2,1,3};
//        Arrays.sort(arr);
//        for(int x: arr) System.out.print(x+" ");
        int arr1[] = {2,6,3,4,1};
        Arrays.sort(arr1,0,3);//2 3 6 4 1
        for(int x: arr1) System.out.print(x+" ");
    }
}
