package com.zhengjy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengjy on 2017/2/22.
 */
public class UserVO {
    private Long id;
    private String name;
    private Integer age;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("ssss");
        list.add("ssss1");
        list.add("ssss");
    }

}
