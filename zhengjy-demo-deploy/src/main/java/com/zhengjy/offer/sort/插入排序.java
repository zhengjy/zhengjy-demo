package com.zhengjy.offer.sort;

/**
 * Created by Jiyang.Zheng on 2018/6/22 11:13.
 */
public class 插入排序 {

    public static void insertionSort(int[] arr){
        for (int i=0;i<arr.length;i++){
            int index = i-1;
            int cur = arr[i];
            while (index >= 0 && arr[index] > cur){
                arr[index+1] = arr[index];
                index--;
            }
            arr[index+1] = cur;
        }


    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,4,2,7,9,8,3,6,0,-1};
        insertionSort(arr);
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+",");
        }
    }
}
