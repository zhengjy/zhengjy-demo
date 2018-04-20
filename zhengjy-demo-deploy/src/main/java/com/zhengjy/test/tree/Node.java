package com.zhengjy.test.tree;

/**
 * Created by zhengjy on 2018/3/10 16:39
 */
public class Node {
    //节点关键词
    private int data;

    private Node leftNode;

    private Node rightNode;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }
}
