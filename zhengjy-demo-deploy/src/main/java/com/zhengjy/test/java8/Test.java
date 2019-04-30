
package com.zhengjy.test.java8;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Test {

    public static void main(String[] args) {
        String ss = "Hello";

        String[] aa = ss.split("");

        String[] bb = {"H", "e", "l", "l", "o"};


        String[] strings = {"Hello", "World"};

        //Arrays.stream接收一个数组返回一个流
        List<Stream<String>> streamList = Arrays.asList(strings).stream().
                map(str -> str.split("")).
                map(str -> Arrays.stream(str)).
                collect(Collectors.toList());

        Arrays.asList(strings).stream().map(str -> str.split("")).flatMap(str -> Arrays.stream(str)).
                collect(Collectors.toList());

    }


    public int readDuration(Properties props, String name) {
        String value = props.getProperty(name);
        if (value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0) {
                    return i;
                }
            } catch (NumberFormatException nfe) { }
        }
        return 0;
    }
    public int readDuration2(Properties props, String name) {
        int i = Optional.ofNullable(props.getProperty(name)).map(Test::stringToInt).filter(v -> v >0 ).orElse(0);
        int i2 = Optional.ofNullable(props.getProperty(name)).flatMap(Test::stringToInt2).filter(v -> v >0 ).orElse(0);
        return 0;
    }


    private static int stringToInt(String value){
        return Optional.ofNullable(value).map(v -> Integer.parseInt(value)).orElse(0);

    }
    private static Optional<Integer> stringToInt2(String value){
        return Optional.ofNullable(Integer.parseInt(value));

    }
}
