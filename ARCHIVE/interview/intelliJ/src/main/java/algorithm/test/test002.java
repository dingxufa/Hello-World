package algorithm.test;

import java.util.Random;

/**
 * Created by ding on 2018/5/14.
 */
public class test002 {
    private Object[] data;
    private int head ,tail;
    private int size;
    public test002(){
        this(3);
    }
    public test002(int capacity){
        data = new Object[capacity];
    }

    public synchronized void produce(){
        if(size>=data.length){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

            }
        }
        int t = new Random().nextInt();
        System.out.println("====>produce " +t);
        data[head] = t;
        size++;
        head = (head+1)%data.length;
        this.notify();
    }

    public synchronized void consume(){
        if(size<=0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        }
        System.out.println("===>consume " + data[tail]);
        data[tail] = null;
        tail = (tail+1) % data.length;
        size--;
        this.notify();
    }

    public static void main(String[] args){
        final  test002 queue = new test002();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
                    queue.produce();
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
                    queue.consume();
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
