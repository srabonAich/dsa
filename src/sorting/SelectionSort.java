package sorting;

/*
given an array [5,4,1,3,2]
sort using selection sort
*/

public class SelectionSort {
    public static void selectionSort(int arr[]){
        for(int i=0; i<arr.length-1; i++){
            int min=i;
            for(int j=i+1; j<arr.length;j++){
                if(arr[min] > arr[j]){
                    min=j;
                }
            }
            //swap
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }
    public static void main(String[] args) {
        int arr[] = {5,2,1,4,3};
        selectionSort(arr);
        for(int x: arr){
            System.out.print(x+" ");
        }
    }
}
