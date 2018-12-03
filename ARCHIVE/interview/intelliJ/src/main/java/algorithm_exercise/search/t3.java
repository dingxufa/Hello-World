package algorithm_exercise.search;

/**
 * Created by ding on 2018/3/5.
 */
public class t3<Key extends Comparable<Key>,Value> {
    private class Node{
        private Key key;
        private Value value;
        private Node right,left;
        private int N;

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            N = n;
        }
    }
    private Node root;
    public int size(){return size(root);}
    private int size(Node root){
        if(root == null){return 0;}
        return root.N;
    }

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
        }else if(cmp <0 ){
            x.left = put(x.left,key,value);
        }else{
            x.right = put(x.right,key,value);
        }
        x.N = size(x.left)+size(x.right)+1;
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
        if(cmp < 0){
            return get(x.left,key);
        }else if(cmp > 0){
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
