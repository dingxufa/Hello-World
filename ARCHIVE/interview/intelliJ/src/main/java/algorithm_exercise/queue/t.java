package algorithm_exercise.queue;

import clojure.lang.Obj;

/**
 * Created by ding on 2018/3/5.
 */
public class t {
    private class Node{
        private Object object;
        private Node next;

        public Node(Object object, Node next) {
            this.object = object;
            this.next = next;
        }
    }

    private Node first;
    private Node last;
    private int size;

    public void enqueue(Object object){
        if(size == 0){
            Node node = new Node(object, null);
            first = node;
            last = node;
        }else {
            Node node = new Node(object, null);
            last.next = node;
            last = node;
        }
        size++;
    }

    public Object dequeue(){
        if (size>0){
            Node node = first;
            first = first.next;
            size--;
            return node.object;
        }
        return null;
    }
    public static void main(String[] args){
        myLinkedQueue myLinkedQueue = new myLinkedQueue();
        myLinkedQueue.enqueue(1);
        myLinkedQueue.enqueue("2");
        myLinkedQueue.enqueue("3");
        myLinkedQueue.enqueue("4");
        System.out.println(myLinkedQueue.dequeue());
        System.out.println(myLinkedQueue.dequeue());
        System.out.println(myLinkedQueue.dequeue());
        System.out.println(myLinkedQueue.dequeue());
        System.out.println(myLinkedQueue.dequeue());
    }
}
