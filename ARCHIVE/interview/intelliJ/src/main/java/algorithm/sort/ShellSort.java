package algorithm.sort;

public class ShellSort {
    private static  boolean less(Comparable v,Comparable w){
        //System.out.println(v + " ?  " + w);
        return v.compareTo(w) < 0;
    }
    private static void exch(Comparable[] arr,int i,int j){
        Comparable t = arr[i];
        arr[i] =arr[j];
        arr[j] =t;
    }

    public static  void sort(Comparable[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+"\t");
        }
        System.out.println("================start");
        int h = 1;
        int N = arr.length;
        while(h < N /3){
            h = 3 * h +1; //1,4,13,40...
        }
        System.out.println("N="+N+" h="+h);
        while(h >= 1){
            for(int i=h; i<N;i++){
                System.out.println("time===========i="+i+",h="+h);
                for(int j=i; j>=h && less(arr[j],arr[j-h]);j-=h){
                    System.out.println("exch:j="+j+",j-h="+(j-h)+" -->"+arr[j]+" " + arr[j-h]);
                    exch(arr,j,j-h);
                }
            }
            for(int i=0;i<arr.length;i++){
                System.out.print(arr[i]+"\t");
            }
            System.out.println("================");
            h = h/3;
        }

        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+"\t");
        }
    }
    public static void main(String[] args){

        sort(new Integer[]{13,27,14,23,11,25,9});
    }
}
