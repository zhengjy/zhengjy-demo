package com.zhengjy.test.design.flyWeight;
/**
 * ReportManagerFatory作为享元工厂，以公司的ID为索引，维护了一个享元对象的集合，它确保相同公司的请求都返回同一个享元实例，
 * 确保享元对象的有效复用。
 * @author zhengjy
 * @version 一
 * @date 2016年6月10日 下午3:31:37
 */
public class Test {
	public static void main(String[] args) {
		
		ReportMannagerFactory rmf= new ReportMannagerFactory();
		IReportManager r =  rmf.getFinancialReport("A");
		System.out.println(r.createReport());
	}
}
