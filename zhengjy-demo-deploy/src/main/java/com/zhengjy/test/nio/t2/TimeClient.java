package com.zhengjy.test.nio.t2;

import java.io.IOException;

/**
 * Created by zhengjy on 2017/10/29.
 */
public class TimeClient {
    public static void main(String[] args) throws IOException {
        new Thread(new TimeClienthandle("127.0.0.1",8088),"timeClient-01").start();
    }
}
