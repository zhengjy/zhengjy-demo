package com.zhengjy.controller;

import com.zhengjy.spring.other.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhengjy on 2017/6/30.
 */
@Controller
@RequestMapping("/converter")
public class ConverterController {

    @RequestMapping("/convert")
    @ResponseBody
    public DemoObj convert(@RequestBody DemoObj demoObj){
        System.out.println(demoObj);
        return demoObj;
    }
}
