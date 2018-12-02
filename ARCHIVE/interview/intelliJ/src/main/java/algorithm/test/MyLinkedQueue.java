package algorithm.test;

/**
 * Created by ding on 2018/4/25.
 */
public class MyLinkedQueue {
    private class Node{
        private Object object;
        private Node next;
        public Node(Object obj,Node node){
            this.object = obj;
            this.next = node;
        }
    }
    private int size;
    private Node head,tail;

    public void enqueue(Object obj){
        Node node = null;
        if(size == 0){
            node = new Node(obj,null);
            head = node;
            tail = node;
        }else{
            node = new Node(obj,null);
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public Object dequeue(){
        Node node = null;
        if(size <=0 ){
            return null;
        }
        node = head;
        head = head.next;
        size--;
        return node.object;
    }
    public static void main(String[] args){
//        MyLinkedQueue myLinkedQueue = new MyLinkedQueue();
//        myLinkedQueue.enqueue(1);
//        myLinkedQueue.enqueue("2");
//        myLinkedQueue.enqueue("3");
//        myLinkedQueue.enqueue("4");
//        System.out.println(myLinkedQueue.dequeue());
//        System.out.println(myLinkedQueue.dequeue());
//        System.out.println(myLinkedQueue.dequeue());
//        System.out.println(myLinkedQueue.dequeue());
//        System.out.println(myLinkedQueue.dequeue());
        ;
        System.out.println("asdd f".replaceAll(" ","%20"));
    }

}
