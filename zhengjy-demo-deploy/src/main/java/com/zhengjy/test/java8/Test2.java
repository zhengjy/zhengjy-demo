package com.zhengjy.test.java8;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * Created by zhengjy on 2017/6/19.
 */
public class Test2 {

    public static void main(String[] args) {
//        new Thread(() -> System.out.println("x")   ).start();
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

       /* System.out.println("Languages which starts with J :");
        System.out.println(languages.stream().filter((str)-> str.startsWith("Jf")).collect(Collectors.toList()));

        List<Integer> values = Arrays.asList(4,5,6,7,8);
        values.stream().filter(i->{
            System.out.println("Hello");
            return false;
        });*/

    }
}
