package com.zhengjy.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * Created by zhengjy on 2017/7/13.
 */

@Configuration
public class ConfigurationTest {

    @Bean
    public Test1 test1(){
        return new Test1("嗷嗷嗷");
    }
}
