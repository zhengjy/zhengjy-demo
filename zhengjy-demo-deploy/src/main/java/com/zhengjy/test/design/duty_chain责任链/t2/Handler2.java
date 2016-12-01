package com.zhengjy.test.design.duty_chain责任链.t2;

public abstract class Handler2 {
	protected Handler2 successor;

	public Handler2 getSuccessor() {
		return successor;
	}

	public void setSuccessor(Handler2 successor) {
		this.successor = successor;
	}
	
	/**
	 * 处理聚餐费用的申请
	 * @author zhengjy
	 * @param user
	 * @param fee
	 * @return
	 */
	public abstract String  handleRequect(String user,double fee);
	
	
}
