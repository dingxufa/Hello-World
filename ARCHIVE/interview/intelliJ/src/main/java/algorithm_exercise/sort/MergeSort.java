package algorithm_exercise.sort;

import java.util.Arrays;

/**
 * 归并排序
 * 因为每次都将问题对半分成两个子问题，而这种对半分的算法复杂度一般为 O(NlogN)，因此该归并排序方法的时间复杂度也为 O(NlogN)。
 小数组的递归操作会过于频繁，可以在数组过小时切换到插入排序来提高性能。
 */
public class MergeSort {

    public static void sort(Comparable[] arr){
        Comparable[] tmp = new Comparable[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        sort(arr,0,arr.length-1,tmp);
        System.out.println(Arrays.toString(arr));
    }
    private static void sort(Comparable[] arr,int left,int right,Comparable[] tmp){
        if(left<right){
            int mid = (left + right)/2;
            sort(arr,left,mid,tmp); ////左边归并排序，使得左子序列有序
            sort(arr,mid+1,right,tmp);////右边归并排序，使得右子序列有序
            merge(arr, left, mid, right, tmp);//将两个有序子数组合并操作
        }
    }
    private static void merge(Comparable[] arr,int left,int mid,int right,Comparable[] tmp){
        int i = left;//左序列指针
        int j = mid+1; ////右序列指针
        int t = 0;//临时数组指针
        while(i<=mid && j<=right){
            if(arr[i].compareTo(arr[j])<=0){
                tmp[t++]=arr[i++];
            }else{
                tmp[t++]=arr[j++];
            }
        }
        while(i<=mid){//将左边剩余元素填充进temp中
            tmp[t++]=arr[i++];
        }
        while(j<=right){//将右序列剩余元素填充进temp中
            tmp[t++]=arr[j++];
        }
        t=0;
        while(left<=right){ //将temp中的元素全部拷贝到原数组中
            arr[left++]=tmp[t++];
        }
    }

    public static void main(String[] args){

        sort(new Integer[]{13,27,14,23,11,25,9});
    }
}

class test{
    public static void sort(Comparable[] arr){
        Comparable[] tmp = new Comparable[arr.length];
        sort(arr,0,arr.length-1,tmp);
        System.out.println("==========");
        System.out.println(Arrays.toString(arr));
    }
    public static void sort(Comparable[]arr,int left,int right,Comparable[]tmp){
        if(left<right){
            int mid = (left+right)/2;
            sort(arr,left,mid,tmp);
            sort(arr,mid+1,right,tmp);
            merger(arr,left,mid,right,tmp);
        }
    }
    public static void merger(Comparable[]arr,int left,int mid,int right,Comparable[]tmp){
        int i=left;
        int j = mid+1;
        int t =0 ;
        while (i<=mid&&j<=right){
            if(arr[i].compareTo(arr[j])<=0){
                tmp[t++]=arr[i++];
            }else {
                tmp[t++]=arr[j++];
            }
        }

        while (i<=mid){
            tmp[t++]=arr[i++];
        }
        while (j<=right){
            tmp[t++]=arr[j++];
        }

        t=0;
        while(left<=right){
            arr[left++]=tmp[t++];
        }
    }
    public static void main(String[] args){
        //sort(new Integer[]{1,13,2,27,14,23,11,25,9});
        Integer[] arr = {5,1, 3, 5, 7, 9, 11, 13, 17, 19,5};
        sort(arr);
    }
}
