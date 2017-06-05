package com.zhengjy.test.rocketMQ;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListener;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.apache.activemq.memory.list.MessageList;

import java.util.List;
import java.util.Random;

/**
 * Created by zhengjy on 2017/4/20.
 */
public class Consumer {

    public Consumer() throws MQClientException {
        String groupName = "order_consumer";
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr("");
        //设置Consumer第一次启动从队列头部开始消费还是队列尾部消费，如果非第一次启动那么按照上次消费的位置消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //订阅主题
        consumer.subscribe("TopicTest","*");
        //注册监听
        consumer.registerMessageListener(new Listener());
        consumer.start();
        System.out.println("c1 start");
    }

    class Listener implements MessageListenerOrderly{

        @Override
        public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext context) {
            //设置自动提交
            context.setAutoCommit(true);
            for(MessageExt m : list){
                System.out.println(m +",context:" + new String(m.getBody()));
            }
            //模拟业务逻辑处理
            try {
                Thread.sleep(new Random().nextInt(1));
            } catch (Exception e) {
                e.printStackTrace();
            }

            return ConsumeOrderlyStatus.SUCCESS;
        }
    }

    public static void main(String[] args) throws MQClientException {
        Consumer c = new Consumer();
    }
}
