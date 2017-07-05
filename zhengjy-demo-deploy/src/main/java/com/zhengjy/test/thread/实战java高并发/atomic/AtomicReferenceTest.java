package com.zhengjy.test.thread.实战java高并发.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by zhengjy on 2017/7/2.
 */
public class AtomicReferenceTest {
    static  AtomicReference<Integer> money = new AtomicReference<>();
    static {

        //设置账户初始值小于20，这个是一个需要被充值的账户
        money.set(19);
    }

    public static void recharge(){
        //模拟多个线程同时更新数据库，为用户充值
        for(int i=0;i<3;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        while (true){
                            Integer m = money.get();
                            if(m < 20){
                                if(money.compareAndSet(m,m+20)){
                                    System.out.println("余额小于20元，充值成功，余额："+money.get()+" 元");
                                    break;
                                }
                            }else {
                                System.out.println("余额大于20元，无需充值");
                                break;
                            }
                        }
                    }
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        recharge();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100;i++){
                    while (true){
                        Integer m = money.get();
                        if(m > 10){
                            System.out.println("大于10元");
                            if(money.compareAndSet(m,m-10)){
                                System.out.println("成功消费10元，余额："+money.get());
                                break;
                            }else {
                                System.out.println("没有足够的金额");
                                break;
                            }
                        }

                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
