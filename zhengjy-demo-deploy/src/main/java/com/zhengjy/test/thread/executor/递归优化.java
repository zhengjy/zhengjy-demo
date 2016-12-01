package com.zhengjy.test.thread.executor;

import java.util.*;
import java.util.concurrent.*;


public class 递归优化 {
	public<T> Collection<T> getParallelResults(List<Node2<T>> nodes) throws InterruptedException{
		ExecutorService executor = Executors.newCachedThreadPool();
		Queue<T> resultQueue = new ConcurrentLinkedQueue<T>();
		parallelRecursive(executor, nodes, resultQueue);
//		sequentialRecursive(nodes, resultQueue);
		executor.shutdown();
		executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
		return resultQueue;
	}
	
	/**
	 * 用深度优先算法遍历一个树，在每个节点上执行计算并将结果放入一个集合。
	 * @param nodes
	 * @param result
	 */
	public<T> void sequentialRecursive(List<Node2<T>> nodes, Collection<T> result){
		if(nodes == null ){
			return ;
		}
		for(Node2<T> n : nodes){
			result.add(n.getObj());
			sequentialRecursive(n.getChild(), result);
			System.out.println(n);
		}
	}
	
	/**
	 * 执行深度优先遍历，但并不是在访问节点时进行计算，而是为每个节点提交一个任务来完成计算。
	 * 当parallelRecursive返回时，书中的各个节点都一个访问过了，并且每个节点的计算任务也已经放入Executor的工作队列。
	 * @param executor
	 * @param nodes
	 * @param results
	 */
	public<T> void parallelRecursive(final Executor executor , List<Node2<T>> nodes, final Collection<T> results){
		if(nodes == null ){
			return ;
		}
		for(final Node2<T> n : nodes){
			results.add(n.getObj());
			executor.execute(new Runnable() {
				@Override
				public void run() {
					results.add(n.getObj());
				}
			});
			parallelRecursive(executor, n.getChild(), results);
			System.out.println(n);
		}
	}
	public static void main(String[] args) throws Exception {
		List<Node2<Node2>> list = new ArrayList<Node2<Node2>>();
		Node2<Node2> node = new Node2<Node2>();
		node.setId(1);
		node.setName("x1");
		node.setObj(node);
		list.add(node);
		
		List<Node2<Node2>> list1 = new LinkedList<Node2<Node2>>();
		Node2 node2 = new Node2();
		node2.setId(22);
		node2.setName("x1.1");
		node2.setObj(node2);
		
		Node2 node3 = new Node2();
		node3.setId(33);
		node3.setName("x1.2");
		node3.setObj(node3);
		
		list1.add(node2);
		list1.add(node3);
		
		node.setChild(list1);
		
		List<Node2<Node2>> list2 = new LinkedList<Node2<Node2>>();
		Node2 node4 = new Node2();
		node4.setId(44);
		node4.setName("x1.1.1");
		node4.setObj(node4);
		
		Node2 node5 = new Node2();
		node5.setId(55);
		node5.setName("x1.1.2");
		node5.setObj(node5);
		
		list2.add(node4);
		list2.add(node5);
		
		node2.setChild(list2);
		递归优化 dd = new 递归优化();
		Collection<Node2> result = dd.getParallelResults(list);
		System.out.println(result);
	}
}

class Node2<T>{
	private T obj;
	private String name;
	private Integer id;
	private List<Node2<T>> child;
	public List<Node2<T>> getChild(){
		return child;
	}
	public void setChild(List<Node2<T>> child) {
		this.child = child;
	}


	
	@Override
	public String toString() {
		return "Node [name=" + name + ", id=" + id + "]";
	}
	public T getObj() {
		return obj;
	}


	public void setObj(T obj) {
		this.obj = obj;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}