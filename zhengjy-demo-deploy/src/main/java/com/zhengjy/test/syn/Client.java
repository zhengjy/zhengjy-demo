package com.zhengjy.test.syn;

/**
 * Created by zhengjy on 2017/3/27.
 */
public class Client {
    Server server;
    public Client(Server s){
        this.server = s;
    }
    public void sendMsg(final String msg) throws InterruptedException {
        System.out.println("客户端正在发消息="+msg);

        new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    server.getMsg(new MyCallback(),msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        System.out.println("消息正在发送，还可以处理其他事情");
        Thread.sleep(5000);
    }
}
class MyCallback implements Callback{

    @Override
    public void process(int status) {
        System.out.println("处理成功返回状态码="+status);
    }
}