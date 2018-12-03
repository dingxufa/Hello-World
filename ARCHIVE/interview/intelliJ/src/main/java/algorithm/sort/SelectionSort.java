package algorithm.sort;

/**
 * 选择排序
 */


public class SelectionSort {
    private static  boolean less(Comparable v,Comparable w){
        System.out.println(v + " ?  " + w);
        return v.compareTo(w) < 0;
    }
    private static void exch(Comparable[] arr,int i,int j){
        Comparable t = arr[i];
        arr[i] =arr[j];
        arr[j] =t;
    }

    public static  void sort(Comparable[] arr){
        for(int i=0;i<arr.length;i++){
            int min = i;
            for (int j=i+1;j<arr.length;j++){
                if(less(arr[j],arr[min])){
                    min = j;
                }
            }
            exch(arr,min,i);
        }
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i] + "\t");
        }
    }

    public static void main(String[] args){
        sort(new Integer[]{3,5,1,23,11,25,9});
    }

}
