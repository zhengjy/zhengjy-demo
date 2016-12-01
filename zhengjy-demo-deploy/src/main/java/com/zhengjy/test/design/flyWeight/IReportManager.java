package com.zhengjy.test.design.flyWeight;
/**
 *  享元对象接口的实现，他用于创建一个报表。即，所有的报表生成类将作为享元对象在一个公司中共享。
 * @author zhengjy
 * @version 一
 * @date 2016年6月10日 下午3:14:22
 */
public interface IReportManager {
	public String createReport();
}
