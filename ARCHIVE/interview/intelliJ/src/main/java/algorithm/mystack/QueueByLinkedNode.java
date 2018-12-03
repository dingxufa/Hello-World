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
