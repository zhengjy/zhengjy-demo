package com.zhengjy.spring.spring_mvc_return_val;

/**
 * Created by zhengjy on 2017/11/30 21:27
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoController {
    @PersonAnnotation("person")
    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public Person getPerson() {
        return new Person("xiya", 25);
    }
}
