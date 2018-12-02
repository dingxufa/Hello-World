package algorithm_exercise.search;

/**
 *二叉查找树
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




    public static void main(String[] args){
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        bst.put(1,1);
        bst.put(5,5);
        bst.put(3,3);
        bst.put(9,9);
        System.out.println(bst.size());
        System.out.println(bst.get(3));

    }
}
