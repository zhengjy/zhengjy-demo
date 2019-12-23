package com.zhengjy.test.concurrent.reentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Jiyang.Zheng on 2019/10/30 9:08.
 */
public class Depot {

    private int capacity;//仓库容量
    private int size;//仓库实际数量
    private Lock lock; //独占锁
    private Condition fullCondition; //生产条件
    private Condition emptyCondition;//消费条件

    public Depot(int capacity){
        this.capacity = capacity;
        this.size = 0;
        this.lock = new ReentrantLock();
        this.fullCondition = lock.newCondition();
        this.emptyCondition = lock.newCondition();
    }

    public void produce(int val) {
        lock.lock();
        try {
            //表示想要生产的数量
            int left = val;
            while (left > 0){
                //库存已满，等待消费者消费产品
                while (size >= capacity){
                    fullCondition.await();
                    // 获取“实际生产的数量”(即库存中新增的数量)
                    // 如果“库存”+“想要生产的数量”>“总的容量”，则“实际增量”=“总的容量”-“当前容量”。(此时填满仓库)
                    // 否则“实际增量”=“想要生产的数量”
                    int inc = (size+left)>capacity ? (capacity-size) : left;
                    size += inc;
                    left -= inc;
                    System.out.printf("%s produce(%3d) --> left=%3d, inc=%3d, size=%3d\n",
                                                     Thread.currentThread().getName(), val, left, inc, size);
                    // 通知“消费者”可以消费了。
                    emptyCondition.signal();

                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void consume(int val) {
        lock.lock();
        try {
            // left 表示“客户要消费数量”(有可能消费量太大，库存不够，需多此消费)
            int left = val;
            while (left > 0) {
                // 库存为0时，等待“生产者”生产产品。
                while (size <= 0)
                    emptyCondition.await();
                // 获取“实际消费的数量”(即库存中实际减少的数量)
                // 如果“库存”<“客户要消费的数量”，则“实际消费量”=“库存”；
                // 否则，“实际消费量”=“客户要消费的数量”。
                int dec = (size<left) ? size : left;
                size -= dec;
                left -= dec;
                System.out.printf("%s consume(%3d) <-- left=%3d, dec=%3d, size=%3d\n",
                        Thread.currentThread().getName(), val, left, dec, size);
                fullCondition.signal();
            }
        } catch (InterruptedException e) {
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 消费者
 */
class Producer {
    private Depot depot;
    public Producer(Depot depot){
        this.depot = depot;
    }
    
    public void produre(int val){
        new Thread(() -> depot.produce(val)).start();
    }
}

class Costomer {
    private Depot depot;
    public Costomer(Depot depot){
        this.depot = depot;
    }
    
    public void consume(int val){
        new Thread(() -> depot.consume(val)).start();
    }
    
}