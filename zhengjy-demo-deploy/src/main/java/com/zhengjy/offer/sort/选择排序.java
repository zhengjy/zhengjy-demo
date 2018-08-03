package com.zhengjy.offer.sort;

/**
 * Created by Jiyang.Zheng on 2018/6/22 10:25.
 */
public class 选择排序 {


    private static void selectionSort(int arr[]){
        for (int i=0;i<arr.length;i++){
            int min = i;
            for (int j=i;j<arr.length;j++){
                if(arr[j] <= arr[min]){
                    min = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,4,2,7,9,8,3,6,0,-1};
        selectionSort(arr);
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+",");
        }
    }
}
