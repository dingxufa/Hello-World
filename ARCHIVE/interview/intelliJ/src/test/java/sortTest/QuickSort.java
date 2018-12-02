package sortTest;

import java.util.Arrays;

/**
 * Created by ding on 2018/2/27.
 */
public class QuickSort {
    private static void exch(Comparable[] arr,int i,int j){
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    private static boolean less(Comparable a,Comparable b){
        return a.compareTo(b) <0;
    }

    public static void sort(Comparable[]arr){
        sort(arr,0,arr.length-1);
    }

    private static void sort(Comparable[] arr,int low,int high){
        if( low >= high){
            return;
        }
        int j = partition(arr,low,high);
        sort(arr,low,j-1);//
        sort(arr,j+1,high);
    }

    private static int partition(Comparable[] arr,int low,int high){
        int i = low;
        int j = high+1;
        Comparable t = arr[low];
        while (true){
            while (less(arr[++i],t)){ if(i==high)break;}
            while(less(t,arr[--j])){ if(j==low)break;}
            if(i >= j){ break;}
            exch(arr,i,j);
        }
        exch(arr,low,j);
        return j;
    }

    public static void main(String[] args){
//        Integer[] arr = {1, 3, 5, 7, 9, 11, 13, 17, 19};
        Integer[] arr = {23,1, 13, 5, 17, 9, 11, 13, 17, 19};
        sort(arr);
        System.out.println(Arrays.toString(arr));

    }

}
