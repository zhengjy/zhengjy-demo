package com.zhengjy.tag;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by zhengjy on 2016/11/30.
 */
public class TagsNamespaceHannder extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("user",new UserBeanDefinitionParser());
    }
}
