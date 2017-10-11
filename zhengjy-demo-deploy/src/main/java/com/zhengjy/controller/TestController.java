package com.zhengjy.controller;

import com.zhengjy.spring.configuration.Test1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhengjy on 2016/11/30.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private Test1 ts;


    @RequestMapping("/index2")
    public ModelAndView index(String str, String str2){
        System.out.println(str);
        return  new ModelAndView("/index").addObject("t",ts.getName());
    }

    @RequestMapping("/index3")
    public String index2(String str, String str2){
        int i = 1/2;
        System.out.println(str);
        return  "/index";
    }
}
