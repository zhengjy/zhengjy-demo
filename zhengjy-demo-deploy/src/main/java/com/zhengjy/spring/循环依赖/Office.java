package com.zhengjy.spring.循环依赖;

/**
 * Created by zhengjy on 2017/6/11.
 */
public class Office {
    public String name;

    public Boss boss;

    public Office(){

    }
    public Office(String name,Boss boss){
        this.name=name;
        this.boss=boss;
    }
    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
