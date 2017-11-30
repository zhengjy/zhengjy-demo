package com.zhengjy.spring.spring_mvc_return_val;

/**
 * Created by zhengjy on 2017/11/30 21:27
 */
import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;

public class MyHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    /**
     * @TestWebMvcConfigurerAdapter
     * @param returnType
     * @return
     */
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return returnType.getParameterType() == Person.class;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        Assert.isInstanceOf(Person.class, returnValue);

        //标识请求是否已经在该方法内完成处理
        mavContainer.setRequestHandled(true);

        // 获取方法上的注解
        PersonAnnotation personAnnotation = returnType.getMethodAnnotation(PersonAnnotation.class);

        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        response.setContentType("text/plain;charset=UTF-8");
        Person person = (Person) returnValue;
        response.getWriter().println(personAnnotation.value() + ":" + person);

    }
}
