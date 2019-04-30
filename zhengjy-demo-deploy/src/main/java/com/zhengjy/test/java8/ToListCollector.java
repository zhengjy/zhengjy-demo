package com.zhengjy.test.java8;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import static java.util.stream.Collector.Characteristics.*;

/**
 * Created by Jiyang.Zheng on 2018/10/12 13:58.
 */
public class ToListCollector<T> implements Collector<T,List<T>,List<T>> {

    @Override
    public Supplier<List<T>> supplier() {//创建集合
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {//累积遍历过的项目，原位修改累加器
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {//修改第一个累加器，将其与第二个累加器的内容合并

        return (list1,list2) -> {
            list1.addAll(list2);
            //返回修改后的第一个累加器
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {//恒等函数
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
    }
}
