package com.zhengjy.test;

import java.util.List;

/**
 * Created by zhengjy on 2018/4/23 16:13
 */
public class Redis分布式锁 {
    public static void main(String[] args) {


    }

//    private boolean getLock(){
//        long expire = 30000;
//        //1、获取当前时间
//        long startTime = System.currentTimeMillis();
//        //2、依次向N个节点获取锁
//        List<String> redisNode = null;
//        int successNode = 0;
//        for(String node:redisNode){
//            redis.setNx("cid-orderId","cid-orderId",expire);
//            successNode ++ ;
//        }
//
//        //3、计算像N个节点获取锁时间,(当前时间 - 获取N个节点时间) < 超时时间(30) && 成功节点 >= N/2+1
//        long setnxTime =  System.currentTimeMillis();
//        if((setnxTime - startTime) < expire && successNode > redisNode.size()/2+1){
//            redis.expire("cid-orderId",expire -(setnxTime - startTime));
//            return true;
//        }
//        return false;
//
//    }
}
