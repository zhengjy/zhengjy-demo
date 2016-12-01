package com.zhengjy.test.design.composite;

import java.util.Enumeration;
import java.util.Vector;

public class TreeNode {
	private String name;
	private TreeNode treeNode;
	private Vector<TreeNode> children = new Vector<TreeNode>();
	
	public TreeNode(String name){  
	        this.name = name;  
	}  
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TreeNode getTreeNode() {
		return treeNode;
	}
	public void setTreeNode(TreeNode treeNode) {
		this.treeNode = treeNode;
	}
	
	
	public void add(TreeNode node){
		children.add(node);
	}
	
	public void remove(TreeNode node){
		children.remove(node);
	}
	
	public Enumeration<TreeNode> getChildren(){
		return children.elements();
	}
}
