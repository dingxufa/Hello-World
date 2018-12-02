package algorithm.sort;

public class MergeSort {
    private static Comparable[] aux;

    private static void merge(Comparable[] a,int lo,int mid,int hi){
        int i=lo,j=mid+1;
        for(int k=lo;k<=hi;k++){
            aux[k] =a[k];
        }
        System.out.println("merger begin:");

        for(int x=lo;i<=hi;x++){
            try{
                System.out.print(aux[x] + "\t");
            }catch(Exception e){
                break;
            }
        }
        System.out.println("");
        System.out.println("merge start:");
        for(int k=lo;k<=hi;k++){
            if(i>mid){a[k] = aux[j++];}
            else if( j>hi){ a[k]=aux[i++];}
            else if( aux[i].compareTo(a[j])<0){ a[k]=aux[i++];}
            else{ a[k] = aux[j++];}
        }
        System.out.println("merge end:");
        for(Comparable t :a){
            System.out.print(t + "\t");
        }
        System.out.println();
    }

    public static  void sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a,0,a.length-1);
        for (int i=0;i<a.length;i++){
            System.out.print(a[i] + "\t");
        }
    }

    private static  void sort(Comparable[]a,int lo,int hi){
        if(hi<=lo){return;}
        int mid = (lo + hi)/2;
        System.out.println("=============lo="+lo+",mid="+mid+",hi="+hi);
       // System.out.println("sort,lo="+lo+",mid="+mid);
        sort(a, lo, mid);
      //  System.out.println("sort,mid+1="+(mid+1)+",hi="+hi);
        sort(a, mid + 1, hi);
       System.out.println("merge,lo="+lo+",mid="+mid+",hi="+hi);
        merge(a, lo, mid, hi);
    }

    public static void main(String[] args){
        sort(new Integer[]{6,2,13,4,7,11,16,8,3,12,17,5});
    }

}
