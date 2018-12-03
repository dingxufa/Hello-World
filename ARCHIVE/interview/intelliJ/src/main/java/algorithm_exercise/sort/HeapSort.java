package algorithm_exercise.sort;
//只有heap才会传length

import java.util.Arrays;

/**
 * 堆排序
 * 堆排序是一种选择排序，它的最坏，最好，平均时间复杂度均为O(nlogn)，它也是不稳定排序。
 *
 　　堆是具有以下性质的完全二叉树：每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆；
        或者每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆。
 堆排序是一种选择排序，整体主要由构建初始堆+交换堆顶元素和末尾元素并重建堆两部分组成。
 其中构建初始堆经推导复杂度为O(n)，在交换并重建堆的过程中，需交换n-1次，而重建堆的过程中，
 根据完全二叉树的性质，[log2(n-1),log2(n-2)...1]逐步递减，近似为nlogn。所以堆排序时间复杂度一般认为就是O(nlogn)级。
 */
public class HeapSort {
    private static void exch(int[]arr,int i,int j){
        int tmp =arr[i];
        arr[i] = arr[j];
        arr[j]=tmp;
    }

    public static  void sort(int[] arr){
        //1.构建大顶堆
        for(int i=arr.length/2 -1;i>=0;i--){
            //从第一个非叶子结点从下至上，从右至左调整结构
            System.out.println("adjustHeap(i="+i+",arr.length="+arr.length+")");
            adjustHeap(arr,i,arr.length);
            System.out.println("====="+Arrays.toString(arr));
        }

        System.out.println("=========step2=====");
        //2.调整堆结构+交换堆顶元素与末尾元素
        for(int j=arr.length-1;j>0;j--){
            exch(arr,0,j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr,0,j);//重新对堆进行调整
        }
    }
//最大堆
    private static void adjustHeap(int[]arr,int i,int length){
        int tmp = arr[i];//先取出当前元素i
        for(int k=2*i+1;k<length;k=2*k+1){//从i结点的左子结点开始，也就是2i+1处开始
            if(k+1<length && arr[k] < arr[k+1]){
                k++;
            }
            if(arr[k] > tmp){//注意这是tmp 不是 arr[k/2-1] ,如果子节点大于父节点(该次操作的i节点)，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        arr[i] = tmp;//将temp值放到最终的位置
    }

    public static void adjust(int[]arr,int i,int length){
        int t = arr[i];
        for(int k=2*i+1;k<length;k++){
            if(k+1<length&&arr[k+1]>arr[k]){
                k++;
            }
            if(arr[k]>t){
                arr[k] =arr[i];
                k=i;
            }else{
                break;
            }
        }
        arr[i] =t;
    }




    public static void main(String[] args){
        int []arr = {9,19,8,7,16,5,4,3,2,1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
