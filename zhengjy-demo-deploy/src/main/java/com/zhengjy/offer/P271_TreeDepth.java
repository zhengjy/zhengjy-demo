package com.zhengjy.offer;

import com.zhengjy.offer.tree.TreeNode;

/**
 * Created by zhengjy on 2018/4/6 9:15
 * 二叉树的深度
 */
public class P271_TreeDepth {
    //递归
    public static int treeDepth(TreeNode<Integer> root){
        if(root==null)
            return 0;
        int left = treeDepth(root.left);
        int right = treeDepth(root.right);
        return left>right?(left+1):(right+1);
    }
    //非递归，广度优先/层序遍历

    public static void main(String[] args){
        TreeNode<Integer> root = TreeNode.get();
        System.out.println(treeDepth(root));
//        System.out.println(treeDepth2(root));
    }
}
