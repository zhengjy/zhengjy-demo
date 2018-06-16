package com.zhengjy.test.thread.实战java高并发.fork_join.t1;

import java.util.Map;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by Jiyang.Zheng on 2018/6/7 16:08.
 */
public class ForkJoinPoolTest {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Map<String, Integer> map = forkJoinPool.invoke(new ForkRecursiveTask("e:/txts"));
        //输出最终所有的计算结果
        for(String key : map.keySet()){
            System.out.println(key + "=" + map.get(key));
        }
    }
}
