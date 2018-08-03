package com.zhengjy.offer.sort;

import java.util.Arrays;

/**
 * Created by Jiyang.Zheng on 2018/6/30 9:46.
 */
public class 堆排序2 {


    public static void main(String []args){
        int[] arr = new int[]{1,4,2,7,9,8,3,6,0,5};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void helpSort(int arr[],int i,int len){
        int temp = arr[i];
        for (int k=i*2+1;k < len;k=k*2+1){
            if (k+1 < len && arr[k] < arr[k+1]){
                k++;
            }
            if (arr[k] > temp){
                arr[i] = arr[k];
                i=k;
            }else {
                break;
            }
        }
        arr[i] = temp;
    }

    private static void sort(int arr[]){
        for (int i= arr.length/2-1;i >= 0;i--){
            helpSort(arr,i,arr.length);
        }

        for (int i=arr.length-1;i>0;i--){
            swap(arr,0,i);
            helpSort(arr,0,i);
        }
    }

    public static void swap(int []arr,int left ,int right){
        int temp=arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
