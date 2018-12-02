package algorithm.mystack;
/*
* 数组实现栈
* */
import java.util.Iterator;

public class ResizeArrayStack<Item> implements Iterable<Item> {
    private Item[] a =  (Item[])new Object[1];
    private int N = 0;

    //// 调整数组大小，使得栈具有伸缩性
    private void resize(int size){
        Item[] newa = (Item[]) new Object[size];
        System.arraycopy(a,0,newa,0,a.length);
        a = newa;
    }
    private boolean isEmpty(){
        return N==0;
    }
    private int size(){
        return N;
    }

    public  Iterator<Item> iterator(){
        return new ReverseArrayIterator();
    }
    private class ReverseArrayIterator implements Iterator<Item>{
        private int i = N;

        @Override
        public void remove() {

        }

        @Override
        public boolean hasNext() {
            return i>0;
        }

        @Override
        public Item next() {
            return a[--i];
        }
    }

    ///////////////////////////

    public void push(Item item){
        if(N>=a.length){
            resize(2*a.length);
        }
        a[N++]=item;
    }

    public Item pop(){
        Item item = a[--N];
        if(N <= a.length/4){
            resize(a.length/2);
        }
        return item;
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


    }

}
