package algorithm_exercise.stack;

import java.util.Iterator;

/**
 * 数组实现栈
 *          iterator    reverseIterator
 * cursor   -1              size
 * hasNext  cursor+1<size  cursor>0
 * next    [++cursor]       [--cursor]
 */
public class MyArrayStack<Item> implements Iterable {
    private Item[] arr ;
    private int size;

    @Override
    public Iterator iterator() {
        return new ReverseIterator () ;
    }
    private class MyIterator implements Iterator<Item>{
        private int cursor = -1;
        @Override
        public boolean hasNext() {
            return  cursor+1<size;
        }

        @Override
        public Item next() {
            return arr[++cursor];
        }

        @Override
        public void remove() {

        }
    }
    private class ReverseIterator implements Iterator<Item>{
        private int cursor = size;
        @Override
        public boolean hasNext() {
            return cursor>0;
        }

        @Override
        public Item next() {
            return arr[--cursor];
        }

        @Override
        public void remove() {

        }
    }



    public MyArrayStack(){
        arr = (Item[])new Object[1];
    }
    private void ensureCapacity(){
        if(size >= arr.length){
            Item[] newArr = (Item[])new Object[arr.length*2+1];
            System.arraycopy(arr,0,newArr,0,arr.length);
            arr = newArr;
        }
    }
    private void resize(int size){
        Item[] newArr = (Item[])new Object[size];
        System.arraycopy(arr,0,newArr,0,size);
        arr = newArr;
    }

    public boolean isEmpty(){return size==0;}
    public int size(){return size;}

    public void push(Item item){
        ensureCapacity();
        arr[size++]=item;
    }

    public Item pop(){
        if(size >0){
            if(size <= arr.length /4){
                resize(arr.length/2);
            }
            return arr[--size];
        }
        return null;
    }

    public static void main(String[] args){
        MyArrayStack<Object> myStack = new MyArrayStack<Object>();
        myStack.push(1);
        myStack.push("2");
        myStack.push("3");
        myStack.push(4);
        System.out.println(myStack.pop());
        System.out.println(myStack.size);
        Iterator iterator = myStack.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }


}
