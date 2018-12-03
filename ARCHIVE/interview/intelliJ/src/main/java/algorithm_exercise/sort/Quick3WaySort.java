package algorithm_exercise.sort;

import java.util.Arrays;

/**
 * 三向切分快速排序
 * 三向切分快速排序是对快速排序的改进。根据关键字将待排序列分为三部分：左边均小于关键字，中间均等于关键字，右边均大于关键字。
 当待排序列中有较多相等数据时，其排序效率高于快速排序。否则，效率低于快速排序。
 时间复杂度：最好 O(n)=O(n)；平均O(n)=O(nlogn)；最差O(n)=O(n2)
 空间复杂度：O(n)=O(n)
 稳定性：    不稳定

 对于每次切分：从数组的左边到右边遍历一次，维护三个指针lt,gthe i，其中
 lt指针使得元素 （arr[0]-arr[lt-1]） 的值均小于切分元素；
 gt指针使得元素（arr[gt+1]-arr[N-1]）的值均大于切分元素；
 i指针使得元素（arr[lt]-arr[i-1]）的值均等于切分元素，（arr[i]-arr[gt]）的元素还没被扫描，切分算法执行到i>gt为止。
 每次切分之后，位于gt指针和lt指针之间的元素的位置都已经被排定，不需要再去移动了。之后将（lo,lt-1）,（gt+1,hi）分别作为处理左子数组和右子数组的递归函数的参数传入，递归结束，整个算法也就结束。
 */
public class Quick3WaySort {
    private static void exch(Comparable[] arr,int i,int j){
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    private static boolean less(Comparable a,Comparable b){
        return a.compareTo(b) <0;
    }
    public static void sort(Comparable[] arr,int low,int high){
        if(low >= high){return;}
        int lt = low;
        int i = low +1;
        int gt = high;
        Comparable t = arr[low];
        while(i<=gt){  // i <= gt   （arr[i]-arr[gt]）的元素还没被扫描，切分算法执行到i>gt为止。
            if(arr[i].compareTo(t)<0){
                exch(arr,lt++,i++);//exch(arr,lt,i); lt++;i++;
            }else if(arr[i].compareTo(t)>0){
                exch(arr,i,gt--);   //注意这里不能 i++
            }else{
                i++;
            }
        }
        sort(arr,low,lt-1);  // [lt,gt]  =V
        sort(arr,gt+1,high);
    }
    private static void sort2(Comparable[] arr,int left,int right){
        if(left<right){
            int lt = left;
            int i = left+1;
            int gt = right;
            Comparable t = arr[left];
            while(i<=gt){
                if(arr[i].compareTo(t)<0){
                    exch(arr,lt++,i++);
                }else if(arr[i].compareTo(t)>0){
                    exch(arr,i,gt--);
                }else{
                    i++;
                }
            }
            sort(arr,left,lt-1);
            sort(arr,gt+1,right);
        }
    }


    public static void main(String[] args){
        Integer[] arr = {34,56,1, 3,9, 5, 7, 9, 11, 13, 17, 19,9};
        sort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));

    }
}
