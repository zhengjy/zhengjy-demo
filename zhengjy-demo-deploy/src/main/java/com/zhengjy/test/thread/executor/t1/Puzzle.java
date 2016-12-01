package com.zhengjy.test.thread.executor.t1;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 参数P和M表示位置类和移动类
 * @author zhengjy
 *
 * @param <P> 位置类
 * @param <M> 移动类
 */
public interface Puzzle<P,M> {
	P initialPosition();
	boolean isGoal(P position);
	Set<M> legalMoves(P position);
	P move(P position, M move);
}

/**
 * node代表通过一系列的移动到达的一个位置，其中保存了到达该位置的移动以及前一个Node。
 * 只要沿着Node链接逐步回溯，就可以重写构建出到达当前位置的移动序列。
 * @author zhengjy
 *
 * @param <P>
 * @param <M>
 */
  class Node<P,M>{
	 P pos;//当前位置
	 M move;//
	 Node<P,M> prev;//上一个节点
	public Node(P pos, M move, Node<P, M> prev) {
		super();
		this.pos = pos;
		this.move = move;
		this.prev = prev;
	}
	List<M> asMoveList(){
		List<M> solution = new LinkedList<M>();
		for(Node<P,M> n = this; n.move != null; n =n.prev){
			solution.add(0,n.move);
		}
		return solution;
	} 
	
}
