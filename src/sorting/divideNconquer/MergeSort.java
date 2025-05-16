package sorting.divideNconquer;

public class MergeSort {
    public static void merge(int[] arr, int st, int mid, int end){
        int[] temp = new int[end - st +1];
        int i = st;
        int j = mid +1;
        int k = 0;
        while(i<=mid && j<=end){
            if(arr[i] < arr[j])
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }
        while(i <= mid)
            temp[k++] = arr[i++];
        while(j <= end)
            temp[k++] = arr[j++];
        for(k = 0, i = st; k < temp.length; k++, i++){
            arr[i] = temp[k];
        }
    }
    public static void mergeSort(int[] arr, int st, int end){
        if(st >= end) return;

        int mid = (st + end) / 2;
        mergeSort(arr, st, mid);
        mergeSort(arr, (mid+1), end);
        merge(arr, st, mid, end);
    }

    public static void main(String[] args) {
        int[] arr ={6, 3, 9, 5, 2, 8};
        mergeSort(arr, 0, arr.length-1);
        for(int x: arr)
            System.out.print(x+" ");
    }
}
