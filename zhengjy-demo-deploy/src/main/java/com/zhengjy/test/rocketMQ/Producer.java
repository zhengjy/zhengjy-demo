package com.zhengjy.test.rocketMQ;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 顺序消费：
 第一条消息处理完，才能往下处理
 * Created by zhengjy on 2017/4/16.
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        String groupName = "order_producer";
        DefaultMQProducer producer = new DefaultMQProducer(groupName);
        producer.setNamesrvAddr("");//TODO
        producer.start();

//        String[] tags = new String[]{""};
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");

        String dateStr = sdf.format(date);

        for(int i=0; i<5;i++){
            String body = dateStr + "order_1 " + i;
            Message ms = new Message("TopicTest","order_1","KEY"+i,body.getBytes());
            SendResult sendResult = producer.send(ms, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    Integer id = (Integer) o;
                    return list.get(id);
                }
            },1);
            System.out.println(sendResult+", body : "+ body);
        }

        for(int i=0; i<5;i++){
            String body = dateStr + "order_2 " + i;
            Message ms = new Message("TopicTest","order_2","KEY"+i,body.getBytes());
            SendResult sendResult = producer.send(ms, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    Integer id = (Integer) o;
                    return list.get(id);
                }
            },2);
            System.out.println(sendResult+", body : "+ body);
        }

        for(int i=0; i<5;i++){
            String body = dateStr + "order_3 " + i;
            Message ms = new Message("TopicTest","order_3","KEY"+i,body.getBytes());
            SendResult sendResult = producer.send(ms, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    Integer id = (Integer) o;
                    return list.get(id);
                }
            },0);
            System.out.println(sendResult+", body : "+ body);
        }
        producer.shutdown();






    }
}
