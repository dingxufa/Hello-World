package algorithm_exercise;

import java.util.ArrayDeque;

/**
 * 实现有序数组
 * @param <T>
 */

public class MyOrderArray<T extends Comparable<T>> {
    private static boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }

    private T[] elementData;
    private int size;


    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public MyOrderArray(){
        this(10);
    }

    public MyOrderArray(int initialCapacity){
        if(initialCapacity<0){
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        elementData = (T[])new Comparable[initialCapacity];
    }

    private void ensureCapacity(){
        //数组扩容和数据的拷贝
        if(size==elementData.length){
            T[] newArray = (T[])new Comparable[size*2+1];
            System.arraycopy(elementData, 0, newArray, 0, elementData.length);
            elementData = newArray;
        }
    }

    public void add(T item){//按递增顺序
        ensureCapacity();
        //现有list为递增顺序，找到当前比要插入数据大的一个元素，将该元素到list末尾的所有元素统统后移一位
        int i ;
        for(i=0;i<size;i++){
            if(less(item , elementData[i])){
                break;
            }
        }
        System.arraycopy(elementData,i,elementData,i+1,size-i);
        elementData[i] = item;
        size++;

        //show
        for(int j=0;j<size;j++){
            System.out.print(elementData[j]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args){
//        MyOrderArray<Integer> orderArray = new MyOrderArray<Integer>();
//        orderArray.add(2);
//        orderArray.add(8);
//        orderArray.add(9);
//        orderArray.add(3);
//        orderArray.add(7);

        MyOrderArray<String> orderArray = new MyOrderArray<String>();
        orderArray.add("a");
        orderArray.add("z");
        orderArray.add("s");
        orderArray.add("b");//a b s z

    }
}
