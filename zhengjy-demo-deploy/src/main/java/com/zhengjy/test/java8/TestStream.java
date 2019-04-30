package com.zhengjy.test.java8;

import java.util.stream.Stream;

/**
 * Created by Jiyang.Zheng on 2018/9/30 9:59.
 */
public class TestStream {



    public static void main(String[] args) {
        Stream<String> stream = Stream.of("Java 8","Lambdas","Python");
        stream.map(String::toLowerCase).forEach(System.out::println);
    }
}
