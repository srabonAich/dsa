package sorting;

public class InsertionSort {
    public static void insertionSort(int arr[]){
        for(int i = 1; i < arr.length; i++){
            int curr = arr[i];
            int prev = i-1;
            //finding out the correct position
            while(prev >= 0 && arr[prev] > curr){
                arr[prev+1] = arr[prev];
                prev--;
            }
            //insertion
            arr[prev+1] = curr;
        }
    }

    public static void main(String[] args) {
        int arr[] = {5,4,2,1,3};
        insertionSort(arr);
        for(int x: arr) System.out.print(x+" ");
    }
}
