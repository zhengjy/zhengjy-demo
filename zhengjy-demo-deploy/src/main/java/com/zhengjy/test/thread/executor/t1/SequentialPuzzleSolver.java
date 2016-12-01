package com.zhengjy.test.thread.executor.t1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * SequentialPuzzleSolver中给出了谜题框架的串行解决方法，他在谜题空间中
 * 执行一个深度优先搜索，当找到解决答案后结束搜索。
 * @author zhengjy
 *
 * @param <P>位置类
 * @param <M> 移动类
 */
public class SequentialPuzzleSolver<P,M> {
	private final Puzzle<P, M> puzzle;
	private  Set<P> seen = new HashSet<P>();
	
	public SequentialPuzzleSolver(Puzzle<P, M> puzzle){
		this.puzzle = puzzle;
	}
	
	public List<M> solve(){
		P pos =puzzle.initialPosition();
		return search(new Node<P,M>(pos,null, null));
	}

	private List<M> search(Node<P,M> node) {
		if(!seen.contains(node.pos)){
			seen.add(node.pos);
			if(puzzle.isGoal(node.pos)){
				return node.asMoveList();
			}
			for(M move:puzzle.legalMoves(node.pos)){
				P pos = puzzle.move(node.pos,move);
				Node<P,M> child = new Node<P,M>(pos, move, node);
				List<M> result=  search(child);
				if(result != null){
					return result;
				}
			}
		}
		
		
		return null;
	}
}
