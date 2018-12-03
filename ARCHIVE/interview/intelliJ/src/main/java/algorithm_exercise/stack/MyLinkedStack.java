package algorithm_exercise.stack;

/**
 * 链表实现栈  [ | ]  <-  [ | ]  <- [ | ]  <-
 */
public class MyLinkedStack{
    private class Node{
        private Object obj;
        private Node next;
        public Node(Object obj, Node next) {
            this.obj = obj;
            this.next = next;
        }
    }

    private Node top;
    private int size;

    public void push(Object object){
        Node node = new Node(object, null);
        if(size==0){
            top = node;
        }else{
            node.next = top;   // [ | ]  <-  [ | ]  <- [ | ]  <-
           // top.next = node;  wrong
            top = node;
        }
        size++;
    }

    public Object pop(){
        if(size>0){
            Node node = top;
            top = top.next;
            size--;
            return node.obj;
        }
        return null;
    }


    public static void main(String[] args){
        MyLinkedStack myStack = new MyLinkedStack();
        myStack.push(1);
        myStack.push("2");
        myStack.push("3");
        myStack.push(4);
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());

    }
}
