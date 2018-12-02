package sortTest;

import java.util.Arrays;

/**
 * Created by ding on 2018/2/27.
 */
public class ShellSort {
    public static void exch(Comparable[] arr,int i,int j){
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j]=t;
    }
    public static boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }

    public static void sort(Comparable[] arr){
        int N = arr.length;
        int h = 1;
        while(h<N/3){
            h = 3 * h + 1; //1,4,13,40...
        }

        while(h>=1){
            for(int i=h;i<arr.length;i++){
                System.out.println("====="+Arrays.toString(arr)+" i="+i+" h="+h);
               /* for(int j=i; j>=h&&less(arr[j],arr[j-h]); j-=h){
                    System.out.println("exch(j="+j+",j-h="+(j-h)+")");
                    exch(arr,j,j-h);
                }*/
                for(int j=i;j>=h;j-=h){
                    System.out.println("--j="+j+" , j-h="+(j-h));
                    if(less(arr[j],arr[j-h])){
                        System.out.println("ech(j="+j+" , j-h="+(j-h)+")");
                        exch(arr,j,j-h);
                    }
                }
            }
            h/=3;
        }

    }
    public static void main(String[] args){
        Integer[] arr = {23,4,12,7,8,15,13,21,11,2};
//        Integer[] arr = {1,2,3,4,5,6,7};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
