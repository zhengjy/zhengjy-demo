package com.zhengjy.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by zhengjy on 2017/4/24.
 */
@Configuration
@ComponentScan("com.zhengjy.spring")
@EnableScheduling
public class Config {
}
