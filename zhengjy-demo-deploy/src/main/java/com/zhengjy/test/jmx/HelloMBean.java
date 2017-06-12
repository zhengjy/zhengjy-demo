package com.zhengjy.test.jmx;

/**
 * Created by zhengjy on 2017/5/23.
 */
public interface HelloMBean {
    public String getName();

    public void setName(String name);

    public String getAge();

    public void setAge(String age);

    public void helloWorld();

    public void helloWorld(String str);

    public void getTelephone();
}
