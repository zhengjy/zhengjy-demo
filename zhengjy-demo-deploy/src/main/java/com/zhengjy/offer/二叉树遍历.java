package com.zhengjy.offer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by zhengjy on 2018/4/6 14:35
 *
         5
       /   \
      3     7
     / \   / \
    2  4  6   8
   /
  1
 /
0


 */
public class 二叉树遍历 {
    /*
    1)将根节点5放入栈顶(5)
    2)取出左节点3作为根节点放入栈中(5,3)
    3)取出左节点2作为根节点放入栈中(5,3,2)
    4)取出做节点1作为根节点放入栈中(5,3,2,1)

    5)取出栈中底部1节点且从栈中删除1节点,取出1对应的右节点作为根节点(5,3,2)
    6)取出栈中底部2节点且从栈中删除2节点,取出2对应的右节点作为根节点(5,3)
    7)取出栈中底部3节点且从栈中删除3节点,取出3对应的右节点4作为根节点(5)

    8)将4放入栈中且将对应的左节点作为根节点(5,4)
     */
    //前序、中序遍历非递归的方式
     public static void preOrderNonRecursive(TreeNode<Integer> root){
        Stack<TreeNode<Integer>> stack = new Stack<>();
        while (true){
            while (root != null){
                stack.push(root);
                //5	 3	2	1	0	4	7	6	8
//                System.out.print(root.val+"\t");//前序遍历
                root = root.left;
            }
            if(stack.isEmpty()) break;
            root = stack.pop();
            //0	1	2	3	4	5	6	7	8
            System.out.print(root.val+"\t");//中序遍历
            root = root.right;
        }
     }



    //递归遍历二叉树
      public static void postOrder(TreeNode<Integer>  root){
         if(root!=null){
            //前序遍历
             System.out.print(root.val+"\t");
             postOrder(root.left);
             //中序遍历
//             System.out.print(root.val+"\t");
             postOrder(root.right);
             //后续遍历
//             System.out.print(root.val+"\t");
         }
     }

    /**
     * 后序遍历
     * @param root
     */
    public static void thePostOrderTraversal_Stack(TreeNode root) {   //后序遍历
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> output = new Stack<>();//构造一个中间栈来存储逆后序遍历的结果
        TreeNode node = root;
        while (node != null || stack.size() > 0) {
            if (node != null) {
                output.push(node);
                stack.push(node);
                node = node.right;
            } else {
                node = stack.pop();
                node = node.left;
            }
        }
        System.out.println(output.size());
        while (output.size() > 0) {
            System.out.print(output.pop().val +"\t");
        }
    }

    /**
     * 层序遍历
     * @param root
     * @return
     */
    public static int treeDepth2(TreeNode<Integer> root){
        if(root==null){
            return 0;
        }
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            TreeNode<Integer> cur;
            for(int i=0;i<size;i++){
                cur = queue.poll();
                System.out.print(cur.val +"\t");
                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }
            }
            depth++;
        }
        return depth;
    }


    /**
     * 递归层序遍历
     * @param root
     * @return
     */
    public static int treeDepth(TreeNode<Integer> root){
        if(root==null)
            return 0;
        int left = treeDepth(root.left);
        int right = treeDepth(root.right);
        return left>right?(left+1):(right+1);
    }

    public static void main(String[] args) {
        TreeNode<Integer> root = TreeNode.get();
        //非递归遍历二叉树
        preOrderNonRecursive(root);
        System.out.println();
        //递归遍历二叉树
        postOrder(root);
        System.out.println();
        //非递归层序遍历二叉树，
        treeDepth2(root);
        //递归层序遍历二叉树
        treeDepth(root);
        System.out.println();
        //后续遍历
        thePostOrderTraversal_Stack(root );
    }
}
