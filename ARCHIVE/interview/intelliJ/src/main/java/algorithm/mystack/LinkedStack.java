package algorithm.mystack;

/**
 * 用链表实现栈
 */
public class LinkedStack<Item> {
    private class Node{
        private Node next;
        private Item item;

        public Node(Node next, Item item) {
            this.next = next;
            this.item = item;
        }
    }

    private Node top = null;
    private int size = 0;

    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }

    public void push(Item item){
        Node newTop = new Node(top,item);
        top = newTop;
        size++;
    }
    public Item pop(){
        if(size >0){
            Item item = top.item;
            top = top.next;
            size--;
            return item;
        }
        return null;
    }

    public static void main(String[] args){
        LinkedStack<Object> myStack = new LinkedStack<Object>();
        myStack.push("1");
        myStack.push("2");
        myStack.push("3");
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
//        3
//        2
//        1
//        Exception in thread "main" java.lang.NullPointerException
//        at algorithm.mystack.LinkedStack$Node.access$000(LinkedStack.java:9)
//        at algorithm.mystack.LinkedStack.pop(LinkedStack.java:35)
    }
}


