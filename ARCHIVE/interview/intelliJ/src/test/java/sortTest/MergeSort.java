package sortTest;

import java.util.Arrays;

/**
 * Created by ding on 2018/2/27.
 */
public class MergeSort {

    public static void exch(Comparable[] arr,int i,int j){
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j]=t;
    }
    public static boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }

    public static void sort(Comparable[] arr){
        Comparable[] tmp = new Comparable[arr.length];
        sort(arr,0,arr.length-1,tmp);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(Comparable[] arr,int left,int right,Comparable[] tmp){
        if(left < right){
            int mid = (left + right ) /2;
            sort(arr,left,mid,tmp);
            sort(arr,mid+1,right,tmp);
            merge(arr,left,mid,right,tmp);
        }
    }

    private static void merge(Comparable[] arr,int left,int mid,int right,Comparable[] tmp){
        int i = left;
        int j = mid+1;
        int t = 0;
        while (i<=mid && j <= right){
            if(arr[i].compareTo(arr[j]) <= 0 ){
                tmp[t++] = arr[i++];
            }else{
                tmp[t++] = arr[j++];
            }
        }
        while(i<=mid){
            tmp[t++] = arr[i++];
        }
        while (j<=right){
            tmp[t++] = arr[j++];
        }

        t=0;
        while(left<=right){
            arr[left++] = tmp[t++];
        }
    }

    public static void main(String[] args){

        sort(new Integer[]{13,27,14,23,11,25,9});
    }
}
