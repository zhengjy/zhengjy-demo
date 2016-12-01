package com.zhengjy.test.design.decorator;
/**
 * PacketDecorator维护核心组件component对象，它负责告知其子类，其核心业务逻辑应该全权委托component完成。自己仅仅是增强处理。
 * @author zhengjy
 * @version 一
 * @date 2016年6月10日 下午3:44:31
 */
public abstract class PacketDecorator implements IpacketCreator {
	IpacketCreator component;

	public PacketDecorator(IpacketCreator ipacketCreator) {
		this.component = ipacketCreator;
	}
	
}
