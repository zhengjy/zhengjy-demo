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
//        int[] arr = {0,1,7,2,10,5,11,12};
//        //1+11,3+9,5+7,9+3，0+12,11+1
//        num(arr,12);
//        System.out.println(8%4);
        int[] arr = {0,1,2,3,5,6,3};
//        int[] arr = {1, 2, 3, 3, 4, 10};
        int[] dup = new int[6];
//        duplicate(arr,6,dup);
        get(arr);
    }

    public static boolean get(int arr[]){
        int newArr[] = new int[7];
        for (int i=0;i< arr.length;i++){
            if (newArr[arr[i]]!=0){
                System.out.println(arr[i]);
            }else {
                newArr[arr[i]] = arr[i];
            }

        }

        for (int i=0;i< newArr.length;i++){
            if (newArr[i] == 0){
                System.out.println(i);
            }
        }
        return false;
    }


    public static boolean duplicate(int[] nums, int length, int[] duplication) {
        if (nums == null || length <= 0)
            return false;
        for (int i = 0; i < length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    duplication[0] = nums[i];
                    return true;
                }
                swap(nums, i, nums[i]);
            }
        }
        return false;
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
