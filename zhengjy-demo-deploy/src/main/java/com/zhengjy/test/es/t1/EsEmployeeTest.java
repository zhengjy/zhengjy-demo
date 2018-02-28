package com.zhengjy.test.es.t1;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.junit.Test;

import java.net.InetSocketAddress;

import static org.elasticsearch.index.query.QueryBuilders.*;

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
    @Test
    public void createEmployee(){
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
    @Test
    public void get(){
        GetResponse response = client.prepareGet("megacorp", "employee", "1")
                .setOperationThreaded(false)    // 线程安全
                .get();
        System.out.println("get ="+response.getSourceAsString());
    }

    /**
     * 搜索
     */
    @Test
    public  void search(){
        QueryBuilder qb = termQuery("_type", "log");

        // must、mustNot、should分别代表and、not、or逻辑。
        QueryBuilder qb1 = boolQuery()
                .must(termQuery("type", "typeValue"))
                .mustNot(termsQuery("logrank",""))
                .should(termsQuery("logsource",""));

        SearchResponse response = client.prepareSearch("megacorp")
//                .setTypes("testlog")
//                .setTypes("employee")
//                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(termQuery("last_name", "smith"))                 // Query
                .setPostFilter(QueryBuilders.rangeQuery("age").gt(30))     // Filter，查询大于30岁
//                .setFrom(0).setSize(60).setExplain(true)
                .get();


        System.out.println("search ="+response.toString());
    }


    @Test
    public void  search2(){
        SearchResponse response = client.prepareSearch("megacorp")
                .setTypes("employee")
//                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                //全文属性上搜索,；类似模糊匹配
                .setQuery(matchQuery("about", "rock climbing")
                //找出一个属性中的独立单词
//                .setQuery(matchPhraseQuery("about", "rock climbing")


                )
                .get();
//        System.out.println("search2 ="+response.toString());


        //获取查询结果集
        SearchHits searchHits = response.getHits();
        System.out.println("共搜到:"+searchHits.getTotalHits()+"条结果!");
        //遍历结果
        for(SearchHit hit:searchHits){
            System.out.println("String方式打印文档搜索内容:");
            System.out.println(hit.getSourceAsString());
            System.out.println("Map方式打印高亮内容");
            System.out.println(hit.getHighlightFields());

            System.out.println("遍历高亮集合，打印高亮片段:");
            Text[] text = hit.getHighlightFields().get("about").getFragments();
            for (Text str : text) {
                System.out.println(str.string());
            }
        }

    }
    @Test
    public void  search3(){

        SearchResponse response = client.prepareSearch("megacorp")
                .setTypes("employee")
//                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                //全文属性上搜索,；类似模糊匹配
                //.setQuery(matchQuery("about", "rock climbing")
                //找出一个属性中的独立单词
                .setQuery(matchPhraseQuery("about", "rock climbing")
                )
                .get();
        System.out.println("search2 ="+response.toString());




    }


    /**
     *
     *5.0后需要执行下语句，开启聚合
     curl  -u elastic:changeme -PUT 'http://localhost:9200/megacorp/_mapping/employee' -H 'Content-Type: application/json' -d'
     {
     "properties": {
     "interests": {
     "type":     "text",
     "fielddata": true
     }
     }
     }
     '

     */
    @Test
    public void  search4(){

        SearchResponse sr = client.prepareSearch("megacorp").setTypes("employee")
                .setQuery(QueryBuilders.matchAllQuery())
                .addAggregation(
                        //聚合功能，挖掘出雇员中最受欢迎的兴趣爱好，agg1别名
                        AggregationBuilders.terms("agg1").field("interests")
                                .subAggregation(AggregationBuilders.max("max_age").field("age"))
                )
                .get();

        System.out.println("search2 ="+sr.toString());


        // Get your facet results
        Terms agg1 = sr.getAggregations().get("agg1");
        Histogram agg2 = sr.getAggregations().get("agg1");

    }

    @Test
    public void  searchHighlight(){

        HighlightBuilder hiBuilder=new HighlightBuilder();
        hiBuilder.preTags("<h2>");
        hiBuilder.postTags("</h2>");
        hiBuilder.field("about");

        SearchResponse response = client.prepareSearch("megacorp")
                .setTypes("employee")
//                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                //全文属性上搜索,；类似模糊匹配
//                .setQuery(matchQuery("about", "rock climbing"))
                //找出一个属性中的独立单词
                .highlighter(hiBuilder)
                .setQuery(matchPhraseQuery("about", "rock climbing")
                )
                .get();




        System.out.println("search2 ="+response.toString());
    }
}
