package com.zhengjy.test.design.gs;

import com.zhengjy.test.design.futrue.FutureData;

import java.util.ArrayList;
import java.util.List;

public class ClientThread extends Thread {
    private RequestQueue requestQueue;//请求队列
    private List<Request> myRequest = new ArrayList<Request>();
    public ClientThread(RequestQueue requestQueue, String name) {
        super(name);
        this.requestQueue = requestQueue;
    }
    public void run() {
        //先提出请求
        for (int i = 0; i < 10; i++) {
            Request request = new Request("RequestID:" + i+" Thread_Name:"+Thread.currentThread().getName());//构造请求
            System.out.println(Thread.currentThread().getName() + " requests " + request);
            
            //设置一个FutureData的返回值
            request.setResponse(new FutureData());
            requestQueue.putRequest(request);//提交请求
            myRequest.add(request);
            //这里可以做一些额外的业务处理，等待服务端装配数据
            try {
                Thread.sleep(10);//客户端请求的速度
                //快于服务端处理的速度
            } catch (InterruptedException e) {
            }
            
        }
        
        for(Request r:myRequest){
        	System.out.println("ClientThread Name is:"+Thread.currentThread().getName() +" Reponse is"+ 
        			r.getResponse().getResult());//如果服务端还没处理完成，这里会等待
        }
        
//        System.out.println(Thread.currentThread().getName()+" request end");
    }
}
