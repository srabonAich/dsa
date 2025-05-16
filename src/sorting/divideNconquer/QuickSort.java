package sorting.divideNconquer;

public class QuickSort {
    public static void quickSort(int[] arr, int st, int end){
        if(st >= end) return;
        //find pivot = last element
        int pivotidx = partition(arr, st, end);
        quickSort(arr, st, pivotidx-1);//left
        quickSort(arr, pivotidx+1, end);//right
    }
    public static int partition(int[] arr, int st, int end){
        int pivot = arr[end];
        int i = st - 1; // to make room for elements smaller than pivot
        for(int j=st; j<end; j++){
            if(arr[j] <= pivot){
                i++;
                //swap
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        //bring pivot to right pos
        i++;
        int temp = pivot;
        arr[end] = arr[i];
        arr[i] = temp;
        return i;
    }
    public static void main(String[] args){
        int[] arr = {6,3,9,8,2,5};
        quickSort(arr, 0, arr.length-1);
        for(int x: arr)
            System.out.print(x+" ");
    }
}
