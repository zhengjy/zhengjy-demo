package com.zhengjy.test.java8.t1;

import com.google.common.collect.Lists;
import com.zhengjy.test.java8.t2.Fun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.stream.Collectors.groupingBy;

/**
 * Created by Jiyang.Zheng on 2018/9/27 10:40.
 */
public class FilteringApples {


    public static void main(String[] args) {
        List<Apple> list = Lists.newArrayList();
        Apple a1 = new Apple(20,"red");
        Apple a2 = new Apple(30,"green");
        Apple a3 = new Apple(400,"yellow");
        Apple a4 = new Apple(150,"green");
        list.add(a1);
        list.add(a2);
        list.add(a3);
        list.add(a4);

        List<Apple> l1 = filterApples(list,(Apple a) -> "green".equals(a.getColor()));
        l1.forEach(v -> System.out.println(v));

        List<Apple> l2 = filterApples(list,FilteringApples::isHeaveApple);
        l2.forEach(v -> System.out.println(v));

        filterApples(list, (Apple a) -> "green".equals(a.getColor()) ).forEach(v -> System.out.println(v));


        List<String> l = new ArrayList<>();
        BiPredicate<List<String>, String> contains =List::contains;


    }




    public static boolean isGreenApple(Apple apple){
        return "green".equals(apple.getColor());
    }

    public static boolean isHeaveApple(Apple apple){

        execute(() -> {});

        return apple.weight > 150;
    }

    public static void execute(Runnable r){
        r.run();
    }

    public static List<Apple> filterApples(List<Apple> apples, Predicate<Apple> p){
        List<Apple> ret = Lists.newArrayList();
        for (Apple apple : apples){
            if (p.test(apple)){
                ret.add(apple);
            }
        }
        return ret;
    }


    public static class Apple{
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color){
            this.weight = weight;
            this.color = color;
        }
        public Apple(int weight){
            this.weight = weight;
        }
        public Apple(){
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }
}
