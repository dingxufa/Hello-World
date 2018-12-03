package algorithm_exercise.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 选择排序需要 ~N2/2 次比较和 ~N 次交换，它的运行时间与输入无关，
 * 这个特点使得它对一个已经排序的数组也需要这么多的比较和交换操作。
 */
public class SelectionSort {
    private static void exch(Comparable[] arr,int i,int j){
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    private static boolean less(Comparable a,Comparable b){
        return a.compareTo(b) <0;
    }

    public static void sort(Comparable[]arr){
        for(int i=0;i<arr.length-1;i++){//arr.length
            int min=i;
            for(int j=i+1;j<arr.length;j++){//j=i+1
                if(less(arr[j],arr[min])){
                   min = j;
                }
            }
            if(i!=min){
                System.out.println("exch(i="+i+",min="+min+")");
                exch(arr,i,min);
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args){
        sort(new Integer[]{24,45,1,3,5,7,9,11,13,17,19,4});
    }
//    public static void main(String[] args){
//        sort(new Integer[]{1,3,5,7,9,11,13,17,19});
//    }
}
