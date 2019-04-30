package com.zhengjy.test.qps;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 解决方案1(假设需要控制在1s内不多于400个请求)：
 *
 * 方案描述:
 * 使用数组存储每个请求到来的时间，前400次请求顺利通过，并填满数组。
 * 后续请求到来时，判断当前时间是否比数组中最早的时间晚1s，未晚，则打回，
 * 晚则替换数组中最早的值。循环。
 * Created by Jiyang.Zheng on 2019/1/17 13:26.
 */
public class WindowQpsControl {
    /**
     * 接受请求窗口
     */
    private Long[] accessWindow;
    /**
     * 窗口大小
     */
    private int limit;
    /**
     * 指针位置
     */
    private int curPosition;
    /**
     * 时间间隔
     */
    private long period;

    private final Object lock = new Object();

    /**
     * 1秒内最多400次请求
     * @param limit 限制次数 400
     * @param period 时间间隔 1
     * @param timeUnit 间隔类型 秒
     */
    public WindowQpsControl(int limit, int period, TimeUnit timeUnit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + limit);
        }
        curPosition = 0;
        this.period = timeUnit.toMillis(period);
        this.limit = limit;
        accessWindow = new Long[limit];
        Arrays.fill(accessWindow, 0L);
    }

    public boolean isPass() {
        long curTime = System.currentTimeMillis();
        synchronized (lock) {
            //判断当前时间是否比数组中最早的时间晚1s，未晚，则打回， 晚则替换数组中最早的值。循环。
            if (curTime >= period + accessWindow[curPosition]) {
                accessWindow[curPosition++] = curTime;
                curPosition = curPosition % limit;
                return true;
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        WindowQpsControl ops = new WindowQpsControl(2,1,TimeUnit.MINUTES);
        ops.isPass();
        ops.isPass();
        ops.isPass();
        ops.isPass();
        ops.isPass();
        ops.isPass();
        ops.isPass();
        ops.isPass();
        ops.isPass();
        System.out.println();

    }
}
