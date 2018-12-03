package algorithm_exercise.search;

/**
 *
 */
public class BinarySearchST<Key extends Comparable<Key>,Value> {
    private Key[] keys;
    private Value[] values;
    private int N;


    public BinarySearchST(int capacity) {
        keys = (Key[])new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }
    public int size(){ return N;}

    public void put(Key key,Value value){
        int i = rank(key);
        if(i<N && keys[i].compareTo(key) == 0){
            values[i] = value;
            return;
        }
        //这里应该要检查数组是否要扩容
        for(int j = N;j>i;j--){//将i后面的值向后移动一位
            keys[j] = keys[j-1];
            values[j] = values[j-1];
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }
    public Value get(Key key){
        int i = rank(key);
        if(i<N && keys[i].compareTo(key) == 0){
            return values[i];
        }
        return null;
    }

    public int rank(Key key){//二分查找
        int low = 0 , high = N-1;
        while(low<= high){
            int mid = (low + high)/2;
            if(key.compareTo(keys[mid])<0){
                high = mid-1;
            }else if(key.compareTo(keys[mid])>0){
                low = mid+1;
            }else{
                return mid;
            }
        }
        return low;
    }



    public Key ceiling(Key key){
        int i = rank(key);
        return keys[i];
    }

}
