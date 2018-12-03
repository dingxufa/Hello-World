package algorithm_exercise.stack;

import java.util.Iterator;

/**
 * Created by ding on 2018/3/5.
 */
public class t1<Item> implements Iterable{

    @Override
    public Iterator iterator() {
        return null;
    }

    private Item[] arr;
    private int size;

    public t1(){
        arr =(Item[])new Object[1];
    }

    private void ensureCapacity(){
        if(size>=arr.length){
            Item[] newArr = (Item[])new Object[size];
            System.arraycopy(arr,0,newArr,0,size);
            arr = newArr;
        }
    }
    private void resize(int resize){
        Item[] newArr = (Item[])new Object[resize];
        System.arraycopy(arr,0,newArr,0,size);
        arr = newArr;
    }

    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){return size;}

    public void push(Item item){
        ensureCapacity();
        arr[size++] = item;
    }
    public Item pop(){
        if (size>0){
            if(size <= arr.length/4){
                resize(arr.length/2);
            }
            return arr[--size];
        }
        return null;
    }


}
