package com.zhengjy.test.java8.parallel;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by Jiyang.Zheng on 2018/10/13 12:41.
 */
public class ParallelStreamTest {



    public static long measureSumPerf(Function<Long,Long> adder, long n){
        long fastest =Integer.MAX_VALUE;
        for (int i=0;i<10;i++){
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
//            System.out.println("Result: " + sum);
            if (duration < fastest){
                fastest = duration;
            }
        }
        return fastest;
    }

    public static void main(String[] args) {
        System.out.println("Sequential sum done in:" +
                measureSumPerf(ParallelStreamTest::sequentialSum, 10_000_000) + " msecs");

        System.out.println("Iterative sum done in:" +
                measureSumPerf(ParallelStreamTest::iterativeSum, 10_000_000) + " msecs");

        System.out.println("Parallel sum done in: " +
                measureSumPerf(ParallelStreamTest::parallelSum, 10_000_000) + " msecs" );

        System.out.println("Ranged sum done in: " +
                measureSumPerf(ParallelStreamTest::rangedSum, 10_000_000) + " msecs" );

        System.out.println("parallelRanged sum done in: " +
                measureSumPerf(ParallelStreamTest::prallelRangedSum, 10_000_000) + " msecs" );
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    //1、iterate生成的是装箱的对象，必须拆箱成数字才能求和；，效率慢
    //2、iterate很难分割成独立执行的小块，因为每次应用这个函数都要依赖前一次应用的结果
    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    //不需要对
    //原始类型做任何装箱或拆箱操作
    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }

    //数值流比前面那个用iterate工厂方法生成数字的顺序执行版本要快得多，因为数值流
    //避免了非针对性流那些没必要的自动装箱和拆箱操作。
    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1,n).reduce(0,Long::sum);
    }

    //并行归纳未做自动装箱和拆箱
    public static long prallelRangedSum(long n){
        return LongStream.rangeClosed(1,n).parallel().reduce(0,Long::sum);
    }
}
