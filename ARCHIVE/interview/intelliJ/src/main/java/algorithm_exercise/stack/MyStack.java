package algorithm_exercise.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 使用队列实现自定义堆栈
 * 1、弹
 * 2、压
 * 3、获取头
 * @author Administrator
 *
 * @param <E>
 */
public class MyStack<E> {
    //容器
    private Deque<E> stack =new ArrayDeque<E>();
    //容量
    private int size;
    public MyStack(int size) {
        super();
        this.size = size;
    }

    //压栈
    public boolean push(E e){
        if(stack.size()+1>= size){
            return false;
        }
        return stack.offerLast(e);

    }
    //弹栈
    public E pop(){
        return stack.pollLast();
    }
    //获取
    public E peek(){
        return stack.peekLast();
    }

    public int size(){
        return this.stack.size();
    }
}
