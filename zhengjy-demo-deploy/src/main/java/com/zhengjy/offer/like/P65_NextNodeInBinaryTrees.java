package com.zhengjy.offer.like;

/**
 * Created by zhengjy on 2018/4/18 14:57
 */
public class P65_NextNodeInBinaryTrees {
    public static TreeNode getNext(TreeNode pNode){
        if(pNode==null)
            return null;
        else if(pNode.right!=null){
            pNode = pNode.right;
            while(pNode.left!=null)
                pNode = pNode.left;
            return pNode;
        }
        while(pNode.father!=null){
            if(pNode.father.left==pNode)
                return pNode.father;
            pNode = pNode.father;
        }
        return null;
    }
    public static void main(String[] args){
        //            1
        //          // \\
        //         2     3
        //       // \\
        //      4     5
        //    inorder->42513
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.father = root;
        root.right = new TreeNode(3);
        root.right.father = root;
        root.left.left = new TreeNode(4);
        root.left.left.father = root.left;
        root.left.right = new TreeNode(5);
        root.left.right.father = root.left;

        System.out.println(getNext(root.left.left).val);
        System.out.println(getNext(root.left).val);
        System.out.println(getNext(root.left.right).val);
        System.out.println(getNext(root).val);
        System.out.println(getNext(root.right));
    }

}
//带有父指针的二叉树节点
class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode father;
    public TreeNode(int val){
        this.val = val;
        this.left = null;
        this.right = null;
        this.father = null;
    }
}
