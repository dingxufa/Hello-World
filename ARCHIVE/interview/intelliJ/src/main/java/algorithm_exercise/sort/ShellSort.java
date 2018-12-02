package algorithm_exercise.sort;

import java.util.Arrays;

/**
 * 基尔排序(优化版的插入排序)
 * 对于大规模的数组，插入排序很慢，因为它只能交换相邻的元素，如果要把元素从一端移到另一端，就需要很多次操作。
 希尔排序的出现就是为了改进插入排序的这种局限性，它通过交换不相邻的元素，使得元素更快的移到正确的位置上。
 希尔排序使用插入排序对间隔 h 的序列进行排序，如果 h 很大，那么元素就能很快的移到很远的地方。
 通过不断减小 h，最后令 h=1，就可以使得整个数组是有序的。
 */
public class ShellSort {
    private static void exch(Comparable[] arr,int i,int j){
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    private static boolean less(Comparable a,Comparable b){
        return a.compareTo(b) <0;
    }

    public static void sort(Comparable[] arr){
        int N = arr.length;
        int h =1;
        while(h < N/3 ){
            h = 3*h+1;//1,4,13,40....
        }
        System.out.println("N="+N+" h="+h);
        while(h>=1){
            for(int i=h;i<arr.length;i++){
                System.out.println("time===========i="+i+",h="+h);
                for(int j=i;j>=h&&less(arr[j],arr[j-h]);j-=h){
                    exch(arr,j,j-h);
                }
            }
            h/=3;
        }
        System.out.println(Arrays.toString(arr));
    }
    public static void main(String[] args){

        sort(new Integer[]{13,27,14,23,11,25,9});
    }
}
