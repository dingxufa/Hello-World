package algorithm.mystack;

import java.util.Iterator;

/**
 * 数组实现stack
 */
public class MyStack<Item> implements  Iterable<Item> {
    private Item[] arr  = (Item[])new  Object[1];
    int size = 0;

    private void resize(int size){
        Item[] newArr = (Item[])new Object[size];
        System.arraycopy(arr,0,newArr,0,arr.length);
        arr = newArr;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
    public void push(Item item){
        if (size>=arr.length){
            resize(2*size+1);
        }
        arr[size++] = item;
    }
    public Item pop(){
        if(size >= 0 ){
            if(size <= arr.length/4){
                resize(arr.length/2);
            }
            return arr[--size];
        }
        return null;
    }


    @Override
    public Iterator<Item> iterator() {
        return new ReverseIterator();
    }
    private class ReverseIterator implements Iterator<Item>{
        private int i = size;
        @Override
        public boolean hasNext() {
            return i>0;
        }

        @Override
        public Item next() {
            return arr[--i];
        }

        @Override
        public void remove() {

        }
    }


    public static void main(String[] args){
        ResizeArrayStack<Object> myStack = new ResizeArrayStack<Object>();
        myStack.push("1");
        myStack.push("2");
        myStack.push("3");
        //System.out.println(myStack.pop());
        Iterator<Object> iterator = myStack.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
//        3
//        2
//        1


    }
}
