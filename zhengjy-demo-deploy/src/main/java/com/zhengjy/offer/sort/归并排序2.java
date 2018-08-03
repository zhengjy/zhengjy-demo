package com.zhengjy.offer.sort;

import java.util.Arrays;

/**
 * Created by Jiyang.Zheng on 2018/6/24 14:35.
 */
public class 归并排序2 {

    private static void sort(int[] arr){
        int temp[] = new int[arr.length];

        mergeSort(arr,0,arr.length-1,temp);
    }

    private static void  mergeSort(int arr[],int left,int right,int[] temp){

        if(left <right){
            int mid = (left+right)/2;
            //左
            mergeSort(arr,left,mid,temp);
            //右
            mergeSort(arr,mid+1,right,temp);
            merge(arr,left,mid,right,temp);

        }


    }

    private static void merge(int[] arr,int left,int mid,int right,int[] temp){
//        int i = left;
//        int j = mid+1;
//        int k = 0;
//        while (i <= mid && j <= right){
//           if (arr[i] <= arr[j]){
//               temp[k++] = arr[i++];
//           }else {
//               temp[k++] = arr[j++];
//           }
//        }
//        while (i <= mid){
//            temp[k++] = arr[i++];
//        }
//
//        while (j <= right){
//            temp[k++] = arr[j++];
//        }
//
//        k= 0;
//        while (left <= right){
//            arr[left++] = temp[k++];
//        }
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,4,2,7,9,8,3,6,0,-1};
//        int []temp = new int[arr.length];
//        merge(arr,0,5,9,temp);
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
