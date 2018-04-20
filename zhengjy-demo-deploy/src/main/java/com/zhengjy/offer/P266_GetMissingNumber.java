package com.zhengjy.offer;

/**
 * Created by zhengjy on 2018/4/5 11:19
 */
public class P266_GetMissingNumber {
    public static int getMissingNumber(int[] data){
        int left = 0,right = data.length-1,mid;
        while (left<=right){
            mid=left+(right-left)/2;// 用位运算替换除法
            //注意加减法优先级高于位运算
//            mid = left+((right-left)>>1);
            if(data[mid]==mid)
                left = mid+1;
            else
                right = mid-1;
        }
        return left;
    }


    private static int num(int[] data){
        for(int i=0;i<data.length;i++){
            if(i != data[i]){
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args){




        int[] data1 = new int[]{0,1,2,3,4,5,7}; //6
        int[] data2 = new int[]{0,1,3,4,5}; //2
        int[] data3 = new int[]{1,2}; //0
        System.out.println(getMissingNumber(data1));
        System.out.println(getMissingNumber(data2));
        System.out.println(getMissingNumber(data3));
        System.out.println(num(data1));
        System.out.println(num(data2));
        System.out.println(num(data3));
    }
}
