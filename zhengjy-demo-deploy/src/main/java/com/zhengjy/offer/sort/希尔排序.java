package com.zhengjy.offer.sort;

import java.util.Arrays;

/**
 * Created by Jiyang.Zheng on 2018/6/9 18:23.
 */
public class 希尔排序 {

    private static void swap2(int []arr,int a,int b){
        int av = arr[a];
        arr[a] = arr[b];
        arr[b] = av;
    }


    private static void test(int arr[]){
        for (int gap = arr.length/2;gap>0;gap = gap/2){
            for (int i=gap;i<arr.length;i++){
                int j=i;
                while (j-gap >=0 && arr[j] < arr[j-gap]){
                    swap2(arr,j,j-gap);
                    j = j-gap;
                }
            }
        }
    }

    public static void main(String []args){
        int[] arr1 = new int[]{1,4,2,7,9,8,3,6,0,-1};
        test(arr1);
        System.out.println(Arrays.toString(arr1));
    }

    /**
     * 希尔排序 针对有序序列在插入时采用交换法
     * @param arr
     */
    public static void sort(int []arr){
        //增量gap，并逐步缩小增量
        for(int gap=arr.length/2;gap>0;gap/=2){
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            for(int i=gap;i<arr.length;i++){
                int j = i;
                while(j-gap>=0 && arr[j]<arr[j-gap]){
                    //插入排序采用交换法
                    swap(arr,j,j-gap);
                    j-=gap;
                }
            }
        }
    }

    /**
     * 希尔排序 针对有序序列在插入时采用移动法。
     * @param arr
     */
    public static void sort1(int []arr){
        //增量gap，并逐步缩小增量
        for(int gap=arr.length/2;gap>0;gap/=2){
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            for(int i=gap;i<arr.length;i++){
                int j = i;
                int temp = arr[j];
                if(arr[j]<arr[j-gap]){
                    while(j-gap>=0 && temp<arr[j-gap]){
                        //移动法
                        arr[j] = arr[j-gap];
                        j-=gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }
    /**
     * 交换数组元素
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int []arr,int a,int b){
        arr[a] = arr[a]+arr[b];
        arr[b] = arr[a]-arr[b];
        arr[a] = arr[a]-arr[b];
    }
}
