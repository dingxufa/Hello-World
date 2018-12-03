package algorithm_exercise.stack;

import clojure.lang.Obj;

/**
 * 链表实现栈，并记录最大值和最小值
 */
public class t2<Item extends Comparable>{
    private class Node implements Comparable<Item>{
        private Item object;
        private Node next;

        public Node(Item object, Node next) {
            this.object = object;
            this.next = next;
        }

        @Override
        public int compareTo(Item item) {
            return object.compareTo(item);
        }
    }

    private Node top;
    private Node max,min;
    private int size;
   // private t2 maxStack,minStack;

    public t2(){

    }

    public void push(Item object){
        Node node = new Node(object, null);
        if(size== 0 ){
            top = node;

            max=node;
            min=node;
//           maxStack.push(node);
//           minStack.push(node);
        }else{
            node.next = top;
            top = node;

            //记录最大值
            if(object.compareTo(max.object) >0){
                max = node;
//                maxStack.push(node);
            }
            //记录最小值
            if(object.compareTo(min.object)<0){
                min=node;
//                minStack.push(node);
            }
        }
        size++;



    }

    public Item pop(){
        if(size>0){
            Node node = top;
            top.next = top;
            size--;

//            if(max == node){
//                maxStack.pop();
//                max = maxStack.top;
//            }
//            if(min == node){
//                minStack.pop();
//                min = minStack.top;
//            }

            return (Item)node.object;
        }
        return null;
    }

    public static void main(String[] args){
        t2<Integer> stack = new t2<Integer>();
        stack.push(1);

        stack.push(3);
        stack.push(6);
        stack.push(8);
        stack.push(10);
        System.out.println(stack.size);


        System.out.println(stack.max.object);
        System.out.println(stack.min.object);


        stack.pop();
        System.out.println(stack.max.object);
        System.out.println(stack.min.object);

    }

}
