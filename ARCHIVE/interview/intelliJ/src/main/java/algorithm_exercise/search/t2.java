package algorithm_exercise.search;

/**
 * Created by ding on 2018/3/5.
 */
public class t2 <Key extends Comparable<Key>, Value>{
    private Key[] keys;
    private Value[] values;
    private int size;

    public t2(int capacity){
        keys = (Key[])new Comparable[capacity];
        values =(Value[]) new Object[capacity];
    }
    public int size(){return size;}

    private int rank(Key  key){
        int low =0,high=size-1;
        while(low <= high){
            int mid = (low + high)/2;
            if(key.compareTo(keys[mid])<0){
                high = mid -1;
            }else if(key.compareTo(keys[mid])>0){
                low = mid + 1;
            }else{
                return mid;
            }
        }
        return low;
    }
    public void put(Key key,Value value){
        int i = rank(key);
        if(i<size && keys[i].compareTo(key) == 0){
            values[i] = value;
            return;
        }
        System.arraycopy(keys,i,keys,i+1,size-1-i);
        for(int j=size;j>i;j--){
            values[j] = values[j-1];
        }

        keys[i] = key;
        values[i] = value;
        size++;
    }

    public Value get(Key key){
        int i = rank(key);
        if(i<size && keys[i].compareTo(key)==0){
            return values[i];
        }
        return null;
    }
}
