package com.zhengjy.spring.other;

import com.zhengjy.spring.spring_mvc_return_val.MyHandlerMethodReturnValueHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by zhengjy on 2017/6/30.
 */
@Component
public class TestWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    /**
     * 静态资源配置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/js/");
        registry.addResourceHandler("/css/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/css/");
        registry.addResourceHandler("/img/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/img/");
    }

    /**
     * 如果页面只有跳转操作，可这种写法
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/test/index").setViewName("/index");
        registry.addViewController("/converter/index").setViewName("/converter_index");
        registry.addViewController("/sse/sse").setViewName("/sse");
        registry.addViewController("/aysnc/deferred").setViewName("/async");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
    }

    @Bean
    public MyMessageConverter converter(){
        return new MyMessageConverter();
    }



    @Bean
    public MyHandlerMethodReturnValueHandler MyHandlerMethodReturnValueHandler(){
        MyHandlerMethodReturnValueHandler formatJsonReturnValueHandler=new MyHandlerMethodReturnValueHandler();
        return formatJsonReturnValueHandler;
    }
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(MyHandlerMethodReturnValueHandler());
    }
}
