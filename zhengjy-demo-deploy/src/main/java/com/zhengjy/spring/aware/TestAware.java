package com.zhengjy.spring.aware;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * Created by zhengjy on 2017/6/30.
 */
@Component
public class TestAware implements BeanNameAware,ResourceLoaderAware {
    @Override
    public void setBeanName(String name) {
        System.out.println("name="+name);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("ResourceLoader="+resourceLoader);
    }
}
