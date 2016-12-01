package com.zhengjy.test.design.duty_chain责任链.t3;

public class IpWhile extends Hanndler3 {

	@Override
	boolean hanndlerRequest(User u) {
		if("127.0.0.1".equals(u.getIp())){
			System.out.println("当前用户Ip在白名单，验证通过Y_Y.......");
			return true;
		}else{
			if(super.getHanndler() !=null){
				System.out.println("当前用户Ip不在白名单，验证不通过=_==.......继续下一验证");
				return super.getHanndler().hanndlerRequest(u);
			}
			System.out.println("IpWhile-----没有后续验证了、、、、、");
			return true;
		}
	}

}
