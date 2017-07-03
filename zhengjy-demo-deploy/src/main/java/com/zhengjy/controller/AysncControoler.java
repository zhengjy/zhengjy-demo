package com.zhengjy.controller;

import com.zhengjy.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by zhengjy on 2017/7/3.
 */
@Controller
@RequestMapping("/aysnc")
public class AysncControoler {
    @Autowired
    private PushService pushService;

    @RequestMapping("/call")
    @ResponseBody
    public DeferredResult<String> deferredCall(){
        return pushService.getAsyncUpdate();
    }


}
