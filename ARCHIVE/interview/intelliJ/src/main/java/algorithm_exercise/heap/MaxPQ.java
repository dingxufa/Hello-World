package algorithm_exercise.heap;

/**
 * 优先队列
 */
public class MaxPQ <Key extends Comparable<Key>> {

    private Key[] pq;
    private int N=0;

    public MaxPQ(int maxN){
        pq = (Key[])new Comparable[maxN+1];
    }

    public boolean isEmpty(){ return N==0;}
    public int size(){return N;}

    private boolean less(int i,int j){
        return pq[i].compareTo(pq[j])<0;
    }
    private void exch(int i,int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;

    }

    //上浮，将当前节点与父节点比较，小的向上调整
    private void swim(int k){
        while(k > 1 && less(k/2,k)){
            exch(k/2,k);
            k = k/2;
        }
    }

    private void sink(int k){
        while(2 * k <= N){
            int j = 2 * k;
            if(j<N && less(j,j+1)){ j++;}
            if(!less(k,j)){ break;}
            exch(k,j);
            k = j;
        }
    }

    public void insert(Key v){
        pq[++N] = v;
        swim(N);
    }

    public Key delMax(){
        Key max = pq[1];
        exch(1,N--);
        pq[N+1]=null;
        sink(1);
        return max;
    }

    public static void sort(Comparable[] arr){
        int N = arr.length;
        for(int k=N/2;k>=1;k--){
            //sink(arr,k,N);
        }
        while(N >1){
            //exch(arr,1,N--);
            //sink(arr,1,N);
        }
    }


}
