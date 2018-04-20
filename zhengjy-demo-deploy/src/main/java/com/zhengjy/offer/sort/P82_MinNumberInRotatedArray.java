package com.zhengjy.offer.sort;

/**
 * Created by zhengjy on 2018/4/18 17:35
 * 旋转数组的最小数字
 */
public class P82_MinNumberInRotatedArray {
    public static int min(int[] data){
        if(data==null || data.length==0)
            return -1;
        int left = 0;
        int right = data.length-1;
        int mid;
        while(left<right){
            mid = left+(right-left)/2;
            //left < right
            if(data[left]<data[right])
                return data[left];
                //left > right
            else if(data[left]>data[right]){
                if(data[mid]>=data[left])
                    left = mid + 1;
                else
                    right = mid;
            }
            //left = right
            else{
                if(data[left]<data[mid])
                    left = mid + 1;
                else if(data[left]>data[mid])
                    right = mid;
                else{
                    left = left+1;
                    right = right-1;
                }
            }
        }
        return data[right];
    }


    public int min2(int[] data){
        int left=0,right=data.length-1,min;
        while (left < right){
            //(right-left/2)，因left下标更改为2，只求出2之后的数组长度取模，然后再加上left，表示取后半段的取模，再加上原有数组长度的模
            min = left + (right-left/2);
            //如果下标0 <  n-1 ，
            if(data[left] > data[right]){

            }
        }

        return 0;
    }


    public static void main(String[] args){
        int[] data1 = {1,2,3,4,5,7,8,9,10,11,12,13,0,1,1,1,1,2};
        int[] data2 = {1,0,1,1,1};
        int[] data3 = {1,1,1,0,1};
        System.out.println(min(data1));
        System.out.println(min(data2));
        System.out.println(min(data3));
    }
}

