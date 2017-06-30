package com.zhengjy.controller;

import com.zhengjy.spring.User2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhengjy on 2016/11/30.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/index2")
    public ModelAndView index(String str,String str2){
        Test t = new Test();
        t.setId(1111);
        return  new ModelAndView("/index").addObject("t",t);
    }
}
