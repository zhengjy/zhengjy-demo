package com.zhengjy.offer;

/**
 * Created by zhengjy on 2018/4/5 17:07
 */
public class TreeNode<T> {

    public  TreeNode left;

    public  TreeNode right;

    public T val;

    public TreeNode(T val) {
        this.val = val;
    }

    public TreeNode() {
    }

    public static TreeNode get(){
        TreeNode<Integer> root =new TreeNode<>(5);
        root.left = new TreeNode<>(3);
        root.left.left = new TreeNode<>(2);
        root.left.right = new TreeNode<>(4);
        root.left.left.left = new TreeNode<>(1);
        root.left.left.left.left = new TreeNode<>(0);
        root.right = new TreeNode<>(7);
        root.right.right = new TreeNode<>(8);
        root.right.left = new TreeNode<>(6);
        return root;
    }
}
