package com.zhengjy.fiter;

import com.zhengjy.UserVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/*
*
 * Created by zhengjy on 2017/2/22.
*/


@ControllerAdvice
public class ControllerAdviceTest {

    @ModelAttribute("user")
    public UserVO newUser(UserVO user) {
        user.setName("xxxxx");
        System.out.println("============应用到所有@RequestMapping注解方法，在其执行之前把返回值放入Model");
        return user;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        System.out.println("============应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String processUnauthenticatedException(HttpServletRequest request, HttpServletResponse response, Model model, Exception e) {
        //请求类型,可以区分对待ajax和普通请求
        String requestType = request.getHeader("X-Requested-With");
        if(StringUtils.isNotBlank(requestType)){
            //是ajax
            try {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");

                PrintWriter writer = response.getWriter();
                //具体操作
                writer.write("json...");
                //
                writer.flush();
                writer.close();
                return null;
            } catch (Exception e1) {
            }
        }
        if(e instanceof Exception){
//            logger.error("session超时，跳转到活动首页");
            return "redirect:/mc/index";
        }
//        logger.error("请求发生错误", e);
        System.out.println("===========应用到所有@RequestMapping注解的方法，在其抛出Exception异常时执行");
        return "/error"; //返回一个逻辑视图名
    }

}
