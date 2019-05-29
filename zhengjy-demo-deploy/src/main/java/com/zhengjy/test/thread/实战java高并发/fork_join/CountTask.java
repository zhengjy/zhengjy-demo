package com.zhengjy.test.thread.实战java高并发.fork_join;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Fork/Join  如果你要处理1000个数据，但是你不具备处理100个数据的能力，那么你可以只处理其中10个，然后分阶段处理100次，
 * 然后将100次的结果进行合成，最终返回想要的结果。
 * Created by zhengjy on 2017/7/1.
 */
public class CountTask extends RecursiveTask<Long> {
    //分解规模，大于10000就分解任务
    private static final int THRESHOLD = 10000;
    private long start;
    private long end;
    public CountTask(long start,long end){
        this.start = start;
        this.end = end;
    }


    @Override
    protected Long compute() {
        long sum = 0;
        boolean canCompute = (end - start) < THRESHOLD;
        if (canCompute){
            for(long i=start;i<=end;i++){
                sum += i;
            }
            //分解任务
        }else {
            //将原有任务划分成100个等规模的小任务
            long step =(start+end) / 100;
            ArrayList<CountTask> subTasks = new ArrayList<>();
            long pos = start;
            for(int i=0;i<100;i++){
                long lastOne = pos+step;
                if(lastOne > end){
                    lastOne = end;
                }
                CountTask subTask = new CountTask(pos,lastOne);
                pos += step+1;
                subTasks.add(subTask);
                //创建子进程处理，使系统可以多一个执行分支
                subTask.fork();invokeAll();
            }
            //等所有子任务结束，在进行一次求和
            for (CountTask t : subTasks){
                sum += t.join();
            }
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        CountTask task = new CountTask(0,200000L);
        ForkJoinTask<Long> result = pool.submit(task);
        long res = result.get();
        System.out.println("sum="+res);

    }
}
