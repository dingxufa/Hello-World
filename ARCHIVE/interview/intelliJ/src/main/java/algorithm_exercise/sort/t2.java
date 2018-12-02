package algorithm_exercise.sort;

import java.util.Arrays;

/**
 * Created by ding on 2018/3/5.
 */
public class t2 {
    private static void exch(Comparable[] arr,int i,int j){
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    private static boolean less(Comparable a,Comparable b){
        return a.compareTo(b) <0;
    }

    public static void sort(Comparable[] arr){
        for(int i=1;i<arr.length;i++){
            for(int j=i;j>0&&less(arr[j],arr[j-1]);j--){
                exch(arr,j,j-1);
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args){
        sort(new Integer[]{34,1,3,5,7,9,11,13,17,19});
    }
}
