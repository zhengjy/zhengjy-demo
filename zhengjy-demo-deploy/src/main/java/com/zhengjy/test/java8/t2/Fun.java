package com.zhengjy.test.java8.t2;


import com.zhengjy.test.java8.t1.FilteringApples;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Jiyang.Zheng on 2018/9/28 11:10.
 */
public class Fun<T> {
    public static String processFile(BufferedReaderProcessor b) throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("data.txt"))) {
           return b.process(br);
        }

    }

    public static void main(String[] args) {
        try {
            String str = processFile((BufferedReader b) -> b.readLine());
        } catch (IOException e) {
        }

        forEach(Arrays.asList(1,2,54,6),(Integer i) -> System.out.println(i));

        List<Integer> ret = TestFunction.map(Arrays.asList("haha","ha"),(String s) -> s.length());
        ret.forEach(v -> System.out.println(v));
        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<FilteringApples.Apple> apples = TestFunction.map(weights, FilteringApples.Apple::new);

        System.out.println(apples);

        Function<Integer, Integer> f = x -> x + 1;

    }


    public static <T> void  forEach(List<T> list, Consumer<T> c){

        for (T t : list){
            c.accept(t);
        }
    }



    static class TestFunction<T,R>{
        public static <T,R> List<R> map(List<T> list, Function<T,R> fun){
            List<R> ret = new ArrayList<>();
            for (T t : list){
                ret.add(fun.apply(t));
            }
            return ret;
        }
    }
}


@FunctionalInterface
interface BufferedReaderProcessor{
    String process(BufferedReader b) throws IOException;

}



