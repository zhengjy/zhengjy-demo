package com.zhengjy.spring.循环依赖;

/**
 * Created by zhengjy on 2017/6/11.
 */
public class Boss {
    private String name;
    private Office office;

    /*public Boss(){

    }*/
    public Boss(String name,Office office){
        this.name=name;
        this.office=office;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

}
