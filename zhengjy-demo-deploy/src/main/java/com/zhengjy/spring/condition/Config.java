package com.zhengjy.spring.condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * @Conditional(MyCondition.class)
    这句代码可以标注在类上面，表示该类下面的所有@Bean都会启用配置
    也可以标注在方法上面，只是对该方法启用配置
 * Created by zhengjy on 2017/7/13.
 */
@Configuration
@Conditional(MyCondition.class)
public class Config {
    @Bean
    public Serializable createSerializable()
    {
        System.out.println("======000");
        return "";
    }
}
