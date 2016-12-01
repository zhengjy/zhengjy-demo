package com.zhengjy.tag;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhengjy on 2016/11/30.
 */
public class Test {
    /*
     *Spring 容器需要解析xml的标签，并把xml里bean的定义转化为内部的结构 BeanDifition,
     *
     * Spring自定义标签执行过程：
     *      1）将xml文件解析成dom树，将xml解析成树的时候，需要xml标签定义schema来验证语法的结构。Spring 约定将所有的schema的虚拟路径
     * 和真实文件路径映射定义在classpath的在META-INF/spring.schemas下面。在容器启动时spring会扫描所有的META-INF/spring.schemas并将
     * 映射维护在一个map里面。
     *      2）将dom树解析成BeanDefinition。将定义bean标签和xml定义解析成BeanDefinition的过程。
     *
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring-tag-test.xml");
        User  user = (User) ac.getBean("user");
        System.out.println(user.getEmail());
    }
}
