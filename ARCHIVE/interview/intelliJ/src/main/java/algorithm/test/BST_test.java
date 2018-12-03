package algorithm.test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ding on 2018/4/28.
 */
public class BST_test {
    private class Node{
        private int value ;
        private Node left,right;
        private int N;
        public Node(int value,int N){
            this.value = value;
            this.N = N;
        }
    }
    private Node root;
    private int N;

    public int size(Node root){
        if(root == null){
            return 0;
        }else{
            return root.N;
        }
    }

    public void put(int value){
        root = put(root,value);
    }
    private Node put(Node root,int value){
        if(root == null){
            return new Node(value,1);
        }
        if(value > root.value){
            root.right = put(root.right,value);
        }else if(value < root.value){
            root.left = put(root.left,value);
        }else{
            root.value=value;
        }
        root.N = size(root.left)+size(root.right)+1;
        return root;
    }

    public void visit(Node x){
        System.out.print(x.value + " ");
    }
    //先序遍历
    public void preOrder(Node root){
        if(root == null){
            return;
        }
        System.out.print(root.value + " ");
        preOrder(root.left);
        preOrder(root.right);
    }
    //先序遍历  非递归
    public void preOrder2(Node root){
        LinkedList<Node> stack = new LinkedList<Node>();
        Node current = null;
        stack.push(root);
        while(!stack.isEmpty()){
            current = stack.pop();
            visit(current);
            if(current.right!=null){
                stack.push(current.right);
            }
            if(current.left!=null){
                stack.push(current.left);
            }
        }
    }
    //先序遍历 递归统计和
    public int preOrder_totalSum(Node root){
        if(root == null){ return 0;}
        if(root.left==null && root.right==null){
            return root.value;
        }
        return preOrder_totalSum(root.left)+preOrder_totalSum(root.right)+root.value;
    }
    //先序遍历  非递归
    public void preOrder2_totalSum(Node root){
        LinkedList<Node> stack = new LinkedList<Node>();
        Node tmp = new Node(0,1);
        Node current = null;
        stack.push(root);
        while(!stack.isEmpty()){
            current = stack.pop();
            visit(current);
            tmp.value += current.value;
            if(current.right!=null){
                stack.push(current.right);
            }
            if(current.left!=null){
                stack.push(current.left);
            }
        }
        System.out.println("totalsum="+tmp.value);
    }

    //===========================================q1
    //算法的复杂度是输的遍历，时间复杂度是O(n)，空间复杂度是O(logn)。
    public boolean hasPathSum(Node root,int sum){
        if(root == null){return false;}
        if(root.left == null && root.right==null && root.value == sum){
            return true;
        }
        return hasPathSum(root.left,sum-root.value) || hasPathSum(root.right,sum-root.value);
    }

    //===========================================q2
    public List<List<Integer>> pathSum_q2(Node root,int sum){
        if(root == null){
            return null;
        }
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        List<Integer> currentResult = new LinkedList<Integer>();
        helper_q2(root, sum, currentResult, result);
        return result;
    }
    private void helper_q2(Node root,int sum,List<Integer> currentResult,List<List<Integer>> result ){
        if(root == null){return;}
        currentResult.add(root.value);
        if(root.left == null && root.right == null && sum == root.value){
            result.add(new LinkedList<Integer>(currentResult));
            currentResult.remove(currentResult.size() - 1);//注意这里不能少 如果不是二叉查找树，可能有重复的节点或是多条路径，
            // 查询一条道路到叶子节点如果不符合要将先加入的这个叶子结点移除掉，成功查询到一条路径也要移除掉，否则对临时存储的currentlist会有影响，存了应该移除的叶子节点
            return;
        }else{
            helper_q2(root.left,sum-root.value,currentResult,result);
            helper_q2(root.right,sum-root.value,currentResult,result);
        }
        currentResult.remove(currentResult.size()-1);
    }

    //==========================q3
    public int pathSum_q3(Node root){
        return helper_q3(root,0);
    }
    private int helper_q3(Node root,int sum){
        if(root == null){
            return 0;
        }
        if(root.left==null && root.right==null){
            return sum*10+root.value;
        }
        return helper_q3(root.left,sum*10+root.value)+helper_q3(root.right,sum*10+root.value);
    }

    //=============q4
    /*【解析】
           1
      / \
     2   3
Return 6.
    题意：在二叉树中找一条路径，使得该路径的和最大。该路径可以从二叉树任何结点开始，也可以到任何结点结束。

    思路：递归求一条经过root的最大路径，这条路径可能是：
            1) 左边某条路径 + root + 右边某条路径
    2) 左边某条路径 + root
    3) root + 右边某条路径
    4) root*/
    private int max=Integer.MIN_VALUE;
    public int maxPathSum(Node root){
        if(root==null){return 0;}
        helper_q4(root);
        return max;
    }
    private int helper_q4(Node root){
        if(root == null){ return 0;}
        int leftVal = helper_q4(root.left);//递归求左支路的最大路径和
        int rightVal = helper_q4(root.right);//递归求右支路的最大路径和
        //如果当前局部解（root或left+root或root+right或left+root+right）是最有解，更新最终结果
        int curMax = root.value;
        if(leftVal>0){
            curMax += leftVal;
        }
        if(rightVal>0){
            curMax+=rightVal;
        }
        if(curMax>max){
            max = curMax;
            System.out.println("-----max="+max);
        }
        //返回从叶子结点到root的最大路径和（root或left+root或root+right）
        return Math.max(root.value,  Math.max(root.value+leftVal,   root.value+rightVal));

    }



    public static void main(String[] args){
        BST_test bst = new BST_test();
        bst.put(10);
        bst.put(20);
        bst.put(15);
        bst.put(3);
        bst.put(4);
        bst.put(90);

        System.out.println(bst.size(bst.root));
        System.out.println("====前序遍历  10 3 4 20 15 90");
        bst.preOrder(bst.root);
        System.out.println();
        System.out.println("====前序遍历  10 3 4 20 15 90");
        bst.preOrder2(bst.root);
        System.out.println();
        System.out.println("======preOrder_totalSum");
        System.out.println(bst.preOrder_totalSum(bst.root));
        System.out.println("======preOrder2_totalSum");
        bst.preOrder2_totalSum(bst.root);

        System.out.println("===========q1");
        System.out.println(bst.hasPathSum(bst.root,45));
        System.out.println("==========q2");
        List<List<Integer>> lists = bst.pathSum_q2(bst.root, 45);
        for (List<Integer> list : lists){
            for(Integer tmp:list){
                System.out.print(tmp + " ");
            }
            System.out.println();
        }
        System.out.println("===============q3 3539");
        System.out.println(bst.pathSum_q3(bst.root));
        System.out.println("==============q4 127");
        System.out.println(bst.maxPathSum(bst.root));
    }
}
