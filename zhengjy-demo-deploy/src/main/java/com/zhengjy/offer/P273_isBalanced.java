package com.zhengjy.offer;

import com.zhengjy.offer.tree.TreeNode;

/**
 * Created by zhengjy on 2018/4/8 10:11
 *
 * 平衡二叉树
 */
public class P273_isBalanced {
    //借助于深度，判断是否是平衡二叉树,由于是从根到叶逐点判断，需要多次遍历树
    public static boolean isBalanced(TreeNode<Integer> node){
        if(node==null)
            return true;
        int left = treeDepth(node.left);
        int right = treeDepth(node.right);
        int diff = left - right;
        if(diff<-1||diff>1)
            return false;
        return isBalanced(node.left)&&isBalanced(node.right);
    }


    public static int treeDepth(TreeNode<Integer> root){
        if(root==null)
            return 0;
        int left = treeDepth(root.left);
        int right = treeDepth(root.right);
        return left>right?(left+1):(right+1);
    }
    //用后序遍历，并记录每个节点的深度，从而可以通过一次遍历完成整棵树的判断
    public static boolean isBalanced2(TreeNode<Integer> node){
        if(node==null)
            return true;
        return isBalanced2Core(node,new int[]{0});
    }

    public static boolean isBalanced2Core(TreeNode<Integer> node,int[] depth){
        if(node==null){
            depth[0] = 0;
            return true;
        }
        int[] left = new int[]{0};
        int[] right = new int[]{0};
        boolean flag1 = isBalanced2Core(node.left,left);
        boolean flag2 = isBalanced2Core(node.right,right);
        if(flag1 && flag2){
            int diff = left[0]-right[0];
            if(diff<=1&&diff>=-1){
                depth[0] = 1+(left[0]>right[0]?left[0]:right[0]);
                return true;
            }
            else
                return false;
        }
        return false;
    }


    public static void main(String[] args){
        TreeNode<Integer> root = TreeNode.get();
        System.out.println(isBalanced(root));
        System.out.println(isBalanced2(root));
    }


}
