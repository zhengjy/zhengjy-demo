package com.zhengjy.test.es;


import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * Created by zhengjy on 2017/10/6.
 */
public class ESTest {
    public static void main(String[] args) throws IOException {
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "es-cluster")
                .put("xpack.security.user","elastic:changeme")
//                .put("client.transport.sniff", true)
                .build();
        Client client = new TransportClient(settings)

//                .addTransportAddress(new InetSocketTransportAddress("118.31.70.81", 9300))
                .addTransportAddress(new InetSocketTransportAddress("118.31.43.85", 9300));


        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        /*IndexResponse  response = client.prepareIndex("t1", "tweet")
                .setSource(json)
                .execute()
                .actionGet();
        // Index name
        String _index = response.getIndex();*/
        //搜索api
        SearchResponse response = client.prepareSearch("t1","index_name")
//                .setTypes("tweet")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                .setQuery(QueryBuilders.termQuery("user", "123456"))                 // Query
//                .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
                .setFrom(0).setSize(10).setExplain(true)
                .execute()
                .actionGet();


        SearchHits hits = response.getHits();
        System.out.println(hits.getHits().length);
        SearchHit[] hits1 = hits.getHits();
        for(SearchHit hit :hits1){
            Map<String, Object> source = hit.getSource();
            for(Map.Entry<String,Object> filed :source.entrySet()){
                String key = filed.getKey();
                System.out.println("key===="+key+"    value====="+filed.getValue().toString());
            }
        }
    }
}
