package sorting;

public class CountSort {

    public static void countSort(int arr[]){
        int largest = Integer.MIN_VALUE;
        for(int i: arr)
            largest = Math.max(largest,i);
        int count[] = new int[largest+1];
        for(int i = 0; i<arr.length; i++){
            count[arr[i]]++;
        }
        //sorting
        int j = 0;
        for(int i=0; i<count.length; i++){
            while(count[i] > 0){
                arr[j] = i;
                j++;
                count[i]--;
            }
        }
    }

    public static void main(String[] args) {
        int arr[] = {5,4,2,1,3};
        countSort(arr);
        for(int x: arr) System.out.print(x+" ");
    }
}
