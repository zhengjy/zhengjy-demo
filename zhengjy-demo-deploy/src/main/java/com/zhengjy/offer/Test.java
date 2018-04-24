package com.zhengjy.offer;

/**
 * Created by zhengjy on 2018/4/5 17:46
 */
public class Test {


    public static void num(int[] arr, int sum){
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i] + arr[j]  == sum){
                    System.out.println(arr[i] +"+" + arr[j] +"=" + sum);
                }
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = {0,1,7,2,10,5,11,12};
        //1+11,3+9,5+7,9+3，0+12,11+1
        num(arr,12);


        System.out.println(8%4);
    }
}
