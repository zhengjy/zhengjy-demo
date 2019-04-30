package com.zhengjy.test.qps;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jiyang.Zheng on 2019/1/17 14:19.
 */
public class RateLimiterTest {

    public static void main(String[] args) {
        RateLimiter limiter = RateLimiter.create(1);

        for(int i = 1; i < 10; i = i + 2 ) {
            double waitTime = limiter.acquire(i);
            System.out.println("cutTime=" + System.currentTimeMillis() + " acq:" + i + " waitTime:" + waitTime);
        }


    }
}
