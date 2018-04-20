package com.zhengjy.test.tree;

/**
 * Created by zhengjy on 2018/3/10 16:43
 */
public class Tree {

    Node root;

    public void insert(int data){
        Node newNode = new Node(data);
        if(root == null){
            root = new Node(data);
        }else {
            Node current = root;
            //层级迭代变量
            Node parent;
            while (true){
                parent = current;
                //要set的节点，小于当前节点
                if(data < current.getData()){
                    current = current.getLeftNode();
                    if(current == null){
                        parent.setLeftNode(newNode);
                        return;
                    }
                }else {
                    current = current.getRightNode();
                    if(current == null){
                        parent.setRightNode(newNode);
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
    }
}
