/*
package com.zhengjy.fiter;

import com.zhengjy.UserVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

*/
/**
 * Created by zhengjy on 2017/2/22.
 *//*

@ControllerAdvice
public class ControllerAdviceTest {

    @ModelAttribute
    public UserVO newUser(UserVO user) {
        System.out.println("============应用到所有@RequestMapping注解方法，在其执行之前把返回值放入Model");
        return user;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        System.out.println("============应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String processUnauthenticatedException(NativeWebRequest request, Exception e) {
        System.out.println("===========应用到所有@RequestMapping注解的方法，在其抛出Exception异常时执行");
        return "/error"; //返回一个逻辑视图名
    }

}
*/
