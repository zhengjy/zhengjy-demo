package com.zhengjy.test.es.t1;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;

import java.net.InetSocketAddress;

/**
 * Created by zhengjy on 2018/1/7.
 */
public class EsEmployeeTest {
    private static Client client;
    static {
        // 通过setting对象指定集群配置信息, 配置的集群名
        Settings settings = Settings.builder().put("cluster.name", "es-cluster") // 设置集群名
                .put("xpack.security.transport.ssl.enabled", false)
                .put("xpack.security.user", "elastic:changeme")
//                .put("client.transport.sniff", true) // 开启嗅探 , 开启后会一直连接不上, 原因未知
//                .put("network.host", "192.168.50.37")
//                .put("node.client", true)
//                .put("client.transport.sniff", false)
//                .put("client.transport.ignore_cluster_name", true) // 忽略集群名字验证, 打开后集群名字不对也能连接上
//                .put("client.transport.nodes_sampler_interval", 5) //报错,
//                .put("client.transport.ping_timeout", 5) // 报错, ping等待时间,
                .build();

        client = new PreBuiltXPackTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("118.31.70.81", 9300)))
                .addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("118.31.43.85", 9300)));
        // 默认5s
        // 多久打开连接, 默认5s
        System.out.println("success connect");
    }


    /**
     * 对于雇员目录，我们将做如下操作：
         每个雇员索引一个文档，包含该雇员的所有信息。
         每个文档都将是 employee 类型 。
         该类型位于 索引 megacorp 内。
         该索引保存在我们的 Elasticsearch 集群中。
     */
    public static void createEmployee(){
        String json = "{\n" +
                "    \"first_name\" :  \"Douglas\",\n" +
                "    \"last_name\" :   \"Fir\",\n" +
                "    \"age\" :         35,\n" +
                "    \"about\":        \"I like to build cabinets\",\n" +
                "    \"interests\":  [ \"forestry\" ]\n" +
                "}";
        client.prepareIndex("megacorp","employee","3").setSource(json).get();
    }

    /**
     * 获取指定文档信息
     */
    private static void get(){
        GetResponse response = client.prepareGet("megacorp", "employee", "1")
                .setOperationThreaded(false)    // 线程安全
                .get();
        System.out.println("get ="+response.getSourceAsString());
    }

    /**
     * 搜索
     */
    private static void search(){

        SearchResponse response = client.prepareSearch("megacorp")
//                .setTypes("testlog")
                .setTypes("employee")
//                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("last_name", "Smith"))                 // Query
//                .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
//                .setFrom(0).setSize(60).setExplain(true)
                .get();


        System.out.println("search ="+response.toString());
    }
    public static void main(String[] args) {
        createEmployee();
        get();
        search();
    }
}
