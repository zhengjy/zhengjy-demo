package com.zhengjy.test.redis;


import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author huangqingshi
 * @Date 2019-01-17
 */
@Slf4j
//@Aspect
//@Component
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class LimitAspect {

    @Autowired
    DistributedLimit distributedLimit;

    @Pointcut("@annotation(com.zhengjy.test.redis.DistriLimitAnno)")
    public void limit() {
    }

    @Before("limit()")
    public void beforeLimit(JoinPoint joinPoint) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistriLimitAnno distriLimitAnno = method.getAnnotation(DistriLimitAnno.class);
        String key = distriLimitAnno.limitKey();
        int limit = distriLimitAnno.limit();
        Boolean exceededLimit = distributedLimit.distributedLimit(key, String.valueOf(limit));
        if (!exceededLimit) {
            throw new RuntimeException("exceeded limit");
        }
    }
}
