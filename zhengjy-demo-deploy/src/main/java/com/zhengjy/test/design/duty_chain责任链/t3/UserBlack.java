package com.zhengjy.test.design.duty_chain责任链.t3;

public class UserBlack extends Hanndler3{

	@Override
	boolean hanndlerRequest(User u) {
		if("xxx".equals(u.getName())){
			System.out.println("当前用户在黑名单，验证不通过=_==.......");
			return false;
		}else{
			if(super.getHanndler() !=null){
				System.out.println("当前用户不在黑名单，验证通过Y_Y....... 继续下一个验证");
				return super.getHanndler().hanndlerRequest(u);
			}
			System.out.println("UserBlack-----没有后续验证了、、、、、");
			return true;
		}
	}

}
