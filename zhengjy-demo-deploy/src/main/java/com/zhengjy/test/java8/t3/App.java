package com.zhengjy.test.java8.t3;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * Created by Jiyang.Zheng on 2018/9/29 11:19.
 */
public class App {

    private static List<Dish> menu = null;
    public static void main(String[] args) {

        List<String> threeNames = menu.stream().filter(v -> v.getCalories() > 300).map(Dish::getName).limit(3).collect(Collectors.toList());
//        List<Integer> threeNames = menu.stream().filter(v -> v.getCalories() > 300).map(Dish::getName).map(String::length).collect(Collectors.toList());
        System.out.println(threeNames);

        if(menu.stream().anyMatch(v -> v.getName().equals("pork"))){
            System.out.println("======pizza");
        };

        List<String> l = Arrays.asList("aaaa","abddd","sddd");

        List<Integer> it = l.stream().map(String::length).collect(Collectors.toList());
        System.out.println(it);

        it.stream().reduce(0,(a,b) -> a+b);
        it.stream().reduce(0,Integer::sum);

        l.stream().map(String::length).reduce(Integer::sum);

        List<String> sl = l.stream().map(w -> w.split("")).flatMap(Arrays::stream).collect(Collectors.toList());

        IntSummaryStatistics sum = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));

        System.out.println(sum);

        String join = menu.stream().map(Dish::getName).collect(Collectors.joining(","));
        System.out.println(join);

        group();


    }

    private void reducing(){
        List<String> list = Arrays.asList("a","b","v");
//        list.stream().collect(reducing("",String::,(i,j) -> i+j));
    }


    public static void group(){
        Map<Dish.Type, Long> typesCount = menu.stream().collect(
                groupingBy(Dish::getType, counting()));

        System.out.println(typesCount);

        Map<Dish.Type, Dish> mostCaloricByType =
                menu.stream().collect(groupingBy(Dish::getType,collectingAndThen(maxBy(comparingInt(Dish::getCalories)),Optional::get)));

        System.out.println(mostCaloricByType);


        Map<Dish.Type, Set<String>> caloricLevelsByType =menu.stream().collect(groupingBy(Dish::getType,mapping(dish ->{
            if (dish.getCalories() <= 400){
                return "a";
            } else if (dish.getCalories() <= 700) {
                return "b";
            }else{
                return "c";
            }

        },toSet())));

        System.out.println(caloricLevelsByType);
    }

    static {
        menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );
    }
}
