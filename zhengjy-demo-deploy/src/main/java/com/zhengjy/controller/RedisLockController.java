package com.zhengjy.controller;

import com.zhengjy.test.redis.DistributedLock;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Created by Jiyang.Zheng on 2019/1/28 9:45.
 */
@RestController
@RequestMapping("/lock")
@Log4j2
public class RedisLockController {
    @Autowired
    private DistributedLock lock;

    @RequestMapping("/distributedLock")
    @ResponseBody
    public String distributedLock(String key, String uuid, String secondsToLock, String userId) throws Exception{
//        String uuid = UUID.randomUUID().toString();
        Boolean locked = false;
        try {
            locked = lock.distributedLock(key, uuid, secondsToLock);
            if(locked) {
                log.info("userId:{} is locked - uuid:{}", userId, uuid);
                log.info("do business logic");
                TimeUnit.MICROSECONDS.sleep(3000);
            } else {
                log.info("userId:{} is not locked - uuid:{}", userId, uuid);
            }
        } catch (Exception e) {
            log.error("error", e);
        } finally {
            if(locked) {
                lock.distributedUnlock(key, uuid);
            }
        }

        return "ok";
    }
}
