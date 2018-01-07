//package com.zhengjy.test.es;
//
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.common.transport.TransportAddress;
//import static org.elasticsearch.common.settings.ImmutableSettings.settingsBuilder;
//
///**
// * TransportClient连接远端的ES集群，其本身并不会加入集群
// * TransportClient适合用于生产环境中。
// *
// * Created by zhengjy on 2017/10/6.
// */
//public class TransportClientTest {
//    public static void main(String[] args) {
//        // 配置信息
//        Settings esSetting = settingsBuilder()
//                .put("cluster.name", "elasticsearch")
//                .build();
//        TransportClient transportClient = new TransportClient(esSetting);
//
//        // 添加连接地址
//        TransportAddress address = new InetSocketTransportAddress("118.31.70.81", 9200);
//        TransportAddress address2 = new InetSocketTransportAddress("118.31.43.85", 9200);
//        transportClient.addTransportAddress(address);
//        transportClient.addTransportAddress(address2);
//    }
//}
