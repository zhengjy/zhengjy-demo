package com.zhengjy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhengjy on 2017/6/25.
 */
@Controller
@RequestMapping(value = "/wildcard")
public class TestWildcardController {

    @RequestMapping("/test/**")
    @ResponseBody
    public ModelAndView test1(ModelAndView view) {
        view.setViewName("/test/test");
        view.addObject("attr", "TestWildcardController -> /test/**");
        return view;
    }


    @RequestMapping("test?")
    @ResponseBody
    public ModelAndView test3(ModelAndView view) {
        view.setViewName("/test/test");
        view.addObject("attr", "TestWildcardController -> test?");
        return view;
    }

    @RequestMapping("test/*")
    @ResponseBody
    public ModelAndView test4(ModelAndView view) {
        view.setViewName("/test/test");
        view.addObject("attr", "TestWildcardController -> test/*");
        return view;
    }
}
