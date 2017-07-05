package com.zhengjy.test.thread.other;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并行搜索，将原始数据集合按照期望的线程数进行分割，集合使用3个线程进行搜索，那么就可以把一个数组或集合分割成3个。每个线程各自独立搜索，
 * 当其中一个线程找到数据后，立即返回结果即可。
 * Created by zhengjy on 2017/7/4.
 */
public class ParallelSearch {

    static int arr[];
    static ExecutorService pool = Executors.newCachedThreadPool();
    static AtomicInteger result = new AtomicInteger(-1);
    static final int Thread_Num = 3;

    public static int search(int searchValue,int beginPos,int endPos){
        int i;
        for(i=beginPos;i<endPos;i++){
            if (result.get() >= 0){
                return result.get();
            }
            if(arr[i] == searchValue){
                //如果设置失败，表示其他线程已经找到了
                if(!result.compareAndSet(-1,i)){
                    return result.get();
                }
                return i;
            }
        }

        return -1;
    }

    public static class SearchTask implements Callable<Integer>{
        int begin,end,searchValue;

        public SearchTask(int begin, int end, int searchValue) {
            this.begin = begin;
            this.end = end;
            this.searchValue = searchValue;
        }

        @Override
        public Integer call() throws Exception {

            return search(searchValue,begin,end);
        }
    }


    public static int pSearch(int searchValue) throws ExecutionException, InterruptedException {
        int subArrSize = arr.length / Thread_Num+1;
        List<Future<Integer>> re = new ArrayList<>();
        for(int i=0;i<arr.length;i+=subArrSize){
            int end = i+subArrSize;
            if(end >=arr.length){
                end = arr.length;
            }
            re.add(pool.submit(new SearchTask(i,end,searchValue)));
        }

        for (Future<Integer> f : re){
            if(f.get() >= 0){
                return f.get();
            }
        }
        return -1;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        arr = new int[]{1,2,5,44,22,45,77,43,545,24,65,7,43,212,233,54,34,0,78,6,7,8,56,78,99,34,15,577,32};
        System.out.println(pSearch(577));

    }
}
