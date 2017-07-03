package com.zhengjy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhengjy on 2017/6/25.
 */
@Controller
@RequestMapping(value = "/dept")
public class DeptController {

    @RequestMapping("/updateRM")
    @ResponseBody
    public String update(String str,String str2) {
        return "success="+str;
    }

    @RequestMapping("/updateRM2")
    @ResponseBody
    public String update2(String str) {
        return "success="+str;
    }

}
