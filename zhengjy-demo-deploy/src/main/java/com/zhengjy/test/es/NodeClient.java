//package com.zhengjy.test.es;
//
//import org.elasticsearch.client.Client;
//import org.elasticsearch.node.Node;
//
//import static org.elasticsearch.node.NodeBuilder.nodeBuilder;
//
///**
// * NodeClient是一种嵌入式节点客户端。它首先在客户端启动一个节点（Node），并加入同名集群内。这个节点可以保存数据，并且数据能够被索引。然后从这个节点中获取Client，这类Client就是NodeClient。NodeClient无需指明ES服务端的地址，操作的数据位于启动的节点所在的集群中。
// * 运行这段代码之后，可以看到工程中新增了一个data文件夹，这是因为data(true)将Node设置为可以存放数据的节点，数据正是放在了data文件夹下
// * NodeClient适合用作单元或集成测试，而不适合用于生产环境。
// *
// * Created by zhengjy on 2017/10/6.
// */
//public class NodeClient {
//    public static void main(String[] args) {
//        // 启动一个本地节点，并加入子网内的ES集群
//        Node node = nodeBuilder()
//                .clusterName("elasticsearch") // 要加入的集群名为elasticsearch
//                // .client(true) //如果设置为true，则该节点不会保存数据
//                .data(true) // 本嵌入式节点可以保存数据
//                .node(); // 构建并启动本节点
//
//        // 获得一个Client对象，该对象可以对子网内的“elasticsearch”集群进行相关操作。
//        Client nodeClient = node.client();
//    }
//
//
//}
