package com.zhengjy.test.activeMQ;


/**
 * Created by zhengjy on 2017/4/4.
 */
public class Sender {
   /* public static void main(String[] args) throws Exception{
        //1.建立cf工厂对象，需要填入用户名密码，已经连接的地址
        ConnectionFactory cf = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://localhost:61616"
        );
        //2.从工厂对象创建一个连接，调用start方法开启连接，因为connection默认关闭
        Connection connection = cf.createConnection();
        connection.start();
        //3.通过Connection对象创建Session会话用于接收消息
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        //4.通过Session场景Destinnation对象，指的是一个客户端用来指定生产消息目标和消费信心来源的对象
        Destination destination = session.createQueue("queue1");
        //5.通过Session对象创建消息的发生和接收对象(生产者和消费者)
        MessageConsumer messageConsumer = session.createConsumer(destination);

        while (true){
            TextMessage msg = (TextMessage) messageConsumer.receive();
            if(msg == null){
                break;
            }
            System.out.println("收到的内容:"+ msg.getText());
        }
        if(connection != null){
            connection.close();
        }
    }*/
}
