package com.zhengjy.test.design.decorator;
/**
 * 对应装饰模式，另一个值得关注的地方是它的使用方法。在本来中，通过层层构造和组装这些装饰者到一个对象中，使其机地结合在一起工作。
 * 
 * 可以看到，通过装饰者的构造函数，将被装饰对象传入。本例中，共生成3个对象实例，作为核心组件的PacketBodyCreator最先被构造，
 * 其次是PachetHTMLHeaderCreator，最后才是PacketHTTPHeaderCreator。
 * 
 * 创建次序：
 * 		PacketBodyCreator
 * 		PachetHTMLHeaderCreator
 * 		PacketHTTPHeaderCreator
 * 
 * @author zhengjy
 * @version 一
 * @date 2016年6月10日 下午4:03:04
 */
public class Test {
	public static void main(String[] args) {
		IpacketCreator pc = new PacketHTTPHeaderCreator(
				new PacketHTMLHeaderCreator(new PacketBodyCreator()));
		System.out.println(pc.handleContent());
	}
}
