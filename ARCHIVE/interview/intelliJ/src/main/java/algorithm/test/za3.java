package algorithm.test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ding on 2018/5/25.
 */
public class za3 {
    private class Node{
        private int value;
        private int N;
        private Node left,right;
        public Node(int vlaue){
            this.value = value;
        }
        public Node(int value,int N){
            this.value = value;
            this.N = N;
        }
    }
    private Node root;
    private int N;

    public int size(){ return size(root);}
    private int size(Node root){
        if(root==null){
            return 0;
        }else{
            return root.N;
        }
    }

    public void put(int value){
        root = put(root,value);
    }
    private Node put(Node root,int value){
        if(root ==null){
            return new Node(value,1);
        }else{
            if(value > root.value){
                root.right  = put(root.right , value);
            }else if(value < root.value){
                root.left = put(root.left,value);
            }else{
                root.value = root.value;
            }
        }
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    public void visit(Node root){
        System.out.print(root.value + " ");
    }

    public void preOrder(Node root){
        if(root != null){
            visit(root);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

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
            if(current.left != null){
                stack.push(current.left);
            }
        }
    }

    public void broad(Node root){
        LinkedList<Node> queue = new LinkedList<Node>();
        Node current = null;
        queue.offer(root);
        while(!queue.isEmpty()){
            current = queue.poll();
            visit(current);
            if(current.left != null){
                queue.offer(current.left);
            }
            if(current.right != null){
                queue.offer(current.right);
            }
        }
    }

    public void inOrder(Node root){
        if(root!=null){
            inOrder(root.left);
            visit(root);
            inOrder(root.right);
        }
    }

    public void inOrder2(Node root){
        LinkedList<Node> stack = new LinkedList<Node>();
        Node current = null;
        current = root;
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

    public void afterOrder(Node root){
        if(root!=null){
            afterOrder(root.left);
            afterOrder(root.right);
            visit(root);
        }
    }

    public void afterOrder2(Node root){
        LinkedList<Node> stack = new LinkedList<Node>();
        Node current, rightNode ;
        rightNode = null;
        current = root;
        while(current!=null || !stack.isEmpty()){
            while(current != null){
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            while(current.right == null || current.right == rightNode){
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
        if(root.left == null && root.right == null ){
            return root.value;
        }
        return root.value + preOrder_totalSum(root.left) + preOrder_totalSum(root.right);
    }


    public int preOrder_totalSum2(Node root){
        LinkedList<Node> stack = new LinkedList<Node>();
        int sum = 0;
        if(root == null){
            return sum;
        }
        Node current = null;
        stack.push(root);
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
        if(root.left == null && root.right == null && root.value == sum){
            return true;
        }
        return hasPathSum(root.left , sum - root.value) || hasPathSum(root.right, sum-root.value);
    }

    //===========================================q2
    public List<List<Integer>> pathSum_q2(Node root,int sum){
        if(root == null){
            return null;
        }
        LinkedList<Integer> currentResult = new LinkedList<Integer>();
        LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
        helper_q2(root,sum,currentResult,result);
        return result;
    }
    private void helper_q2(Node root,int sum, LinkedList<Integer> currentResult ,LinkedList<List<Integer>> result){
        if(root == null){
            return;
        }
        currentResult.add(root.value);
        if(root.left == null && root.right==null && root.value == sum){
            result.add(new LinkedList<>(currentResult));
            currentResult.remove(currentResult.size() - 1);
            return ;
        }else{
            helper_q2(root.left , sum - root.value , currentResult, result);
            helper_q2(root.right , sum - root.value , currentResult, result);
        }
        currentResult.remove(currentResult.size() - 1);
    }

    //==========================q3
    public int pathSum_q3(Node root){
        if(root == null){
            return 0;
        }
       return helper_q3(root,0);
    }
    private int helper_q3(Node root,int sum){
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return root.value + sum * 10;
        }

        return helper_q3(root.left, sum * 10 + root.value) + helper_q3(root.right , sum*10 + root.value);
    }

    public int getDepth(Node root){
        if(root == null){
            return 0 ;
        }
        if(root.left == null && root.right == null){
            return 1;
        }
        int nleft  = getDepth(root.left);
        int nright = getDepth(root.right);
        return Math.max(nleft, nright)+1;
    }


    //mindeepth
    public int minDepth(Node root){
        if(root == null){
            return 0 ;
        }
        if(root.left == null && root.right == null){
            return 1;
        }
        int nleft  = minDepth(root.left);
        int nright = minDepth(root.right);
        return (nleft == 0 || nright == 0) ? nleft + nright + 1 : Math.min(nleft , nright)+1;
    }

    public void invertTree(Node root){
        if(root == null){
            return;
        }
        Node tmp = root.left;
        root.left = root.right;
        root.right = tmp;
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

    public static void main(String[] args){
        za3 bst = new za3();
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
        System.out.println(bst.getDepth(bst.root));
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
       // System.out.println(bst.maxPathSum(bst.root));

        System.out.println("========invert");
        bst.invertTree(bst.root);
    }
}
