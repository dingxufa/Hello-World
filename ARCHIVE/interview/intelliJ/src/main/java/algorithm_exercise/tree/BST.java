package algorithm_exercise.tree;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *二叉查找树  左节点  < 父节点 < 右节点 (不存在等于的情况)
 * 最好的情况是 O(logn)，存在于完全二叉排序树情况下，其访问性能近似于折半查找；
 最差时候会是 O(n)，比如插入的元素是有序的，生成的二叉排序树就是一个链表，这种情况下，需要遍历全部元素才行
 */
public class BST<Key extends  Comparable<Key>,Value> {
    private Node root;
    private class Node{
        private Key key;
        private Value value;
        private Node left,right;
        private int N;
        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            N = n;
        }
    }
    public int size(){ return size(root);}
    public int size(Node x){
        if(x == null){ return 0;}
        return x.N;
    }
    ///每个node节点都有一个左指针 一个右指针
    //查找树有个root指针，指向根节点
    public void put(Key key,Value value){
        root = put(root,key,value);
    }
    private Node put(Node x,Key key,Value value){
        if(x == null){
            return new Node(key,value,1);
        }
        int cmp = key.compareTo(x.key);
        if(cmp == 0){
            x.value = value;
        }else if(cmp < 0 ){
            x.left = put(x.left,key,value);
        }else{
            x.right =put(x.right,key,value);
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;

    }
    ///
    public Value get(Key key){
        return get(root,key);
    }
    private Value get(Node x,Key key){
        if(x==null){ return null;}
        int cmp = key.compareTo(x.key);
        if(cmp < 0 ){
            return  get(x.left,key);
        }else if(cmp >0 ){
            return get(x.right,key);
        }else{
            return x.value;
        }
    }
    //前序遍历
    public  void frontNode(Node x){
        if(x != null){
            System.out.println("( "+x.key+", " + x.value+" )");
            //前序遍历左子树
            frontNode(x.left);
            //前序遍历右子树
            frontNode(x.right);
        }
    }
    //前序遍历非递归
    public void preOrderTraverse(Node x){
        LinkedList<Node> stack = new LinkedList<Node>();
        Node currentNode = null;
        stack.push(x);
        while(!stack.isEmpty()){
            currentNode = stack.poll();
            System.out.println("( "+currentNode.key+", " + currentNode.value+" )");
            if(currentNode.right != null){
                stack.push(currentNode.right);
            }
            if(currentNode.left!=null){
                stack.push(currentNode.left);
            }
        }
    }
    //中序遍历
    public void inOrder(Node x){
        if(x != null){
            //中序遍历左子树
            inOrder(x.left);
            //访问根节点
            System.out.println("( "+x.key+", " + x.value+" )");
            //中序遍历右子树
            inOrder(x.right);
        }
    }
    //中序遍历非递归
    public void inOrderTraverse(Node x){
        LinkedList<Node> stack = new LinkedList<Node>();
        Node currentNode = x;
        while(currentNode != null  || !stack.isEmpty()){
            // 一直循环到二叉排序树最左端的叶子结点（currentNode是null）
            while(currentNode != null){
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = stack.pop();
            System.out.println("( "+currentNode.key+", " + currentNode.value+" )");
            currentNode = currentNode.right;
        }
    }
    //后序遍历
    public void afterOrder(Node x){
        if(x!=null){
            //后序遍历左子树
            afterOrder(x.left);
            //后序遍历右子树
            afterOrder(x.right);
            //访问根节点
            System.out.println("( "+x.key+", " + x.value+" )");
        }
    }
    //后序遍历 非递归
    public void postOrderTraverse(Node x){
        LinkedList<Node> stack = new LinkedList<Node>();
        Node currentNode  =x; // root;
        Node rightNode = null;
        while(currentNode != null || !stack.isEmpty()){
            // 一直循环到二叉排序树最左端的叶子结点（currentNode是null）
            while (currentNode != null){
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = stack.pop();
            // 当前结点没有右结点或上一个结点（已经输出的结点）是当前结点的右结点，则输出当前结点
            while(currentNode.right == null || currentNode.right == rightNode){
                System.out.println("( "+currentNode.key+", " + currentNode.value+" )");
                rightNode =currentNode;
                if (stack.isEmpty()){
                    return;//root以输出，则遍历结束
                }
                currentNode = stack.pop();
            }
            stack.push(currentNode); //还有右结点没有遍历
            currentNode = currentNode.right;
        }
    }
    //广度优先
    public void broadTraverse(Node x){
        LinkedList<Node> queue = new LinkedList<Node>();
        Node currentNode = null;
        queue.offer(x);
        while(!queue.isEmpty()){
            currentNode = queue.poll();
            System.out.println("( "+currentNode.key+", " + currentNode.value+" )");
            if(currentNode.left != null){
                queue.offer(currentNode.left);
            }
            if(currentNode.right != null){
                queue.offer(currentNode.right);
            }
        }
    }

    //获得树的深度
    public  int getDeepth(Node x){
        if(x == null) return 0;
        int nLeft = getDeepth(x.left);
        int nRight = getDeepth(x.right);
        return nLeft > nRight ?  nLeft+1 : nRight+1;
    }

    public static void main(String[] args){
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
//        bst.put(10,10);
//        bst.put(5,5);
//        bst.put(13,13);
//        bst.put(9,9);
//        bst.put(4,4);
//        bst.put(14,14);
//        bst.put(7,7);

        bst.put(10,10);
        bst.put(20,20);
        bst.put(15,15);
        bst.put(3,3);
        bst.put(4,4);
        bst.put(90,90);

//        bst.put(35,35);
//        bst.put(20,20);
//        bst.put(15,15);
//        bst.put(16,16);
//        bst.put(29,29);
//        bst.put(29,29);
//        bst.put(28,28);
//        bst.put(30,30);
//        bst.put(40,40);
//        bst.put(50,50);
//        bst.put(45,45);
//        bst.put(55,55);


        System.out.println(bst.size());
        System.out.println(bst.get(3));

        System.out.println("====前序遍历 10 3 4 20 15 90");
        bst.frontNode(bst.root);
        System.out.println("====前序遍历2");
        bst.preOrderTraverse(bst.root);


        System.out.println("====中序遍历  3 4 10 15 20 90");
        bst.inOrder(bst.root);
        System.out.println("====中序遍历2");  //递增
        bst.inOrderTraverse(bst.root);

        System.out.println("====后序遍历 4 3 15 90 20 10");
        bst.afterOrder(bst.root);
        System.out.println("====后序遍历2  ");
        bst.postOrderTraverse(bst.root);

        System.out.println("=====广度优先遍历 10 3 20 4 15 90");
        bst.broadTraverse(bst.root);

        System.out.println("====getDeepth: 3 ");
        System.out.println(bst.getDeepth(bst.root));
        System.out.println();

        System.out.println("====删除");
        System.out.println();
        bst.delete(10);
        bst.inOrder(bst.root);




    }

    public boolean delete(Key key){
        //引用当前节点，从根节点开始
        Node current = root;
        //引用当前节点的父节点；
        Node parent = root;
        //是否为左节点
        boolean isLeftChild = true;
        while(current.key.compareTo(key) != 0){
            parent = current;
            //进行比较，比较查找值和当前节点的大小
            if(current.key.compareTo(key) > 0){
                current = current.left;
                isLeftChild = true;
            }else {
                current = current.right;
                isLeftChild = false;
            }
            //如果查找不到
            if(current == null){
                return false;
            }
        }
        //删除叶子节点，也就是该节点没有子节点
        if(current.left == null && current.right == null){
            if(current == root){
                root = null;
            }
            //如果它是父节点的左子节点
            else if (isLeftChild){
                parent.left = null;
            }else{
                parent.right = null;
            }
        }
        //有一个左子节点
        if(current.left != null && current.right == null){
            if(current == root){
                root = current.left;
            }
            else if(isLeftChild){//当前节点（匹配的节点）是父节点的左子节点
                parent.left = current.left;
            }else{
                parent.right = current.left;
            }
        }
        //有一个右子节点
        if(current.left == null && current.right != null){
            if(current == root){
                root = current.right;
            }
            else if(isLeftChild){//当前节点（匹配的节点）是父节点的左子节点
                parent.left =current.right;
            }else{
                parent.right = current.right;
            }
        }
        //有两个子节点
        if(current.left != null && current.right != null){
            Node successor = getSuccessor(current);
            if(current == root){
                root = successor;
            }else if(isLeftChild){
                parent.left = successor;
            }else{
                parent.right = successor;
            }
            successor.left = current.left;
        }
        return true;
    }
    //获取中序后继节点
    public Node getSuccessor(Node delNode){
        System.out.println("=====xxx=== " + getSuccessor3(delNode).value);
        //successor就是要查找的中序后继节点
        Node successor = delNode;
        Node successorParent = delNode;
        //当前遍历的节点
        Node current = delNode.right;
        while(current != null){
            successorParent = successor;
            successor =current;
            current = current.left;
        }
        //如果找到的节点不是要删除节点的右子节点
        if(successor != delNode.right){
            successorParent.left = successor.right;
            successor.right = delNode.right;//替换完成了一半
        }
        System.out.println("====final==="+successor.value);
        return successor;
    }
    //获取中序后继节点
    public Node getSuccessor2(Node x){
        Node t =null;
        t = x.left;
        while(t.right!=null){
            t = t.right;
        }
        return t;
    }
    //获取中序后继节点
    public   Node getSuccessor3(Node x){
        Node t =null;
        t = x.right;
        while(t.left!=null){
            t = t.left;
        }
        return t;
    }

}


//        先序遍历（递归）：35 20 15 16 29 28 30 40 50 45 55
//        中序遍历（递归）：15 16 20 28 29 30 35 40 45 50 55
//        后序遍历（递归）：16 15 28 30 29 20 45 55 50 40 35
//        先序遍历（非递归）：35 20 15 16 29 28 30 40 50 45 55
//        中序遍历（非递归）：15 16 20 28 29 30 35 40 45 50 55
//        后序遍历（非递归）：16 15 28 30 29 20 45 55 50 40 35
//        广度优先遍历：35 20 40 15 29 50 16 28 30 45 55