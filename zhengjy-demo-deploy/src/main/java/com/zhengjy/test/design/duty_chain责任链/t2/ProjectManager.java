package com.zhengjy.test.design.duty_chain责任链.t2;

public class ProjectManager extends Handler2 {

	@Override
	public String handleRequect(String user, double fee) {
		String str = "";
		//经理权限比较小，控制在500内
		if(fee<500){
			
			//为了测试，简单点，只同意张三的请求  
            if("张三".equals(user))  
            {  
                str = "成功：项目经理同意【" + user + "】的聚餐费用，金额为" + fee + "元";      
            }else  
            {  
                //其他人一律不同意  
                str = "失败：项目经理不同意【" + user + "】的聚餐费用，金额为" + fee + "元";  
            }  
		}else{
			//超过500，继续传递给级别更高的人处理  
            if(getSuccessor() != null)  
            {  
                return getSuccessor().handleRequect(user, fee);  
            }  
		}
		return str;
	}

}
