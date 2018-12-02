package algorithm.test;

import javax.naming.event.ObjectChangeListener;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ding on 2018/4/25.
 */
public class ArrayBlockingDequeue2 {
    private Object[] data;
    private int size;
    private int head;
    private int tail;
    public ArrayBlockingDequeue2(){
        this(8);
    }
    public ArrayBlockingDequeue2(int initial){
        data = new Object[initial];
    }
    private ReentrantLock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    public void enqueue(Object obj){
        System.out.println("===put wait lock");
        lock.lock();
        System.out.println("===put get lock");
        try {
            while (size == data.length){
                System.out.println("===buffer full,please wait");
                notFull.await();
            }
            data[head] =obj;
            head = (head+1)%data.length;
            size++;
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Object dequeue(){
        System.out.println("====take wait lock");
        lock.lock();
        System.out.println("====take get lock");
        try {
            while(size<= 0 ){
                System.out.println("===no data,please wait");
                notEmpty.await();
            }
            Object obj = data[tail];
            size--;
            tail = (tail+1)%data.length;
            notFull.signal();
            return obj;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }
    public static void main(String[] args){
        final  ArrayBlockingDequeue2 queue = new ArrayBlockingDequeue2();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
                    System.out.println("putting..");
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
