package com.zhengjy.offer.sort;

/**
 * Created by Jiyang.Zheng on 2018/6/22 10:05.
 */
public class 冒泡排序 {

    private static void bubbleSort(int[] arr){
        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr.length-1-i;j++){
                if(arr[j+1] < arr[j]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,4,2,7,9,8,3,6,0,-1};
        bubbleSort(arr);
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+",");
        }
    }
}
