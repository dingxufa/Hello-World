package algorithm.test;

/**
 * Created by ding on 2018/4/25.
 */
public class BinarySearch {

    public static int search(int[] arr,int key){
        int i = 0;
        int j = arr.length-1;
        while(i<=j){
            int mid = (i+j)/2;
            if(arr[mid] < key){
                i = mid+1;
            }else if(arr[mid]>key){
                j = mid-1;
            }else{
                return mid;
            }
        }
        return -1;
    }
    public static void main(String[] args){

        //System.out.println(search(new int[]{1,3,5,7,9,11,13,17,19},-1));
        System.out.println(search(new int[]{1,3,5,7,9,11,13,17,19},19));

    }
}
