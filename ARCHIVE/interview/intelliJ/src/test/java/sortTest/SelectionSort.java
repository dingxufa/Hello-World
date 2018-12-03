package sortTest;

import java.util.Arrays;

/**
 * Created by ding on 2018/2/27.
 */
public class SelectionSort {
    public static void exch(Comparable[] arr,int i,int j){
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j]=t;
    }
    public static boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }

    public static void sort(Comparable[] arr){
        for(int i=0;i<arr.length;i++){
            int min = i;
            for(int j=i+1;j<arr.length;j++){
                if(less(arr[j],arr[min])){
                    min = j;
                }
            }
            exch(arr,i,min);
        }
    }

    public static void main(String[] args){
        Integer[] arr = {1,2,3,4,5,6,7};//{3, 12, 2, 45, 13, 14, 2, 6, 9, 7};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
