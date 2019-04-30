package com.zhengjy.test.java8.t2;

import java.util.function.Function;

/**
 * Created by Jiyang.Zheng on 2018/9/29 10:21.
 */
public class Fun2 {

    public static void main(String[] args) {
        Function<String,String> addHeader = Letter::addHeader;
        Function<String,String> pipeline = addHeader.andThen(Letter::checkSpelling).andThen(Letter::addFooter);

        String s = pipeline.apply("labda");
        System.out.println(s);
    }

    static class Letter{
        public static String addHeader(String text){
            return "Form Raoul, Header:"+text;
        }

        public static String addFooter(String text){
            return text + " foorler";
        }

        public static String checkSpelling(String text){
            return text.replaceAll("labda","lambda");
        }
    }
}
