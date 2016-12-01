package com.zhengjy.test.design.decorator;
/**
 * PachetHTTPHeaderCreator与PacketHTMLHeaderCreator类似，但是它完成数据包HTTP头部的处理。其余业务出来依然交给内部的component完成。
 * @author zhengjy
 * @version 一
 * @date 2016年6月10日 下午3:58:53
 */
public class PacketHTTPHeaderCreator extends PacketDecorator{

	public PacketHTTPHeaderCreator(IpacketCreator ipacketCreator) {
		super(ipacketCreator);
	}

	@Override
	public String handleContent() {//对给定数据加上HTTP头信息
		StringBuffer sb = new StringBuffer();
		sb.append("Cache-Control:no-cache\n").
		append("Date:Mon,31Dec201204:25:57GNT\n").
		append(component.handleContent());
		return sb.toString();
	}

}
