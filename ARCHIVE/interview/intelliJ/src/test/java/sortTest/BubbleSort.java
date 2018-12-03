package sortTest;

import java.util.Arrays;

/**
 * Created by ding on 2018/2/27.
 */
public class BubbleSort {
    public static void exch(Comparable[] arr,int i,int j){
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j]=t;
    }
    public static boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }
    public static void sort(Comparable[] arr){
        for(int i=0;i<arr.length-1;i++){
            boolean sorted = true;
            for(int j=0;j<arr.length-1-i;j++){
                if(less(arr[j+1],arr[j])){
                    exch(arr,j,j+1);
                    sorted = false;
                }
            }
            if (sorted){ break;}
        }
    }
    public static void main(String[] args){
        Integer[] arr = {3, 12, 2, 45, 13, 14, 2, 6, 9, 7};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
