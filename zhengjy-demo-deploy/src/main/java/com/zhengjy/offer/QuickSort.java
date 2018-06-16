package com.zhengjy.offer;

/**
 * Created by Jiyang.Zheng on 2018/6/16 9:17.
 */
public class QuickSort {

    private static void quickSort2(int[] arr,int left,int right){
        if(left>=right){
            return;
        }

        int index = partition2(arr,left,right);
        //左
        quickSort2(arr,left,index-1);

        //右
        quickSort2(arr,index+1,right);
    }
    public static int partition2(int arr[],int left,int right){
        int temp = arr[left];
        while (left < right){
            while (arr[right] >= temp && right > left){
                right--;
            }
            arr[left] = arr[right];
            while (arr[left] <= temp && right > left){
                left++;
            }
            arr[right] = arr[left];
        }
        arr[right] = temp;
        return right;
    }

    /**
     * 一次快速排序
     * @param array 数组
     * @param left 数组的前下标
     * @param right 数组的后下标
     * @return key的下标index，也就是分片的间隔点
     */
    public static int partition(int []array,int left,int right){
        /** 固定的切分方式 */
        int key=array[left];//选取了基准点
        while(left<right){
            //从后半部分向前扫描
            while(array[right]>=key&&right>left){
                right--;
            }
            array[left]=array[right];
            //从前半部分向后扫描
            while(array[left]<=key&&right>left){
                left++;
            }
            array[right]=array[left];
        }
        array[right]=key;//最后把基准存入
        return right;
    }



    /**
     * 快速排序
     * @param array
     * @param left
     * @param right
     */
    public static void quickSort(int[] array,int left ,int right){
        if(left>=right){
            return ;
        }
        //进行第一轮排序获取分割点
        int index=partition(array,left,right);
        //排序前半部分
        quickSort(array, left, index - 1);
        //排序后半部分
        quickSort(array,index+1,right);
    }

    public static void main(String[] args) {
        int[] arr = {6,1,2,7,9,3,4,5,10,8};

        quickSort2(arr, 0, arr.length-1);

        for(int i:arr){
            System.out.print(i+",");
        }
    }
}
