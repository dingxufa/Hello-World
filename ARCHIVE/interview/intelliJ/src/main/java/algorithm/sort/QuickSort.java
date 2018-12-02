package algorithm.sort;

/**
 * 快速排序
 */
public class QuickSort {
    private static  boolean less(Comparable v,Comparable w){
        System.out.println(v + " ?  " + w);
        return v.compareTo(w) < 0;
    }
    private static void exch(Comparable[] arr,int i,int j){
        Comparable t = arr[i];
        arr[i] =arr[j];
        arr[j] =t;
    }


    public static void sort(Comparable[] a) {
       // shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
}
