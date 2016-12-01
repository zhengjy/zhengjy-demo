package com.zhengjy.test.design.decorator;
/**
 * PacketHTMPHeaderCreator是具体的装饰器，它负责对核心发布的内容进行HTML格式化操作。需要特别注意的是，
 * 它委托了具体组件Component进行核心业务处理。
 * @author zhengjy
 * @version 一
 * @date 2016年6月10日 下午3:48:37
 */
public class PacketHTMLHeaderCreator  extends PacketDecorator{

	public PacketHTMLHeaderCreator(IpacketCreator ipacketCreator) {
		super(ipacketCreator);
	}

	@Override
	public String handleContent() {//对给定数据加上Http头信息
		StringBuffer sb = new StringBuffer();
		sb.append("<html>")
		.append("<body>").
		append(super.component.handleContent())
		.append("</body>").
		append("</html>");
		
		return sb.toString();
	}

}
