package com.zhengjy.controller;

import com.zhengjy.test.redis.DistriLimitAnno;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * Created by Jiyang.Zheng on 2019/1/28 9:33.
 */
@RequestMapping("/limit")
public class RedisLimitController {
    @RequestMapping("distributedLimit")
    @ResponseBody
    @DistriLimitAnno(limitKey="limit", limit = 10)
    public String distributedLimit(String userId) {
        return "ok";
    }
}
