package com.zhengjy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@ImportResource("classpath*:spring/spring-*.xml")
//@ConfigurationProperties(locations = {"classpath:config/test.properties"},prefix = "test.")

public class Application {//extends SpringBootServletInitializer

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(Application.class);
//    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        SpringApplication.run(Application.class, args);
        long period = System.currentTimeMillis() - start;
        LOGGER.error(" start successfully in "+period+" ms.");
    }
}
