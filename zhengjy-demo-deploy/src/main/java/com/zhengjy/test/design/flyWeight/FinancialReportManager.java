package com.zhengjy.test.design.flyWeight;
/**
 * 以下是两个报表生成的实例，分别对应员工财务收入报表和员工个人信息报表。他们都具有享元类。
 * @author zhengjy
 * @version 一
 * @date 2016年6月10日 下午3:15:55
 */
public class FinancialReportManager implements IReportManager {//财务报表
	protected String tenantId = null;//租户id
	
	public FinancialReportManager(String tenantId) {
		this.tenantId = tenantId;
	}

	@Override
	public String createReport() {
		return "This is a financial report";
	}

}

class EmployeeReportMannage implements IReportManager {//员工报表
	protected String tenantId = null;//租户id
	
	public EmployeeReportMannage(String tenantId) {
		this.tenantId = tenantId;
	}
	@Override
	public String createReport() {
		return "This is a Employee report";
	}
	
	
}