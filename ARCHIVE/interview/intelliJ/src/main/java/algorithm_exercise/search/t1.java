package algorithm_exercise.search;

/**
 * Created by ding on 2018/3/5.
 */
public class t1 {
    public static int search(int[] arr,int key){
        int low = 0;
        int high = arr.length-1;
        while(low <= high){
            int mid = (low + high)/2;
            if(arr[mid] < key){
                low = mid +1;
            }else if(arr[mid] > key){
                high = mid -1;
            }else{
                return mid;
            }
        }
        return -1;
    }

}
