package com.zhengjy.test.design.flyWeight;

import java.util.HashMap;
import java.util.Map;


/**
 * 最为核心的享元工厂类:
 * 		它也是享元模式的精髓所在。它确保同一个公司使用相同的对象产生报表。这是相当有意义的，
 * 否则系统可能会为每个员工生成各自的报表对象，导致系统开销激增。
 * @author zhengjy
 * @version 一
 * @date 2016年6月10日 下午3:21:10
 */
public class ReportMannagerFactory {
	Map<String,IReportManager> financialReport = new HashMap<String,IReportManager>();
	Map<String,IReportManager> employeeReport = new HashMap<String,IReportManager>();
	
	
	IReportManager getFinancialReport(String tenantId){
		IReportManager r = financialReport.get(tenantId);
		if(r == null){
			r = new FinancialReportManager(tenantId);
			financialReport.put(tenantId, r);
		}
		return r;
	}
	IReportManager getEmployeeReport(String tenantId){
		IReportManager r = employeeReport.get(tenantId);
		if(r == null){
			r = new EmployeeReportMannage(tenantId);
			employeeReport.put(tenantId, r);
		}
		return r;
	}
}
