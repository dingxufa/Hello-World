package algorithm.test;

import java.util.Arrays;

/**
 * Created by ding on 2018/4/26.
 */
public class HeapSort {
    public static void exch(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    public static void sort(int[]arr){
        //1.
        for(int i=arr.length/2-1;i>=0;i--){
            adjust(arr,i,arr.length);
        }
        //2.
        for(int j=arr.length-1;j>0;j--){
            exch(arr,0,j);
            adjust(arr,0,j);
        }
    }

    public static void adjust(int[]arr,int i,int length){
        int t = arr[i];
        for(int k=2*i+1;k<length;k=2*k+1){
            if(k+1<length && arr[k+1]>arr[k]){
                k++;
            }
            if(arr[k] > t){//注这个t
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        arr[i]=t;
    }
    public static void main(String[] args){
        int []arr = {9,19,8,7,16,5,4,3,2,1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
