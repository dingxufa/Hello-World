package algorithm.test;

/**
 * Created by ding on 2018/4/25.
 */
public class ArrayDequeue {
    private Object[] data;
    private int size;
    private int head,tail;
    public ArrayDequeue(){
        data = new Object[8];
    }
    private void ensureCapacity(){
        int h = head;
        int t =data.length -head;
        int newCapacity = data.length << 1;
        Object[] arr = new Object[newCapacity];
        System.arraycopy(data,h,arr,0,t);
        System.arraycopy(data,0,arr,t,h);
        data = arr;
        head =0 ;
        tail=arr.length;
    }
    public void enqueue(Object obj){
        data[size++] = obj;
        if ( (tail = (tail +1)%data.length ) == head   ){
            ensureCapacity();
        }
    }
    public Object dequeue(){
        Object obj = data[head];
        data[head] = null;
        head = (head+1)% data.length;
        return obj == null?null:obj;
    }


    public static void main(String[] args){
        final  ArrayDequeue queue = new ArrayDequeue();
       for(int i=0;i<1000;i++){
           System.out.println("put="+i);
           queue.enqueue(i);
       }

        for(int i=0;i<1000;i++){
            System.out.println("get="+queue.dequeue());
        }
    }
}
