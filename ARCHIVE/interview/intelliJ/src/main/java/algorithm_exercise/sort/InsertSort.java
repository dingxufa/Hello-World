package algorithm_exercise.sort;

import java.util.Arrays;
import java.lang.Comparable;

/**
 * 插入排序
 * 插入排序的复杂度取决于数组的初始顺序，如果数组已经部分有序了，那么插入排序会很快。
 *
 平均情况下插入排序需要 ~N2/4 比较以及 ~N2/4 次交换；
 最坏的情况下需要 ~N2/2 比较以及 ~N2/2 次交换，最坏的情况是数组是逆序的；
 最好的情况下需要 N-1 次比较和 0 次交换，最好的情况就是数组已经有序了。
 插入排序对于部分有序数组和小规模数组特别高效。
 */
public class InsertSort {
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
/*        for(int i=0;i<arr.length;i++){  //不对 越界
            for(int j=i+1;j>0&&less(arr[j],arr[j-1]);j--){
                exch(arr,j,j-1);
            }
        }*/
        System.out.println(Arrays.toString(arr));
    }

/*    public static void main(String[] args){
        sort(new Integer[]{24,45,1,3,5,7,9,11,13,17,19});
    }*/



    public static void main(String[] args){
        sort(new Integer[]{34, 1, 3, 5, 7, 9, 11, 13, 17, 19});
    }
}
