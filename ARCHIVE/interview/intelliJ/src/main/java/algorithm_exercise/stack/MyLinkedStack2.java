package algorithm_exercise.stack;

/**
 * 链表实现栈，并记录最大值和最小值
 */
public class MyLinkedStack2<Item extends Comparable> {
    private class Node {
        private Item object;
        private Node next;

        public Node(Item object, Node next) {
            this.object = object;
            this.next = next;
        }
    }

    private Node top;
    private Node max,min;
    private int size;


    public void push(Item object){
        Node node = new Node(object, null);
        if(size== 0 ){
            top = node;

            max=node;
            min=node;
        }else{
            node.next = top;
            top = node;

            //记录最大值
            if(object.compareTo(max.object) >0){
                max = node;
            }
            //记录最小值
            if(object.compareTo(min.object)<0){
                min=node;
            }
        }
        size++;
    }

    public Item pop(){
        if(size>0){
            Node node = top;
            top.next = top;
            size--;
            return (Item)node.object;
        }
        return null;
    }
}
