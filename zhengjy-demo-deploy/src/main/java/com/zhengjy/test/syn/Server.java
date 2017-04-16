package com.zhengjy.test.syn;

/**
 * Created by zhengjy on 2017/3/27.
 */
public class Server {
    public void getMsg(Callback callback, String msg) throws InterruptedException {
        System.out.println("服务端获取消息="+msg);
        Thread.sleep(2000);
        System.out.println("服务端处理成功");
        callback.process(200);
    }
}
