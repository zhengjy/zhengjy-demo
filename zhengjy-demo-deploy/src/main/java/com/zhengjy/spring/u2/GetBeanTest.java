package com.zhengjy.spring.u2;

/**
 * Created by zhengjy on 2017/6/5.
 */
public abstract class GetBeanTest {
    public void showMe(){
        this.getBean().showMe();
    }
    public abstract User2 getBean();
}
