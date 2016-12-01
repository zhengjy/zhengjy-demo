package com.zhengjy.test.design.master_worker;

import java.util.Map;
import java.util.Set;

/**
 * 在主函数中，首先通过Master类创建5个Worker工作进程和Worker工作实例PlusWorker。在提交了100个子任务后，便开始子任务的计算。
 * 这些子任务，由生成的5个Worker进程共同完成。Master并不等待所有的Worker执行完成，就开始访问子结果集进行最终结果计算，知道子
 * 结果集中所有的数据都被处理，并且5个活跃的Worker进程全部终止，才给出最终计算结果。
 * @author zhengjy
 * @version 一
 * @date 2016年6月19日 下午2:10:30
 */
public class PlusWorker extends Worker{
	public Object handle(Object input){//Worker,求立方和
		Integer i = (Integer) input;
		return i*i*i;
	}
	
	public static void main(String[] args) {
		Master m = new Master(new PlusWorker(),5);//固定使用5个Worker，并指定Worker
		for(int i=0;i<100;i++){
			m.submit(i);//提交100个子任务
		}
		m.execute();//开始计算
		int re = 0;//最终计算结果保存在此
		Map<String,Object> resultMap = m.getResultMap() ;
		while(resultMap.size() >0  || !m.isComplete()){
			Set<String> keys = resultMap.keySet();//开始计算最终
			String key = null;
			for(String k :keys){
				key =k;
				break;
			}
			Integer i = null;
			if(key != null){
				i= (Integer) resultMap.get(key);
			}
			if(i != null){
				re += i;//最终结果
			}
			if(key != null){
				resultMap.remove(key);//移除已经被计算过的项
			}
			
		}
		
		System.out.println(re);
	}
}



























