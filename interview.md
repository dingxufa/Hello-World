## algorithm

### stack

```java

/**
 * 用链表实现栈
 */
public class LinkedStack<Item> {
    private class Node{
        private Node next;
        private Item item;

        public Node(Node next, Item item) {
            this.next = next;
            this.item = item;
        }
    }

    private Node top = null;
    private int size = 0;

    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }

    public void push(Item item){
        Node newTop = new Node(top,item);
        top = newTop;
        size++;
    }
    public Item pop(){
        if(size >0){
            Item item = top.item;
            top = top.next;
            size--;
            return item;
        }
        return null;
    }

    public static void main(String[] args){
        LinkedStack<Object> myStack = new LinkedStack<Object>();
        myStack.push("1");
        myStack.push("2");
        myStack.push("3");
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
//        3
//        2
//        1
//        Exception in thread "main" java.lang.NullPointerException
//        at algorithm.mystack.LinkedStack$Node.access$000(LinkedStack.java:9)
//        at algorithm.mystack.LinkedStack.pop(LinkedStack.java:35)
    }
}



```



```java
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

```



```java
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

```





### queue

```java
package algorithm.mystack;

import clojure.lang.Obj;

import java.util.Objects;

/**
 *链表实现队列
 */
public class QueueByLinkedNode<Item> {

    private class Node{
        private Item item;
        private Node next;

        public Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    private Node first = null;
    private Node last = null;
    private int size = 0;

    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }

    public void enqueue(Item item){
        Node node = new Node(item, null);
        if(size == 0){
            first = node;
            last = node;
        }else{
            last.next = node;
            last = node;
        }
        size++;
    }

    public Item dequeue(){
        if (size>0){
            Node tmp = first;
            first = first.next;
            size--;
            return  tmp.item;
        }else{
            return null;
        }
    }

    public static void main(String[] args){
        QueueByLinkedNode<Object> queue = new QueueByLinkedNode<Object>();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
//        1
//        2
//        3
//        null
    }

}

```









### search

```java

public class BinarySearch {

    public static  int  sort(int[] arr,int key){
        int low = 0;
        int high = arr.length-1;
        while(low <= high){
            int mid = (low + high)/2;
            if(arr[mid]<key){
                low = mid+1;
            }else if(arr[mid]>key){
                high = mid-1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        int index = sort(new int[]{1, 3, 5, 7, 8, 12, 24, 56}, 13);
        System.out.println(index);
    }

}

```













## 设计模式

### 1.单例模式

```java

/**
 * 饿汉式   线程安全 效率高 不能延时加载
 */
public class Singleton {
    //类初始化时立即加载这个对象（没有延时加载的优势） 由于类加载时，天然的是线程安全的
    private static Singleton instance = new Singleton();

    // 私有化构造器
    private Singleton(){}

    //方法没有同步，调用效率高
    public static Singleton getInstance(){
        return instance;
    }
}

```



```java

/**
 * 懒汉式  线程安全  效率不高 能延时加载
 */
public class Singleton2 {
    //类初始化时，不初始化这个对象(延时加载，真正用的时候再创建)
    private static Singleton2 instance;

    private Singleton2() {}

    //方法同步  调用效率低
    public static synchronized Singleton2 getInstance(){
        if(instance == null){
            instance = new Singleton2();
        }
        return  instance;
    }
}
```





双重检测锁

```java
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * 双重检测锁
 * 有序性：是因为 instance = new Singleton(); 不是原子操作。编译器存在指令重排，从而存在线程1 创建实例后（初始化未完成），线程2 判断对象不为空，但实际对象扔为空，造成错误。

 主要是禁止重排序，初始化一个实例（SomeType st = new SomeType()）在java字节码中会有4个步骤，
 1. 申请内存空间，
 2. 初始化默认值（区别于构造器方法的初始化），
 3. 执行构造器方法
 4. 连接引用和实例。

 这4个步骤后两个有可能会重排序，1234  1243都有可能，造成未初始化完全的对象发布。
 volatile可以禁止指令重排序，从而避免这个问题
 */
public class Singleton3 {
    public static volatile Singleton3 instance = null;

    private Singleton3() {}

    public static Singleton3 getInstance(){
        if(instance == null){
            synchronized(Singleton3.class) {
                if (instance == null) {
                    instance = new Singleton3();
                }
             //  return instance;
            }
        }
        return instance;
    }

    
```



```java

/**
 * 静态内部类  线程安全  效率高  能延时加载
 */
public class Singleton4 {
    static {
        System.out.println("222");
    }

    private static class SingletonClassInstance {
        static {
            System.out.println("111");
        }
        private static final Singleton4 instance = new Singleton4();
    }

    private Singleton4(){
    }

    //方法没有同步，调用效率高！
    public static Singleton4 getInstance(){
        return SingletonClassInstance.instance;
    }

    public static void main(String[] args){
        Singleton4 singleton4 = new Singleton4();
        System.out.println("==============");
        Singleton4.getInstance();
//        222
//        ==============
//        111
    }
}

```





```java

/**
 * 枚举实现单列模式  枚举本身就是单列模式，避免通过反射和反序列化创建多个实例  不能延时加载
 */
public class Singleton5 {
    private Singleton5(){}
    public static Singleton5 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private static enum Singleton{
        INSTANCE;
        private Singleton5 instance;

        private Singleton(){
            instance = new Singleton5();
        }
        public Singleton5 getInstance(){
            return instance;
        }
    }

    



}
```





```java

import java.io.Serializable;

/**
 * 懒汉式单例模式(如何防止反射和反序列化漏洞)
 */
public class Singleton6 implements Serializable{

    private static Singleton6 instance;
    private Singleton6(){
        if(instance != null) {
            throw new RuntimeException();  //防止通过反射创建实例
        }
    }
    public static synchronized Singleton6 getInstance(){
        if(null == instance){
            instance = new Singleton6();
        }
        return instance;
    }

    //反序列化时，如果定义了readResolve()则直接返回此方法指定的对象。而不需要单独再创建新对象！
    private Object readResolve(){
        return  instance;
    }


}

```



lll###

## dfd

