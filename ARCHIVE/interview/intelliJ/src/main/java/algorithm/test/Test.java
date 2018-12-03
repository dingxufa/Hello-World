package algorithm.test;


import java.util.LinkedList;
import java.util.List;

public class Test {
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
            root.value = value;
        }
        root.N = size(root.left)+size(root.right)+1;
        return root;
    }

    private void visit(Node x){
        System.out.print(x.value + " ");
    }
    //
    public void preOrder(Node root){
        if(root != null){
            visit(root);
            preOrder(root.left);
            preOrder(root.right);
        }
    }
    //
    public void preOrder2(Node root){
        LinkedList<Node> stack = new LinkedList<Node>();
        Node cuurrent = null;
        stack.push(root);
        while(!stack.isEmpty()){
            cuurrent = stack.pop();
            visit(cuurrent);
            if(cuurrent.right!=null){
                stack.push(cuurrent.right);
            }
            if(cuurrent.left!=null){
                stack.push(cuurrent.left);
            }
        }
    }

    //先序遍历 统计节点总和
    public int preOrder_totalSum(Node root){
        if(root == null){
            return 0;
        }
        if(root.left==null && root.right==null){ //叶子节点
            return root.value;
        }
        return root.value+preOrder_totalSum(root.left)+preOrder_totalSum(root.right);
    }
    //先序遍历  统计节点总和非递归
    public int preOrder_totalSum2(Node root ){
        if(root == null){
            return 0;
        }
        int sum =0;
        LinkedList<Node> stack = new LinkedList<Node>();
        Node current =null;
        stack.push(root);
        while(!stack.isEmpty()){
            current = stack.pop();
            //visit(current);
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

    //中序遍历 递归
    public void inOrder(Node root){
        if(root!=null){
            inOrder(root.left);
            visit(root);
            inOrder(root.right);
        }
    }
    //中序遍历  非递归
    public void inOrder2(Node root){
        LinkedList<Node> stack = new LinkedList<Node>();
        Node current = null;
        current = root;
        while(current!=null || !stack.isEmpty()){
            while(current !=null){//// 一直循环到二叉排序树最左端的叶子结点（currentNode是null）
                stack.push(current);
                current =current.left;
            }
            current = stack.pop();
            visit(current);
            current = current.right;
        }
    }

    //后序遍历  递归
    public void afterOrder(Node root){
        if(root != null){
            afterOrder(root.left);
            afterOrder(root.right);
            visit(root);
        }
    }
    //后序遍历  非递归
   public void afterOrder2(Node root){
       LinkedList<Node> stack = new LinkedList<Node>();
       Node current = root;
       Node rightNode = null;   //上一次visit的节点
       while(current!=null || !stack.isEmpty()){
           while(current!=null){// 一直循环到二叉排序树最左端的叶子结点（currentNode是null）
               stack.push(current);
               current = current.left;
           }
           current = stack.pop();
           while(current.right == null || current.right == rightNode){
               visit(current); // 当前结点没有右结点或上一个结点（已经输出的结点）是当前结点的右结点，则输出当前结点
               rightNode = current;
               if(stack.isEmpty()){
                   return;//root以输出，则遍历结束
               }
               current = stack.pop();
           }
           stack.push(current);//还有右结点没有遍历  有些节点会存在出栈又重新入栈的操作
           current  = current.right;
       }
   }
   //广度优先
    public void broad(Node root){
        LinkedList<Node> queue = new LinkedList<Node>();
        Node current = null;
        queue.offer(root);
        while(!queue.isEmpty()){
            current  =queue.poll();
            visit(current);
            if(current.left!=null){
                queue.offer(current.left);
            }
            if(current.right!=null){
                queue.offer(current.right);
            }
        }
    }
    //获得树的最大深度
    public int getDeepth(Node root){
        if(root==null){
            return 0;
        }
        if(root.left == null && root.right==null){
            return 1;
        }
        int nLeft = getDeepth(root.left);
        int nRight = getDeepth(root.right);
        return nLeft>nRight? nLeft+1 :nRight+1;
    }

    //Minimum Depth of Binary Tree
    public int minDepth(Node root) {
        if(root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left + right + 1: Math.min(left,right) + 1;

    }
    //求最大深度VS111求最小深度
    //=========================================
/*
* class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);
        return Math.max(leftMaxDepth,rightMaxDepth)+1;
    }
}
class Solution {
    public int minDepth(TreeNode root) {
        if(root == null)
            return 0;
        //因为题目要求是求根节点到叶子节点的最短高度，那么情况与求最高深度是略有不同的
        //如果不进行下面的判断，那么求出来的深度并不是到叶子节点的
        if(root.left == null)
            return minDepth(root.right) + 1;
        if(root.right == null)
            return minDepth(root.left) + 1;
        //下面与求最大深度是一样的，只是换成Math.min即可
        int leftMinDepth = minDepth(root.left);
        int rightMinDepth = minDepth(root.right);
        return Math.min(leftMinDepth,rightMinDepth)+1;
    }
}
* */
    //===============================


    //===========翻转树
/*
*   1                   1
*  /  \               /    \
*  2   3   ===>    3       2
*     /             \
*     4             4
* */
    public Node invertTree2(Node root) {
        if (root == null) return null;
        Node left = root.left; // 后面的操作会改变 left 指针，因此先保存下来
        root.left = invertTree2(root.right);
        root.right = invertTree2(left);
        return root;
    }

    public void invertTree(Node root){
        if(root == null){ return ;}
        Node tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTree(root.left);
        invertTree(root.right);
    }

    //===========归并两棵树
    /*
    * Input:
       Tree 1                     Tree 2
          1                         2
         / \                       / \
        3   2                     1   3
       /                           \   \
      5                             4   7
Output:
Merged tree:
         3
        / \
       4   5
      / \   \
     5   4   7
    * */
    public Node mergeTrees(Node t1, Node t2) {
        if (t1 == null && t2 == null) return null;
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        Node root = new Node(t1.value + t2.value);
        root.left = mergeTrees(t1.left, t2.left);
        root.right = mergeTrees(t1.right, t2.right);
        return root;
    }

    //===========================================q1
    //这道题是树操作的题目，判断是否从根到叶子的路径和跟给定sum相同的。还是用常规的递归方法来做，递归条件是看左子树或者右子树有没有满足条件的路径，
    // 也就是子树路径和等于当前sum减去当前节点的值。结束条件是如果当前节点是空的，则返回false，如果是叶子，那么如果剩余的sum等于当前叶子的值，
    // 则找到满足条件的路径，返回true。算法的复杂度是输的遍历，时间复杂度是O(n)，空间复杂度是O(logn)。
    //算法的复杂度是输的遍历，时间复杂度是O(n)，空间复杂度是O(logn)。
    public boolean hasPathSum(Node root,int sum){
        if(root==null){
            return false;
        }
        if(root.left==null && root.right==null && root.value == sum){
            return true;
        }
        return hasPathSum(root.left,sum - root.value) || hasPathSum(root.right ,sum - root.value);
    }
    //===========================================q2
    //原题链接: http://oj.leetcode.com/problems/path-sum-ii/
    //这道题是树的题目，跟Path Sum的要求很接近，都是寻找从根到叶子的路径。这道题目的要求是求所有满足条件的路径，
    // 所以我们需要数据结构来维护中间路径结果以及保存满足条件的所有路径。这里的时间复杂度仍然只是一次遍历O(n)，
    // 而空间复杂度则取决于满足条件的路径和的数量（假设是k条），则空间是O(klogn)
    public List<List<Integer>> pathSum_q2(Node root,int sum){
        if(root == null){
            return null;
        }
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        List<Integer> currentResult = new LinkedList<Integer>();
        helper_q2(root, sum, currentResult, result);
        return result;
    }
    private void helper_q2(Node root,int sum,List<Integer> currentResult,List<List<Integer>> result){
        if(root == null){return;}
        currentResult.add(root.value);
        if(root.left==null && root.right==null && root.value==sum){
            result.add(new LinkedList<Integer>(currentResult));
            currentResult.remove(currentResult.size()-1);
            return ;
        }else{
            helper_q2(root.left,sum-root.value,currentResult,result);
            helper_q2(root.right,sum-root.value,currentResult,result);
        }
        currentResult.remove(currentResult.size()-1);
    }
    //=================q5
    /*
    * 4.9 给定一棵二叉树，其中每个结点都含有一个数值。设计一个算法，打印结点数值总和
等于某个给定值的所有路径。注意，路径不一定非得从二叉树的根结点或叶结点开始或结束。（第
54 页）

。对于每
个结点，我们都会向“上”检查是否得到相符的总和。也就是说，我们不再要求“从这个结点开
始是否会有总和为给定值的路径”，而是关注“这个结点是否为总和为给定值的某条路径的末端”。
递归访问每个结点 n 时，我们会将 root 到 n 的完整路径传入该函数。随后，这个函数会以相反
的顺序，从 n 到 root ，将路径上的结点值加起来。当每条子路径的总和等于 sum 时，就打印这条
路径。


如果结点在r层，那么就需要r
份量的工作（向“上”检查结点的步骤）。我们可以猜测时间复杂度为O(n log(n))，因为总共有n
个结点，平均下来，每一步需要log(n)的工作量
算法的空间复杂度为O(log(n))，因为该算法会递归O(log n)次，
而在递归调用中参数 path 只分配一次空间（大小为O(log n)）。
    * */
    private int deepth(Node root){
        if(root==null){
            return 0;
        }
        if(root.left == null && root.right==null){
            return 1;
        }
        return 1 + Math.max( deepth(root.left) ,deepth(root.right) );
    }
    public void print(int[] path,int start,int end){
        for(int i=start;i<=end;i++){
            System.out.print(path[i]+" ");
            System.out.println();
        }
    }
    public void findSum(Node root,int sum){
        int deepth = deepth(root);
        int[] path = new int[deepth];
        findSum(root,sum,path,0);
    }
    private void findSum(Node root,int sum,int[] path,int level){
        if(root == null){ return;}
        /* 将当前结点插入路径 */
        path[level] = root.value;
        /* 查找以此为终点且总和为sum的路径 */
        int t = 0;
        for(int i=level;i>=0;i--){
            t += path[i];
            if(t == sum){
                print(path,i,level);
            }
        }
        /* 查找此结点之下的结点 */
        findSum(root.left,sum,path,level+1);
        findSum(root.right,sum,path,level+1);
        /* 从路径中移除当前结点。严格来说并不一定要这么做，
23 * 直接忽略这个值即可，但这么做是个好习惯 */
        path[level] = Integer.MIN_VALUE;

    }


    //==========================q3
    //Sum Root to Leaf Numbers -- LeetCode
    //原题链接: http://oj.leetcode.com/problems/sum-root-to-leaf-numbers/
    // 这是一道树的题目，一般使用递归来做，主要就是考虑递归条件和结束条件。这道题思路还是比较明确的，目标是把从根到叶子节
    // 点的所有路径得到的整数都累加起来，递归条件即是把当前的sum乘以10并且加上当前节点传入下一函数，进行递归，最终把左右
    // 子树的总和相加。结束条件的话就是如果一个节点是叶子，那么我们应该累加到结果总和中，如果节点到了空节点，则不是叶子节点，
    // 不需要加入到结果中，直接返回0即可。算法的本质是一次先序遍历，所以时间是O(n)，空间是栈大小，O(logn)。
    public int pathSum_q3(Node root){
        return helper_q3(root,0);
    }
    private int helper_q3(Node root,int sum){
        if(root == null){
            return 0;
        }
        if(root.left==null && root.right==null){
            return sum*10 + root.value;
        }
        return helper_q3(root.left,sum*10 + root.value) + helper_q3(root.right,sum*10+root.value);
    }

    //=============q4
    //Binary Tree Maximum Path Sum -- LeetCode
    //原题链接: http://oj.leetcode.com/problems/binary-tree-maximum-path-sum/
    //这道题是求树的路径和的题目，不过和平常不同的是这里的路径不仅可以从根到某一个结点，而且路径可以从左子树某一个结点，
    // 然后到达右子树的结点，就像题目中所说的可以起始和终结于任何结点。在这里树没有被看成有向图，而是被当成无向图来寻找路径。
    // 因为这个路径的灵活性，我们需要对递归返回值进行一些调整，而不是通常的返回要求的结果。
    // 在这里，函数的返回值定义为以自己为根的
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
        if(root == null){
            return 0;
        }
        if(root.left==null && root.right==null){
            return root.value;
        }
        int leftVal = helper_q4(root.left);
        int rightVal = helper_q4(root.right);
        //如果当前局部解（root或left+root或root+right或left+root+right）是最优解，更新最终结果
        int curMax = root.value;
        if(leftVal>0){ curMax += leftVal;}
        if(rightVal>0){ curMax += rightVal;}
        if(curMax > max){ max = curMax;}
        return Math.max(root.value , Math.max( root.value+leftVal , root.value+rightVal )     );
    }

   // =============q5

    public static void main(String[] args){
        Test bst = new Test();
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
        System.out.println(bst.hasPathSum(bst.root,55));
        System.out.println();
        System.out.println("==========q2");
        List<List<Integer>> lists = bst.pathSum_q2(bst.root, 17);
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
