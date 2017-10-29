package com.zhengjy.test.nio.t2;

/**
 * Created by zhengjy on 2017/10/29.
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8088;
        //建立多路复用器类
        MultiplexerTimeServer multiplexer = new MultiplexerTimeServer(port);
        new Thread(multiplexer,"multiplexer-01").start();

    }
}
