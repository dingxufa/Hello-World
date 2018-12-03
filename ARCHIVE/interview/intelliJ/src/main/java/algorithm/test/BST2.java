package algorithm.test;

import java.util.ArrayList;

/**
 * Created by ding on 2018/4/28.
 */
public class BST2 {
    private class Node{
        private int key;
        private int value;
        private int N;
        private Node left,right;
        public Node(int key,int value,int N){
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
    public void put(int key,int value){
        root = put(root,key,value);
    }
    private Node put(Node x,int key,int value){
        if(x == null){
            return new Node(key,value,1);
        }else if( key - x.key <0){
            x.left = put(x.left,key,value);
        }else if( key - x.key>0){
            x.right = put(x.right,key,value);
        }else{
            x.value = value;
        }
        x.N = size(x.left) + size(x.right)+1;
        return x;
    }

    public int get(int key){
        return get(root,key);
    }
    private int get(Node x,int key){
        if(x == null){
            return -1;
        }
        if(key - x.key <0 ){
            return get(x.left,key);
        }else if(key - x.key>0){
            return get(x.right,key);
        }else{
            return x.value;
        }
    }


    public ArrayList<ArrayList<Integer>> pathSum(Node x,int needSum){
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (x == null){
            return res;
        }
        ArrayList<Integer> item = new ArrayList<Integer>();
        item.add(x.key);
        helper(x,needSum - x.key,item,res);
        return res;
    }
    private void helper(Node x ,int needSum,ArrayList<Integer> item,ArrayList<ArrayList<Integer>> res){
        if (x == null){return;}
        if(x.left == null && x.right == null && needSum==0){
            res.add(new ArrayList<Integer>(item));   //res.add(item);  这个是不对的，这里的item相当于一个临时变量，每成功一次要保存该次结果
            return;
        }
        if(x.left != null){
            item.add(x.left.key);
            helper(x.left,needSum - x.left.key,item,res);
            item.remove(item.size()-1);
        }
        if(x.right != null){
            item.add(x.right.key);
            helper(x.right,needSum - x.right.key,item,res);
            item.remove(item.size()-1);
        }
    }


    public int sumNumbers(Node root){
        return helper(root,0);
    }
    private int helper(Node root,int sum){
        if(root == null){ return 0;}
        if(root.left == null && root.right==null ){
            return sum*10+root.key;
        }
        return helper(root.left,sum*10+root.value) + helper(root.right,sum*10+root.value);
    }


    //Binary Tree Maximum Path Sum -- LeetCode
    //原题链接: http://oj.leetcode.com/problems/binary-tree-maximum-path-sum/
    //这道题是求树的路径和的题目，不过和平常不同的是这里的路径不仅可以从根到某一个结点，而且路径可以从左子树某一个结点，
    // 然后到达右子树的结点，就像题目中所说的可以起始和终结于任何结点。在这里树没有被看成有向图，而是被当成无向图来寻找路径。
    // 因为这个路径的灵活性，我们需要对递归返回值进行一些调整，而不是通常的返回要求的结果。在这里，函数的返回值定义为以自己为根的
    // 一条从根到子结点的最长路径（这里路径就不是当成无向图了，必须往单方向走）。这个返回值是为了提供给它的父结点计算自身的最长路径用，
    // 而结点自身的最长路径（也就是可以从左到右那种）则只需计算然后更新即可。这样一来，
    // 一个结点自身的最长路径就是它的左子树返回值（如果大于0的话），加上右子树的返回值（如果大于0的话），再加上自己的值。
    // 而返回值则是自己的值加上左子树返回值，右子树返回值或者0（注意这里是“或者”，而不是“加上”，因为返回值只取一支的路径和）。
    // 在过程中求得当前最长路径时比较一下是不是目前最长的，如果是则更新。算法的本质还是一次树的遍历，所以复杂度是O(n)。
    // 而空间上仍然是栈大小O(logn)。代码如下：
//    Input: [1,2,3]
//
//              1
//            / \
//           2   3
//
//    Output: 6

    public int maxPathSum4(Node root){
        if (root == null){
            return 0;
        }
        ArrayList<Integer> res = new ArrayList<Integer>();
        res.add(Integer.MIN_VALUE);
        helper(root,res);
        return res.get(0);
    }
    private int helper(Node root,ArrayList<Integer>res){
        if(root == null){
            return 0;
        }
        int left = helper(root.left,res);
        int right = helper(root.right,res);
        int cur = root.key + (left>0?left:0) + (right>0?right:0);
        if(cur>res.get(0)){
            res.set(0,cur);
        }
        return root.value + Math.max(left,Math.max(right,0));
    }

    //solution2
    private int max = Integer.MIN_VALUE;
    public int maxPathSum(Node root){
        helper(root);
        return max;
    }
    // helper returns the max branch
    // plus current node's value
    private int helper(Node root){
        if(root == null){return 0;}
        int left = Math.max( helper(root.left),0 );
        int right = Math.max( helper(root.right),0);
        max = Math.max(max,root.key + left + right);
        return root.value + Math.max(left,right);
    }




    public static void main(String[] args){
        BST2 bst = new BST2();
        bst.put(10, 10);
        bst.put(20, 20);
        bst.put(15, 15);
        bst.put(3, 3);
        bst.put(4, 4);
        bst.put(90, 90);
        System.out.println(bst.size());
        System.out.println(bst.get(3));

        System.out.println("=====sumPath2");

        ArrayList<ArrayList<Integer>> lists = bst.pathSum(bst.root, 45);
        for(ArrayList<Integer> list :lists){
            for(Integer tmp:list){
                System.out.print(tmp + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
