package com.zhengjy;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println("[\\u4E00-\\u9FA5]".replaceAll("\\\\","\\"));
        JSONObject jb = new JSONObject();
        jb.put("a","[\\u4E00-\\u9FA5]");
        System.out.println(jb.toJSONString());
    }


    public static String unicodeToString(String str) {


        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");

        Matcher matcher = pattern.matcher(str);

        char ch;

        while (matcher.find()) {

            ch = (char) Integer.parseInt(matcher.group(2), 16);

            str = str.replace(matcher.group(1), ch+"" );

        }

        return str;

    }
}
