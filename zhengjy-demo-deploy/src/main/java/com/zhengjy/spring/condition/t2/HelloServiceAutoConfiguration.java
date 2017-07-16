package com.zhengjy.spring.condition.t2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhengjy on 2017/7/16.
 */

@Configuration
//开启属性注入，通过@EnableConfigurationProperties声明，使用@Autowired注入
@EnableConfigurationProperties(HelloServiceProperties.class)
@ConditionalOnClass(HelloService.class)
//如果没有enabled这个属性，则默认设置true
@ConditionalOnProperty(prefix = "hello",value = "enalbled",matchIfMissing = true)
public class HelloServiceAutoConfiguration {
    @Autowired
    private HelloServiceProperties helloServiceProperties;

    public HelloService helloService(){
        HelloService helloService = new HelloService();
        helloService.setMsg(helloServiceProperties.getMsg());
        return helloService;
    }

}
