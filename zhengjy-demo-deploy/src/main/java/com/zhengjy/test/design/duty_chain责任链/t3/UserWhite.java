package com.zhengjy.test.design.duty_chain责任链.t3;

public class UserWhite extends Hanndler3{

	@Override
	boolean hanndlerRequest(User u) {
		if("xxx".equals(u.getName())){
			System.out.println("当前用户在白名单，验证通过Y_Y.......");
			return true;
		}else{
			if(super.getHanndler().getHanndler()!=null){
				System.out.println("当前不用户在白名单，验证不通过=_==.......继续下一个验证");
				return super.getHanndler().hanndlerRequest(u);
			}
			System.out.println("UserWhite-----没有后续验证了、、、、、");
			return true;
		}
	}

}
