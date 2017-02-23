package com.zhengjy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhengjy on 2016/11/30.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/index")
    public ModelAndView index(){
        return  new ModelAndView("/index");
    }
}
