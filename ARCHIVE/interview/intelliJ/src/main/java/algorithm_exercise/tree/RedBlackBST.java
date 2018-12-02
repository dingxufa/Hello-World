package algorithm_exercise.tree;
/*红黑树的特性:
        （1）每个节点或者是黑色，或者是红色。
        （2）根节点是黑色。
        （4）如果一个节点是红色的，则它的子节点必须是黑色的。 //红色节点不能连续
        （5）从一个节点到该节点的子孙节点的所有路径上包含相同数目的黑节点。
        性质1. 节点是红色或黑色。
        性质2. 根节点是黑色。
        性质3 每个叶节点（NIL节点，空节点）是黑色的。
        性质4 每个红色节点的两个子节点都是黑色。(从每个叶子到根的所有路径上不能有两个连续的红色节点)
        性质5. 从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点
        主要是用它来存储有序的数据，它的时间复杂度是O(lgn)，效率非常之高。


        一颗大小为 N 的红黑树的高度不会超过 2logN。最坏的情况下是它所对应的 2-3 树，构成最左边的路径节点全部都是 3- 节点而其余都是 2- 节点。

红黑树大多数的操作所需要的时间都是对数级别的。

每个节点要么是红色，要么是黑色。
根节点必须是黑色
红色节点不能连续（也即是，红色节点的孩子和父亲都不能是红色）。
对于每个节点，从该点至null（树尾端）的任何路径，都含有相同个数的黑色节点。
        */
public class RedBlackBST <Key extends Comparable<Key>,Value> {
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node{
        Key key;
        Value value;
        Node left, right;
        int N;
        boolean color;
        Node(Key key, Value value, int n, boolean color) {
            this.key = key;
            this.value = value;
            N = n;
            this.color = color;
        }
    }
    private boolean isRed(Node x){
        if(x == null) return false;
        return x.color == RED;
    }
    public int size(){ return size(root);}
    public int size(Node x){
        if(x == null){ return 0;}
        return x.N;
    }

    public Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left)+size(h.right);  // 1是节点本身 + size(left) + size(right)
        return x;
    }

    public Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h ;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left)+size(h.right);
        return x;
    }

    public void flipColors(Node h){//如果一个节点是红色的，则它的子节点必须是黑色的。
        h.color =RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }


    public void put(Key key,Value value){
        root = put(root,key,value);
        root.color = BLACK;
    }

    private Node put(Node x,Key key,Value value){
        if(x==null){ return new Node(key,value,1,RED);}
        int cmp = key.compareTo(x.key);
        if(cmp == 0){
            x.value  = value;
        }else if(cmp <0 ){
            x.left = put(x.left,key,value);
        }else {
            x.right = put(x.right,key,value);
        }
//        如果右子节点是红色的而左子节点是黑色的，进行左旋转；
//        如果左子节点是红色的，而且左子节点的左子节点也是红色的，进行右旋转；
//        如果左右子节点均为红色的，进行颜色转换。
        if(isRed(x.right) && !isRed(x.left)) x=rotateLeft(x);
        if(isRed(x.left) && isRed(x.left.left)) x=rotateRight(x);
        if(isRed(x.left) && isRed(x.right)) flipColors(x);
        x.N= 1 + size(x.left) + size(x.right);
        return x;
    }

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


    public static void main(String[] args){
        RedBlackBST<Integer, Integer> bst = new RedBlackBST<Integer, Integer>();
        bst.put(35,35);
        bst.put(20,20);
        bst.put(15,15);
        bst.put(16,16);
        bst.put(29,29);
        bst.put(29,29);
        bst.put(28,28);
        bst.put(30,30);
        bst.put(40,40);
        bst.put(50,50);
        bst.put(45,45);
        bst.put(55,55);


        System.out.println(bst.size());
        System.out.println(bst.get(30));
    }
}
