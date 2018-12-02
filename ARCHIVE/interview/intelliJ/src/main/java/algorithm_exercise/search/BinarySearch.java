package algorithm_exercise.search;

/**
 * 二分查找
 */
public class BinarySearch {
    public static int search(int[] arr,int key){
        int N = arr.length;
        int low = 0;
        int high = arr.length-1;
        while(low <= high){
            int mid = (low + high)/2;
            System.out.println("mid="+mid);
            if(arr[mid] < key){
                low = mid+1;
            }else if(arr[mid]>key){
                high = mid -1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    public static int search(Comparable[] arr,Comparable key){
        int low = 0;
        int high = arr.length-1;
        while(low <= high){
            int mid = (low + high)/2;
            if(key.compareTo(arr[mid])< 0){
                high = mid-1;
            }else if(key.compareTo(arr[mid])>0){
                low = mid+1;
            }else{
                return mid;
            }
        }
      return -1;
    }

    public static void main(String[] args){

        //System.out.println(search(new int[]{1,3,5,7,9,11,13,17,19},-1));
        System.out.println(search(new Integer[]{1,3,5,7,9,11,13,17,19},-1));

    }
}
