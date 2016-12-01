package com.zhengjy.test.design.duty_chain责任链.t2;
/**
 * 责任链模式：
 * 		申请聚餐费用管理：当某人提出聚餐费用申请后，该请求会经过项目、部门、总经理某一个领导做相应的处理，
 * 但是提出申请的人并不知道最终由谁来处理的他请求，一般申请人是把自己的申请提交给项目经理，或者最后是由总经理
 * 来处理他的请求 。申请人只要直接和项目经理交互就可以了，其他的工作是在黑盒中，究竟流程是怎么样的，最后是由谁来
 * 审核的，申请人不需要关系。
 * Author ：zhengjy <br/>
 * Create Time：2016年9月22日 下午3:23:31 <br/>
 */
public class Test2 {
	public static void main(String[] args) {
		//组装责任链
		Handler2 handler = new ProjectManager();//项目经理
		Handler2 handler2 = new DeptManager();//部门经理
		Handler2 handler3 = new GeneralManager();//总经理
		handler.setSuccessor(handler2);
		handler2.setSuccessor(handler3);
		
		String str1  = handler.handleRequect("张三", 300);
		System.out.println("str1："+str1);
		String str2 = handler.handleRequect("麻麻", 400);
		System.out.println("str2："+str2);
		System.out.println("---------------------------------------");  
		
		String test3 = handler.handleRequect("张三", 700);  
        System.out.println("test3 = " + test3);  
        String test4 = handler.handleRequect("李四", 700);  
        System.out.println("test4 = " + test4);  
          
        System.out.println("---------------------------------------");  
        String test5 = handler.handleRequect("张三", 1500);  
        System.out.println("test5 = " + test5);  
        String test6 = handler.handleRequect("李四", 1500);  
        System.out.println("test6 = " + test6);  
				
				
	}
}
