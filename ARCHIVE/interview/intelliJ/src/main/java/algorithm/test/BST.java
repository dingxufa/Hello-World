package algorithm.test;

import java.util.LinkedList;

/**
 * Created by ding on 2018/4/25.
 */
public class BST<Key extends Comparable,Value> {
    private class Node{
        private Key key;
        private Value value;
        private int N;
        private Node left,right;
        public Node(Key key,Value value,int N){
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }
    private Node root;

    public int size(){return size(root);}
    public int size(Node x){
        if (x == null){
            return 0;
        }else{
            return x.N;
        }
    }

    //put
    public void put(Key key,Value value){
        root = put(root,key,value);
    }
    private Node put(Node x,Key key,Value value){
        if(x == null){
            return new Node(key,value,1);
        }else if( key.compareTo(x.key)<0){
            x.left = put(x.left,key,value);
        }else if(key.compareTo(x.key)>0){
            x.right = put(x.right,key,value);
        }else{
            x.value = value;
        }
        x.N = size(x.left) + size(x.right)+1;
        return x;
    }

    public Value get(Key key){
        return get(root,key);
    }
    private Value get(Node x,Key key){
        if(x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if(cmp <0 ){
           return get(x.left,key);
        }else if(cmp>0){
            return get(x.right,key);
        }else{
            return x.value;
        }
    }

    public void preOrder(Node x){
        if(x != null){
            //System.out.println("key="+x.key+" value="+x.value);
            System.out.print(x.value +" ");
            preOrder(x.left);
            preOrder(x.right);
        }
    }
    public void preOrder2(Node x){
        LinkedList<Node> stack = new LinkedList<Node>();
        Node current = null;
        stack.push(x);
        while(!stack.isEmpty()){
            current = stack.pop();
            System.out.print(current.value + " ");
            if(current.right!=null){
                stack.push(current.right);
            }
            if(current.left!=null){
                stack.push(current.left);
            }
        }
    }

    public void broad2(Node x){
        LinkedList<Node> queue = new LinkedList<Node>();
        Node current = null;
        queue.offer(x);
        while(!queue.isEmpty()){
            current = queue.poll();
            System.out.print(current.value + " ");
            if(current.left!=null){
                queue.offer(current.left);
            }
            if(current.right!=null){
                queue.offer(current.right);
            }
        }
    }
    public void inOrder(Node x){
        if(x!=null){
            inOrder(x.left);
            System.out.print(x.value + " ");
            inOrder(x.right);
        }
    }
    public void inOrder2(Node x){
        LinkedList<Node> stack = new LinkedList<Node>();
        Node current = null;
        current = x;
        while(current!=null || !stack.isEmpty()){
            while(current!=null){
                stack.push(current);
                current = current.left;
            }
            current = stack.poll();
            current = current.right;
        }
    }



    public void afterOrder(Node x){
        if (x != null){
            afterOrder(x.left);
            afterOrder(x.right);
            System.out.print(x.value + " ");
        }
    }
   /* public void afterOrder2(Node x){
        LinkedList<Node> stack = new LinkedList<Node>();
        Node current = null;
        current = x;
        while(current!= null || !stack.isEmpty()){
            while(current!=null){
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
        }
    }*/

    public void broad(Node x){
        LinkedList<Node> queue = new LinkedList<Node>();
        Node current = null;
        queue.offer(x);
        while(!queue.isEmpty()){
            current = queue.poll();
            System.out.print(current.value + " ");
            if(current.left!=null){
                queue.offer(current.left);
            }
            if(current.right!=null){
                queue.offer(current.right);
            }
        }
    }


    public int preOrderGetSum(Node x){
        if(x!=null){
            if(x.left == null && x.right==null){//叶子节点
                return Integer.parseInt(x.value.toString());
            }
            return Integer.parseInt(x.value.toString())+ preOrderGetSum(x.left)+preOrderGetSum(x.right);
        }
        return 0;
    }



    public static void main(String[] args){
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        bst.put(10,10);
        bst.put(20,20);
        bst.put(15,15);
        bst.put(3,3);
        bst.put(4,4);
        bst.put(90,90);
        System.out.println(bst.size());
        System.out.println(bst.get(3));

        System.out.println("====前序遍历");
        bst.preOrder(bst.root);
        System.out.println("\n====前序遍历2");
        bst.preOrder2(bst.root);

        System.out.println("\n====中序遍历");
        bst.inOrder(bst.root);
        System.out.println("\n====中序遍历2");  //递增
        bst.inOrder2(bst.root);

        System.out.println("\n====后序遍历");
        bst.afterOrder(bst.root);
        System.out.println("\n====后序遍历2");
        //bst.postOrderTraverse(bst.root);

        System.out.println("\n=====广度优先遍历");
       bst.broad(bst.root);
        System.out.println("\n=====广度优先遍历2");
        bst.broad2(bst.root);

        System.out.println("=============");
        System.out.println(bst.preOrderGetSum(bst.root));
        //bst.preOrderGetSum2(bst.root);
    }
}
