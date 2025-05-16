package sorting.divideNconquer;
/*
Count the inversions in the given array={3,5,6,9,1,2,7,8}
Two elements a[i] and a[j] form an inversion if a[i] > a[j] && i < j
output: 10
inversions: 3,1; 3,2; 5,1; 5,2; 6,1; 6,2; 9,1; 9,2; 9,7; 9,8
 */
public class CountInversion {
    public static int bruteForce(int[] arr){ //O(n^2)
        int inv = 0;
        for(int i = 0; i<arr.length; i++){
            for(int j = i+1; j<arr.length; j++){
                if(arr[i] > arr[j]) inv++;
            }
        }
        return inv;
    }
    public static int merge(int[] arr, int st, int mid, int end){
        int inv = 0;
        int[] temp = new int[end - st + 1];
        int i = st; int j = mid + 1; int k = 0;
        while(i <= mid && j <= end){
            if(arr[i] < arr[j]) temp[k++] = arr[i++];
            else {
                temp[k++] = arr[j++];
                inv += mid - i + 1;
            }
        }
        while(i <= mid) temp[k++] = arr[i++];
        while(j <= end) temp[k++] = arr[j++];

        for(i=st,k=0; k<temp.length; i++,k++) arr[i] = temp[k];
        return inv;
    }
    public static int countInversion(int[] arr, int st, int end){
        if(st<end) {
            int mid = st + (end - st)/2;
            int leftInvCount = countInversion(arr, st, mid);
            int rightInvCount = countInversion(arr, mid+1, end);
            int invCount = merge(arr, st, mid, end);
            return leftInvCount + rightInvCount + invCount;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] arr = {3,5,6,9,1,2,7,8};
        //System.out.println(bruteForce(arr));
        System.out.println(countInversion(arr,0,arr.length-1));
    }
}
