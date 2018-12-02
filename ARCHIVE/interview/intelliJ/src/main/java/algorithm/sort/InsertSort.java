package algorithm.sort;

public class InsertSort {
    private static  boolean less(Comparable v,Comparable w){
        System.out.println(v + " ?  " + w);
        return v.compareTo(w) < 0;
    }
    private static void exch(Comparable[] arr,int i,int j){
        Comparable t = arr[i];
        arr[i] =arr[j];
        arr[j] =t;
    }

    public static void sort(Comparable[] arr){
        for(int i=1;i<arr.length;i++){
            for(int j=i;j>0&&less(arr[j],arr[j-1]);j--){ //
                exch(arr,j,j-1);
            }
        }

        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+"\t");
        }
    }

    public static void main(String[] args){
        sort(new Integer[]{3,5,1,23,11,25,9});
    }
}
