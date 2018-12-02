package algorithm.test;

import clojure.lang.Obj;

/**
 * Created by ding on 2018/4/25.
 */
public class ArratBlockingQueue {
    private Object[] data;
    private int size;
    private int head;
    private int tail;

    public ArratBlockingQueue(){
        this(8);
    }
    public ArratBlockingQueue(int initial){
        data = new Object[initial];
    }

    public synchronized void enqueue(Object obj){
        if (size>=data.length){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        }
        System.out.println("produce = " + obj);
        data[head] = obj;
        size++;
        head = (head+1)%data.length;
        this.notify();
    }

    public synchronized Object dequeue(){
        if(size<=0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        }
        Object obj = data[tail];
        size--;
        tail = (tail+1)%data.length;
        this.notify();
        return obj;
    }

    public static void main(String[] args){
       final  ArratBlockingQueue queue = new ArratBlockingQueue();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
                    queue.enqueue(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
                    System.out.println("consume："+queue.dequeue());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
