package algorithm_exercise.queue;

import java.util.Arrays;

public class ArrayBlockingDequeue {
    private Object[] elements;
    private int head;
    private int tail;
    private int size;

    public ArrayBlockingDequeue(){
        elements = new Object[3];
    }

    public synchronized void addLast(Object e){
        System.out.println("===== add before" + Arrays.toString(elements));
        if (size>=elements.length){
            try {
               // System.out.println("add wait");
                this.wait();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        elements[tail]=e;
        size++;
       // System.out.println("add " + e);
        tail = (tail+1)%elements.length;
        this.notifyAll();
      //  System.out.println("===== add after" + Arrays.toString(elements));
    }

    public synchronized void poolFirst(){
      //  System.out.println("===== consume before" + Arrays.toString(elements));
        if(size<=0){
            try {
               // System.out.printf("onsume wait");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
      System.out.println("consume :"+ elements[head]);
        size--;
        head = (head+1)% elements.length;
        this.notifyAll();
 //       System.out.println("===== consume after" + Arrays.toString(elements));
    }
    public static void main(String[] args){
       final ArrayBlockingDequeue arrayBlockingDequeue = new ArrayBlockingDequeue();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10000;i++){
                    arrayBlockingDequeue.addLast(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10000;i++){
                    arrayBlockingDequeue.poolFirst();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
