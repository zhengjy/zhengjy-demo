package com.zhengjy.test.design.gs;

import com.zhengjy.test.design.futrue.FutureData;

public class ServerThread extends Thread {
    private RequestQueue requestQueue;//请求队列
    public ServerThread(RequestQueue requestQueue, String name) {
        super(name);
        this.requestQueue = requestQueue;
    }
    public void run() {
        // 线程一直存在，直到进程结束. 守护线程。
        while (true) {
            final Request request = requestQueue.getRequest();//得到请求
            final FutureData future = (FutureData) request.getResponse();
            //realData的创建比较耗时
            com.zhengjy.test.design.futrue.RealData realdata = new com.zhengjy.test.design.futrue.RealData(request.getName());
            //处理完成后，通知客户进程
            future.setRealData(realdata);
            
            try {
                Thread.sleep(100);//模拟请求处理耗时
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " handles  " + request);            
        }
    }
}
