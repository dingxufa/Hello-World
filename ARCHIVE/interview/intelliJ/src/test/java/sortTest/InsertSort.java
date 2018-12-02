package sortTest;

import java.util.Arrays;

/**
 * Created by ding on 2018/2/27.
 */
public class InsertSort {
    public static void exch(Comparable[] arr,int i,int j){
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j]=t;
    }
    public static boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }

    public static void sort(Comparable[] arr){
        for(int i=1;i<arr.length;i++){
            for(int j=i;j>0;j--){
                if(less(arr[j],arr[j-1])){
                    exch(arr,j,j-1);
                }
            }
        }
    }
    public static void main(String[] args){
//        Integer[] arr = {3, 12, 2, 45, 13, 14, 2, 6, 9, 7};
        Integer[] arr = {1,2,3,4,5,6,7};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
