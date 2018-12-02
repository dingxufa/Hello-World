package algorithm.test;

import java.util.Arrays;

/**
 * Created by ding on 2018/4/26.
 */
public class Bubble {
    public static void exch(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    public static void sort(int[] arr){
        for(int i=0;i<arr.length-1;i++){
            boolean sorted = true;
            for(int j=0;j<(arr.length-1-i);j++){
                if(arr[j]>arr[j+1]){
                    exch(arr,j,j+1);
                    sorted = false;
                }
            }
            if (sorted){break;}
        }
        System.out.println( Arrays.toString(arr));
    }
    public static void main(String[] args){
        //sort(new Integer[]{24,45,1,3,5,7,9,11,13,17,19});
        sort(new int[]{23,5,37,6,8,9,10});
    }

}
