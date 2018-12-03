package algorithm_exercise.queue;
/**
 * 数组实现循环队列
 * 队空： head == tail
 * 队满：（tail+1）% (arr.length) == head
 * 队长：tail - head
 *
 * (tail = (tail +1)&(elements.length-1) ) == head
 * ，我们之前在构造elements元素的时候，说过它的长度一定是2的指数级，所以对于任意一个2的指数级的值减去1之后必然所有位全为1，
 * 例如：8-1之后为111，16-1之后1111。而对于tail来说，当tail+1小于等于elements.length - 1，两者与完之后的结果还是tail+1，
 * 但是如果tail+1大于elements.length - 1，两者与完之后就为0，回到初始位置。
 * 这种判断队列是否满的方式要远远比我们使用符号%直接取模高效，
 */
public class ArrayDequeue {
    private Object[] elements;
    private int head;
    private int tail;
    private static  final int MIN_INTITIAL_CAPACITY = 8;

    public ArrayDequeue(){
        elements = new Object[16];
    }


    public void addLast(Object e){
        if (e == null){
            throw new NullPointerException();
        }
        elements[tail] = e;
        //判断是否队满
        if( (tail = (tail +1)&(elements.length-1) ) == head ){ //tail++
            doubleCapacity();//或者设置标识表示不能再入队
        }
    }
    public boolean add(Object object){
        addLast(object);
        return true;
    }

    public void doubleCapacity(){
        int p = head;
        int n = elements.length;
        int r = n - p;
        int newCapacity = n << 1;
        if(newCapacity < 0 ){
            throw new IllegalStateException("deque is too big");
        }
        Object[] a = new Object[newCapacity];
        System.arraycopy(elements,p,a,0,r);
        System.arraycopy(elements,0,a,r,p);
        elements =a;
        head = 0;
        tail = n;

    }
    public Object pollFirst(){
        int h = head;
        Object result= elements[h];
        if (result == null){
            return null;
        }
        elements[h] = null;
        head = ( head + 1) & ( elements.length -1);  //head++
        return result;
    }

}
