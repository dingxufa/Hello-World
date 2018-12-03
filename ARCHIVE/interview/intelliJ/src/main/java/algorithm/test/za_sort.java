package algorithm.test;

import java.util.Arrays;

/**
 * Created by ding on 2018/4/29.
 */
public class za_sort {
    public static void exch(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    //堆排序
    public static void sort_heap(int[]arr){
        for(int i=arr.length/2-1;i>=0;i--){
            adjust(arr,i,arr.length);
        }
        for(int j=arr.length-1;j>0;j--){
            exch(arr,0,j);
            adjust(arr,0,j);
        }
    }
    private static  void adjust(int[]arr,int i,int length){
        int tmp = arr[i];
        for(int k=2*i+1;k<length;k=2*k+1){
            if(k+1<length && arr[k+1]>arr[k]){
                k++;
            }
            if(arr[k] > tmp){
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        arr[i] = tmp;
    }


    //归并
    public static void sort_merge(int[] arr){
        int[] tmp = new int[arr.length];
        sort_merge(arr, 0, arr.length - 1, tmp);
    }
    private static void sort_merge(int[]arr,int left,int right,int[]tmp){
        if(left<right){
            int mid = (left+right)/2;
            sort_merge(arr, left, mid, tmp);
            sort_merge(arr,mid+1,right,tmp);
            merge(arr,left,mid,right,tmp);
        }
    }
    private static void merge(int[]arr,int left,int mid,int rihgt,int[] tmp){
        int i = left;
        int j = mid+1;
        int t = 0;
        while(i<= mid && j <= rihgt){
            if(arr[i] < arr[j]){
                tmp[t++] = arr[i++];
            }else{
                tmp[t++] = arr[j++];
            }
        }
        while (i<=mid){
            tmp[t++] = arr[i++];
        }
        while (j<=rihgt){
            tmp[t++] = arr[j++];
        }
        t = 0;
        while (left<=rihgt){
            arr[left++] = tmp[t++];
        }
    }

    //快速排序
    public static void sort_quick(int[]arr){
        sort_quick(arr,0,arr.length-1);
    }
    private static void sort_quick(int[]arr,int left,int right){
        if(left<right){
            int j = parition(arr,left,right);
            sort_quick(arr,left,j-1);
            sort_quick(arr,j+1,right);
        }
    }
    private static int parition(int[]arr,int left,int right){
        int i = left;
        int j = right+1;
        int tmp = arr[left];
        while(i<=j){
            while(arr[++i] < tmp){ if(i==right){break;}}
            while(tmp < arr[--j]){ if(j==left){break;}}
            if(i>=j){break;}
            exch(arr,i,j);
        }
        exch(arr,left,j);
        return j;
    }

    public static void main(String[] args){
        int []arr = {9,19,8,7,16,5,4,3,2,1};
        //sort_heap(arr);
       // sort_merge(arr);
        sort_quick(arr);
        System.out.println(Arrays.toString(arr));

    }
}
