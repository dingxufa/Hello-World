package algorithm.search;

/**
 * Created by ding on 2018/2/25.
 */
public class BinarySearch {

    public static  int  sort(int[] arr,int key){
        int low = 0;
        int high = arr.length-1;
        while(low <= high){
            int mid = (low + high)/2;
            if(arr[mid]<key){
                low = mid+1;
            }else if(arr[mid]>key){
                high = mid-1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        int index = sort(new int[]{1, 3, 5, 7, 8, 12, 24, 56}, 13);
        System.out.println(index);
    }

}
