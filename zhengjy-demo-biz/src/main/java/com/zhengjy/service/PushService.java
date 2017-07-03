package com.zhengjy.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by zhengjy on 2017/7/3.
 */
@Service
public class PushService {
    private DeferredResult<String> deferredResult;

    public DeferredResult<String> getAsyncUpdate(){
        deferredResult = new DeferredResult<>(2000L);
        deferredResult.setResult("xxx");

        deferredResult.onTimeout(new Runnable() {
            @Override
            public void run() {
                System.out.println("异步调用执行超时！thread id is : " + Thread.currentThread().getId());
                deferredResult.setResult("异步调用执行超时");
            }
        });



        return deferredResult;
    }

    @Scheduled(fixedDelay = 10000)
    public void refresh(){
        if(deferredResult != null){
            deferredResult.setResult(new Long(System.currentTimeMillis()).toString());
        }
    }

}
