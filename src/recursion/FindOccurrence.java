package recursion;
/*
write a function to find the first occurrence of a key in an array
 */
public class FindOccurrence {
    public static int firstOccurrence(int arr[], int key, int i){
        if(i==arr.length) return -1;
        if(arr[i] == key) return i;
        return firstOccurrence(arr, key, i+1);
    }
    public static int lastOccurrence(int arr[], int key, int i){
        if(i==arr.length) return -1;
        int result = lastOccurrence(arr, key, i+1);
        if(result != -1) return result;
        if(arr[i] == key) return i;
        return result;
    }

    public static void main(String[] args) {
        int arr[] = {2,4,5,1,6,7,5,2};
        System.out.println(firstOccurrence(arr,5,0));
    }
}
