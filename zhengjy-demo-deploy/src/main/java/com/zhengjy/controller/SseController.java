package com.zhengjy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * Created by zhengjy on 2017/6/30.
 */
@Controller
@RequestMapping("/sse")
public class SseController {
    /**
     * 每5秒向浏览器推送随机消息
     * text/event-stream 使用输出的媒体类型，这是服务端SSE的支持
     * @return
     * @throws InterruptedException
     */
    @RequestMapping(value = "/push",produces = "text/event-stream")
    @ResponseBody
    public String push() throws InterruptedException {
        Random r = new Random();
        Thread.sleep(5000);
        return "data:Testing 1,2,3"+ r.nextInt() + "\n\n";
    }

}
