package com.zhengjy.offer;

/**
 * Created by zhengjy on 2018/4/6 15:45
 */
public class 递归乘法表 {

    public static void main(String[] args) {
        run(9);
    }

    public static void run(int i){
        if(i < 1){
            return;
        }
        run(i-1);
        for(int j=1;j<=i;j++){
            System.out.print(j + "*" + i + "=" + i * j +"\t");
        }

        System.out.println();
    }


}
