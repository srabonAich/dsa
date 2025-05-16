package sorting;
/*
given an unsorted array [5,2,1,4,3]
sort using Bubble Sort
 */

public class BubbleSort{
    public static void bubbleSort(int arr[]){
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length-1-i; j++) {
                if (arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
    public static void main(String[] args) {
        int arr[] = {5,2,1,4,3};
        bubbleSort(arr);
        for(int x: arr){
            System.out.print(x+" ");
        }
    }
}