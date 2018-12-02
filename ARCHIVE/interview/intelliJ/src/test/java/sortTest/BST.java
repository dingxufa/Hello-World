package sortTest;

/**
 * Created by ding on 2018/3/1.
 */
public class BST<Key extends Comparable<Key>,Value> {
    private Node root;
    private class Node{
        private Key  key;
        private Value value;
        private Node left,right;
        private int N;

        public Node( Key key, Value value,int n) {
            this.value = value;
            this.key = key;
            N = n;
        }
    }
    public int size(){ return size(root);}
    private int size(Node x){
        if(x == null) return 0;
        return x.N;
    }

    //put
    public void put(Key key,Value value){
        root  = put(root,key,value);
    }
    private Node put(Node x,Key key,Value value){
        if(x == null){ return new Node(key,value,1);}
        int cmp = x.key.compareTo(key);
        if(cmp == 0){ x.value = value;}
        else if(cmp < 0 ){ x.right = put(x.right,key,value);}
        else { x.left = put(x.left,key,value);}
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    //get
    public Value get(Key key){
        return get(root,key);
    }
    private Value get(Node x,Key key){
        if(x == null){ return null;}
        int cmp = x.key.compareTo(key);
        if(cmp == 0 ){ return x.value;}
        else if(cmp < 0){ return get(x.right,key);}
        else { return get(x.left,key);}
    }


    public static void main(String[] args){
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        bst.put(5,5);
        bst.put(1,1);
        bst.put(4,4);
        bst.put(9,9);
        bst.put(3,3);
        bst.put(7,7);
        bst.put(7,17);
        System.out.println(bst.size());
        System.out.println(bst.get(12));
        System.out.println(bst.get(7));
    }
}
