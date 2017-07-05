package com.zhengjy.test.thread.实战java高并发;

/**
 * Created by zhengjy on 2017/7/2.
 */
public class FalseSharing implements Runnable {
    public final static int NUM_THREADS =4;
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;
    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];
    static {
        for(int i=0;i<longs.length;i++){
            longs[i] = new VolatileLong();
        }
    }

    public FalseSharing(int arrayIndex){
        this.arrayIndex = arrayIndex;
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads =new Thread[NUM_THREADS];
        for(int i=0;i<threads.length;i++){
            threads[i] = new Thread(new FalseSharing(i));
        }
        for(Thread t : threads){
            t.join();
        }
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i){
            longs[arrayIndex].value = i;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        runTest();
        System.out.println("duration="+(System.currentTimeMillis() - start));
    }

    public final static  class VolatileLong{
        public volatile long value =0L;
//        public long p1,p2,p3,p4,p5,p6,p7;

    }
}
