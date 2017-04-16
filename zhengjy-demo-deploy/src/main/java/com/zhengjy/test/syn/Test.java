package com.zhengjy.test.syn;

/**
 * Created by zhengjy on 2017/3/27.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        Server server = new Server();
        Client client = new Client(server);
        client.sendMsg("xxxx");
    }
}
