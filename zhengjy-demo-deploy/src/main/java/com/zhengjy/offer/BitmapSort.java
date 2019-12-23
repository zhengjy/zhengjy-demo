package com.zhengjy.offer;

/**
 * Created by Jiyang.Zheng on 2019/6/2 15:47.
 */
public class BitmapSort {
    public static int[] bitmapSort(int[] arr) {
        // 找出数组中最值
        int max = arr[0];
        int min = max;

        for (int i : arr) {
            if (max < i) {
                max = i;
            }
            if (min > i) {
                min = i;
            }
        }
        //初始化位图数组大小
        int temp=0;//用于解决数组有负数的情况
        int[] newArr=null;
        if(min<0){
            temp=0-min;
            newArr = new int[max - min + 1];
        }else{
            newArr = new int[max+1];
            min=0;
        }

        //构建位图
        for(int i:arr){
            newArr[i+temp]++;//算法体现
        }
        // 重新调整arr数组中的元素
        int index = 0;
        for (int i = 0; i < newArr.length; i++) {
            // 位图是1的就输出，对数组排序
            while (newArr[i] > 0) {
                arr[index] = i + min;
                index++;
                newArr[i]--;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr={5,2,3,7,3,1};
        //int[] arr={-5,2,-3,7,1};
        int[] arrsort=bitmapSort(arr);
        for(int i:arrsort)
            System.out.println(i);
    }
}
