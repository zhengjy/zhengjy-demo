package com.zhengjy.test.design.decorator;
/**
 * 
 * PacketBodyCreator是具体业务的组件，它的功能是构造要发布信息的核心内容，但是它不负责将其构造成一个格式工整、可直接发布的数据格式。
 * @author zhengjy
 * @version 一
 * @date 2016年6月10日 下午3:42:55
 */
public class PacketBodyCreator implements IpacketCreator {

	@Override
	public String handleContent() {
		return "Content of Packet";//构造核心数据，但不包括格式
	}

}
