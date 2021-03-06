package com.zhengjy.offer;


import com.zhengjy.offer.tree.TreeNode;

/**
 * Created by zhengjy on 2018/4/5 17:04
 *
 * 二叉搜索树的第k大节点
 */
public class P269_KthNodeInBST2 {
    public static TreeNode<Integer> kthNode(TreeNode<Integer> root, int k){
        //栈顶元素保证一直是cur的父节点
        if(root==null || k<0){
            return null;
        }
        TreeNode<Integer> current = root;
        while (true){
            //遍历节点为空
            if(current == null){
                return null;
            }
            //比较值 < 根节点值 则取左节点比较
            if(k < current.val){
                current = current.left;
            }else {
                current = current.right;
            }
            if(current.val == k){
                return current.right;
            }
        }
    }
    public static void main(String[] args){
        TreeNode<Integer> root = new TreeNode<>(5);
        root.left = new TreeNode<>(3);
        root.left.left = new TreeNode<>(2);
        root.left.right = new TreeNode<>(4);
        root.right = new TreeNode<>(7);
        root.right.left = new TreeNode<>(6);
        root.right.right = new TreeNode<>(8);
        System.out.println(kthNode(root,3).val);//4
        System.out.println(kthNode(root,7).val);//7
        System.out.println(kthNode(root,8));//null
    }

}
