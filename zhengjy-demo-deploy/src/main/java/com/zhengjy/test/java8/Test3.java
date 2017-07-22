package com.zhengjy.test.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by zhengjy on 2017/7/11.
 */
public class Test3 {
    public static void main(String[] args) {
        // 1. Individual values 单独值
        Stream stream = Stream.of("a1", "b1", "c1");
        stream.forEach(System.out::print);//打印 a1b1c1

        // 2. Arrays 数组
        String[] strArray = new String[] {"a2", "b2", "c2"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        System.out.println(stream.collect(Collectors.joining(",")).toString());//打印 a2,b2,c2

        // 3. Collections 集合
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();

        System.out.println("-----------------------------------------");


        Stream<String> stream2 = Stream.of("hello", "world", "java8", "stream");
        List<String> wordList = stream2.map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(wordList.toString());// [HELLO, WORLD, JAVA8, STREAM]

        stream2 = Stream.of("hello", "world", "java8", "stream");
        wordList = stream2.map(s -> { return s.toUpperCase(); }).collect(Collectors.toList());
        System.out.println(wordList.toString());// [HELLO, WORLD, JAVA8, STREAM]

        stream2 = Stream.of("hello", "world", "java8", "stream");
        wordList = stream2.map(s -> s.toUpperCase()).collect(Collectors.toList());
        System.out.println(wordList.toString());// [HELLO, WORLD, JAVA8, STREAM]

        System.out.println("-----------------------------------------");


        Stream<List<Integer>> inputStream =
                Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
        Stream<Integer> outputStream = inputStream.flatMap(childList -> childList.stream());
        System.out.print(outputStream.collect(Collectors.toList()).toString());// [1, 2, 3, 4, 5, 6]

        System.out.println("-----------------------------------------");


        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Integer[] evens = Stream.of(sixNums).filter(n -> n % 2 == 0).toArray(Integer[]::new);
        System.out.println(Arrays.toString(evens));// [2, 4, 6]



        System.out.println("-----------------------------------------");

        Stream.of("one", "two", "three", "four")
                .filter(p -> p.length() > 3)
                .peek(v -> System.out.println("Filtered Value:" + v))
                .map(String::toUpperCase)
                .peek(v -> System.out.println("Mapped Value:" + v))
                .collect(Collectors.toList());
        // 1. Filtered Value:three
        // 2. Mapped Value:THREE
        // 3. Filtered Value:four
        // 4. Mapped Value:FOUR



    }

}
