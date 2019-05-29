package com.zhengjy.offer;

/**
 * Created by zhengjy on 2018/5/20.
 */
public class 斐波那契 {
    public static void main(String[] args) {
        int [] data = new int[40];
        data[0] =1;
        data[1] =1;
//        for(int i=3;i<data.length;i++){
//            data[i] = data[i-1] + data[i-2];
//            System.out.println(data[i]);
//        }
            System.out.println(f(40));
    }

    private static int f(int n){
        if(n < 2){
            return n==0?0:1;
        }
        int ret = f(n-1) + f(n-2);
        System.out.println(ret);
        return ret;
    }


}
