package com.zhengjy.test.thread.实战java高并发.fork_join.t2;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

import static com.zhengjy.test.java8.parallel.ParallelStreamTest.measureSumPerf;

/**
 * Created by Jiyang.Zheng on 2018/10/15 14:42.
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    /**不再将任务分解为子任务的数组大小*/
    public static final long THRESHOLD = 10_000;

    /**要求和的数组*/
    private final long[] numbers;
    /**子任务处理数组的起始位置*/
    private final int start;
    /**子任务处理数组的结束位置*/
    private final int end;

    public ForkJoinSumCalculator(long[] numbers){
        this(numbers,0,numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers,int start,int end){
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }


    @Override
    protected Long compute() {
        System.out.println(Thread.currentThread().getName());
        //该任务负责求和的部分大小
        int length = end - start;
        //如果大小小于或等于阈值，顺序计算结果
        if (length <= THRESHOLD){
            return computeSequentially();
        }
        //创建子任务来为数组的前一半求和
            ForkJoinSumCalculator leftFork = new ForkJoinSumCalculator(numbers,start,start + length/2);
        //利用另外一个ForkJoinPool线程异步执行新创建的子任务
        leftFork.fork();
        //创建一个任务为数组的后一半求和
        ForkJoinSumCalculator rightFork = new ForkJoinSumCalculator(numbers,start+length/2,end);
        //同步执行第二个子任务，有可能允许进一步递归划分
        Long rightResult =rightFork.compute();
        //读取第一个子任务的结果，如果尚未完成就等待
        Long leftResult = leftFork.join();

        return leftResult+rightResult;
    }

    /**
     * 在子任务不可再分时计算结果
     * @return
     */
    private long computeSequentially(){
        long sum = 0;
        for (int i = start;i<end;i++){
            sum += numbers[i];
        }

        return sum;
    }


    public static long forkJoinSum(long n){
        long numbers[] = LongStream.rangeClosed(0,n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);

        return new ForkJoinPool().invoke(task);
    }

    public static void main(String[] args) {
        System.out.println("ForkJoin sum done in: " + measureSumPerf(
                ForkJoinSumCalculator::forkJoinSum, 10_000_000) + " msecs" );
    }
}
