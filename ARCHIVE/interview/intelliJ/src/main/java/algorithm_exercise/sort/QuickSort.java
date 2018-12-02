package algorithm_exercise.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 时间复杂度：最好 O(n)=O(nlogn)；平均O(n)=O(nlogn)；最差O(n)=O(n2)
 空间复杂度：O(n)=O(n)
 稳定性：    不稳定
 */
public class QuickSort {
    private static void exch(Comparable[] arr,int i,int j){
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    //left是绝对不能大于right的
    private static boolean less(Comparable a,Comparable b){
        return a.compareTo(b) <0;
    }
    public static void sort(Comparable[] arr){
        sort(arr,0, arr.length-1);
    }
    private static void sort(Comparable[]arr,int low,int high){
        if(low >= high){return;}
        int j = partition(arr,low,high);
        sort(arr, low, j - 1);
        sort(arr, j + 1, high);
       /* if(low < high){ //
            int j = partition(arr,low,high);
            sort(arr,low,j-1);
            sort(arr,j+1,high);
        }*/
    }
    private static int partition(Comparable[]arr,int low,int high){
        int i=low;
        int j=high+1;
        Comparable t = arr[low];
        while(i<j ){//i<high&&j>low){  //不能用 i<=j，这个是不一定的有可能i>=j;
            while(less(arr[++i],t)){if(i==high) break;}
            while(less(t,arr[--j])){ if(j==low) break;}
            if (i>=j ){break;}
            exch(arr,i,j);
        }
        System.out.println("exch(low="+low+",j="+j+",i="+i);
        exch(arr,low,j);//不能是i
        return j;
    }

    private static int partion2(Comparable[] arr,int left,int right){
        int i = left;
        int j = right+1;
        Comparable t = arr[i];
        while(i<j){
            while(less(arr[++i],t)){ if(i==right){break;}}
            while(less(t,arr[--j])){if(j==left){break;}}
            if(i>=j){ break;}
            exch(arr,i,j);
        }
        exch(arr,left,j);
        return j;
    }
    public static void main(String[] args){
       Integer[] arr = {1, 3, 5, 7, 9, 11, 13, 17, 19,5};
//        Integer[] arr = {23,1, 13, 5, 17, 9, 11, 13, 17, 59};
        sort(arr);
        System.out.println(Arrays.toString(arr));

    }
}
