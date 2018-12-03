package algorithm.test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ding on 2018/4/29.
 */
public class za {
    private class Node{
        private int value;
        private Node left,right;
        private int N;
        public Node(int value,int N){
            this.value = value;
            this.N = N;
        }
        public Node(int value){
            this.value = value;
        }
    }
    private Node root;
    public int size(Node root){
        if(root == null){ return 0;}
        return root.N;
    }
    public void put(int value){
        root = put(root,value);
    }
    private Node put(Node root,int value){
        if(root == null){ return new Node(value,1);}
        if(value > root.value){
            root.right = put(root.right,value);
        }else if(value < root.value){
            root.left = put(root.left,value);
        }else{
            root.value = value;
        }
        root.N = size(root.left) + size(root.right)+1;
        return root;
    }
    public void visit(Node x){
        System.out.print(x.value + " ");
    }

    //先序遍历 递归
    public void preOrder(Node root){
        if(root!=null){
            visit(root);
            preOrder(root.left);
            preOrder(root.right);
        }
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
    //广度遍历
    public void broad(Node root){
        LinkedList<Node> queue = new LinkedList<Node>();
        Node current = null;
        queue.offer(root);
        while(!queue.isEmpty()){
            current = queue.poll();
            visit(current);
            if(current.left!=null){
                queue.offer(current.left);
            }
            if(current.right!=null){
                queue.offer(current.right);
            }
        }
    }
    //获得树深度
    public int getDeepth(Node root){
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return 1;
        }
        //return Math.max(getDeepth(root.left),getDeepth(root.right))+1;
        int nleft = getDeepth(root.left);
        int nright = getDeepth(root.right);
        return nleft>nright?nleft+1:nright+1;
    }
    //中序遍历 递归
    public void inOrder(Node root){
        if(root!=null){
            inOrder(root.left);
            visit(root);
            inOrder(root.right);
        }
    }
    //中序遍历非递归
    public void inOrder2(Node root){
        LinkedList<Node> stack = new LinkedList<Node>();
        Node current = root;
        while(current!=null || !stack.isEmpty()){
            while(current!=null){
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            visit(current);
            current = current.right;
        }
    }
    //后序遍历 递归
    public void afterOrder(Node root){
        if(root!=null){
            afterOrder(root.left);
            afterOrder(root.right);
            visit(root);
        }
    }
    //后序遍历 非递归
    public void afterOrder2(Node root){
        LinkedList<Node> stack = new LinkedList<Node>();
        Node current = root;
        Node rightNode = null;
        while(current!=null || !stack.isEmpty()){
            while(current!=null){
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            while(current.right==null || current.right == rightNode){
                visit(current);
                rightNode = current;
                if(stack.isEmpty()){
                    return;
                }
                current = stack.pop();
            }
            stack.push(current);
            current = current.right;
        }
    }

    public int preOrder_totalSum(Node root){
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return root.value;
        }
        return preOrder_totalSum(root.left) + preOrder_totalSum(root.right)+root.value;
    }

    public int preOrder_totalSum2(Node root){
        LinkedList<Node> stack = new LinkedList<Node>();
        Node current = null;
        stack.push(root);
        int sum = 0;
        while(!stack.isEmpty()){
            current = stack.pop();
            sum += current.value;
            if(current.right!=null){
                stack.push(current.right);
            }
            if(current.left!=null){
                stack.push(current.left);
            }
        }
        return sum;
    }

    //===========================================q1
    public boolean hasPathSum(Node root,int sum){
        if(root == null){
            return false;
        }
        if(root.left==null && root.right  == null && root.value == sum){
            return true;
        }
        return hasPathSum(root.left, sum - root.value) || hasPathSum(root.right, sum - root.value);
    }

    //===========================================q2
    public List<List<Integer>> pathSum_q2(Node root,int sum){
        if(root == null){
            return null;
        }
        LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
        LinkedList<Integer> currentResult = new LinkedList<Integer>();
        helper_q2(root,sum,currentResult,result);
        return result;
    }
    private void helper_q2(Node root,int sum, LinkedList<Integer> currentResult ,LinkedList<List<Integer>> result ){
        if(root == null){
            return;
        }
        currentResult.add(root.value);
        if(root.left==null && root.right==null && root.value==sum){
            result.add(new LinkedList<Integer>(currentResult));
            currentResult.remove(currentResult.size()-1);
            return;
        }else{
            helper_q2(root.left, sum - root.value,currentResult,result);
            helper_q2(root.right, sum - root.value,currentResult,result);
        }
        currentResult.remove(currentResult.size()-1);
    }

    //==========================q3
    public int pathSum_q3(Node root){
        if(root==null){
            return 0;
        }
        return helper_q3(root,0);
    }
    private int helper_q3(Node root,int sum){
        if(root == null){
            return 0;
        }
        if(root.left==null && root.right==null){
            return sum *10+root.value;
        }
        return helper_q3(root.left,sum*10+root.value) + helper_q3(root.right, sum * 10 + root.value);
    }
    //=============q4
    private int max = Integer.MIN_VALUE;
    public int maxPathSum(Node root){
        if(root==null){
            return 0;
        }
        helper_q4(root);
        return max;
    }
    private int helper_q4(Node root){
        if(root == null){
            return 0;
        }
        if(root.left==null && root.right==null){
            return root.value;
        }
        int leftVal = helper_q4(root.left);
        int rightVal = helper_q4(root.right);

        int curMax = root.value;
        if(leftVal>0){
            curMax += leftVal;
        }
        if(rightVal>0){
            curMax+=rightVal;
        }
        if(curMax > max){
            max = curMax;
        }
        return Math.max(root.value, Math.max(root.value + leftVal, root.value + rightVal));
    }

    //
    public int getDepth(Node root){
        if(root==null){
            return 0;
        }
        if(root.left==null &&  root.right==null){
            return 1;
        }
        return Math.max(getDepth(root.left),getDepth(root.right))+1;
    }

    //mindeepth
    public int minDepth(Node root){
        if(root==null){
            return 0;
        }
        if(root.left==null && root.right==null){
            return 1;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);

        return (left == 0 || right == 0)? left + right + 1 : Math.min(left,right)+1;

    }

    //traverse
    public void invertTree(Node root){
        if(root == null){
            return;
        }
        Node left = root.left;
        root.left = root.right;
        root.right = left;
        invertTree(root.left);
        invertTree(root.right);
    }

    //
    public Node mergeTrees(Node t1,Node t2){
        if(t1 == null && t2 == null){
            return null;
        }
        if(t1==null){return t2;}
        if(t2 == null ){return t1;}
        Node root = new Node(t1.value + t2.value);
        root.left = mergeTrees(t1.left , t2.left);
        root.right = mergeTrees(t1.right , t2.right);
        return root;
    }

    ////////============================q5




    public static void main(String[] args){
        za bst = new za();
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
        System.out.println("======preOrder_totalSum2");
        System.out.println(bst.preOrder_totalSum2(bst.root));

        System.out.println("====中序遍历  3 4 10 15 20 90");
        bst.inOrder(bst.root);
        System.out.println();
        System.out.println("====中序遍历2");  //递增
        bst.inOrder2(bst.root);
        System.out.println();

        System.out.println("====后序遍历 4 3 15 90 20 10");
        bst.afterOrder(bst.root);
        System.out.println();
        System.out.println("====后序遍历2  ");
        bst.afterOrder2(bst.root);
        System.out.println();

        System.out.println("=====广度优先遍历 10 3 20 4 15 90");
        bst.broad(bst.root);
        System.out.println();

        System.out.println("====getDeepth: 3 ");
        System.out.println(bst.getDeepth(bst.root));
        System.out.println();
        System.out.println("====minDepth: 3 ");
        System.out.println(bst.minDepth(bst.root));
        System.out.println();


        System.out.println("===========q1");
        System.out.println(bst.hasPathSum(bst.root,45));
        System.out.println();
        System.out.println("==========q2");
        List<List<Integer>> lists = bst.pathSum_q2(bst.root, 45); //17
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

        System.out.println("========invert");
        bst.invertTree(bst.root);
    }



}
