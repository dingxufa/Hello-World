



###  code

```java
package com.example.demo;


class Node<T>{
    private Node pre;
    private Node next;
    private T object;    // 

    public Node(T object) {
        this.object = object;
    }

    public Node getPre() {
        return pre;
    }

    public Node getNext() {
        return next;
    }

    public T getObject() {
        return object;
    }

    public void setPre(Node pre) {
        this.pre = pre;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setObject(T object) {
        this.object = object;
    }
}


public class MyLinkedList {
    private Node head;
    private Node tail;
    private int size;



    private void add(Node node){
        if(head == null){
            head = node;
            tail = node;
        }else{
            node.setPre(tail);
            tail.setNext(node);
            tail = node;
        }
        size++;
    }

    private boolean remove(Node node){
        if( head == null && tail == null){
            return true;
        }else{
            for(int i=0;i<size;i++){
                Node tmp = get(i);
                Object object = tmp.getObject();
                Class<?> clazz = object.getClass();
                System.out.println("====> " + clazz +"   " + node.getObject().getClass());
                if( clazz != node.getObject().getClass() ){
                    continue;
                }
                if(object.equals( node.getObject()  )){
                    Node pre = tmp.getPre();
                    Node next = tmp.getNext();

                    if(pre == null){ // 第一个节点
                        head = head.getNext();
                    }else{
                        pre.setNext(next );
                    }
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    public Node get(int index){
        if(index <0 || index >= size){
            return null;
        }
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node;
    }

    public void pprint(MyLinkedList list){
        if(list == null) return;
        for (int i = 0; i < list.size; i++) {
            System.out.println(list.get(i).getObject().toString() +" "  + 						           list.get(i).getObject().getClass());
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node("1");
        Node node3 = new Node(1);

        Node node4 = new Node('a');
        Node node5 = new Node(2.34);

        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.add(node1);
        myLinkedList.add(node2);
        myLinkedList.add(node3);
        myLinkedList.add(node4);
        myLinkedList.add(node5);

        myLinkedList.pprint(myLinkedList);
        System.out.println("===============");
        myLinkedList.remove(new Node(2.34));

        myLinkedList.pprint(myLinkedList);


    }

}

============
1 class java.lang.Integer
1 class java.lang.String
1 class java.lang.Integer
a class java.lang.Character
2.34 class java.lang.Double
===============
====> class java.lang.Integer   class java.lang.Double
====> class java.lang.String   class java.lang.Double
====> class java.lang.Integer   class java.lang.Double
====> class java.lang.Character   class java.lang.Double
====> class java.lang.Double   class java.lang.Double
1 class java.lang.Integer
1 class java.lang.String
1 class java.lang.Integer
a class java.lang.Character

```

