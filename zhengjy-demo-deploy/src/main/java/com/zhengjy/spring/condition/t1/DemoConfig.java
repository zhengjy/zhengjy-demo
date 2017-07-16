package com.zhengjy.spring.condition.t1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhengjy on 2017/7/15.
 */
@Configuration
public class DemoConfig {
    @Bean
    @Conditional(WindowsCondition.class)
    public CommandService commandService() {
        return new WindowsCommnadService();
    }

    @Bean
    @Conditional(LinuxCondition.class)
    public CommandService linuxEmailerService() {
        return new LinuxCommandService();
    }

}
