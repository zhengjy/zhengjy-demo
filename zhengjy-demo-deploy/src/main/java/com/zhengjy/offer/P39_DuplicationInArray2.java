package com.zhengjy.offer;

import java.util.Arrays;

/**在一个长度为n的数组中，所有数字的取值范围都在[0,n-1]，但不知道有几个数字重复或重复几次，找出其中任意一个重复的数字。
 * Created by zhengjy on 2018/4/16 14:59
 */
public class P39_DuplicationInArray2 {
    //方法一：暴力求解，不会修改原始数据，时间复杂度o(n^2)，空间复杂度o(1)
    public static int getDuplication(int[] data){
        for(int i=0;i<data.length;i++){
            for(int j=i+1;j<data.length;j++){
                if(data[i] == data[j]){
                    return data[i];
                }
            }
        }
        return -1;
    }

    public static int getDuplication2(int[] data){
        Arrays.sort(data);

        int pav = data[0];
        for(int i=1;i<data.length;i++){
            if(data[i] == pav){
                return data[i];
            }else {
                pav = data[i];
            }

        }
        return -1;
    }

    public static int getDuplication3(int[] data){

        int[] newdata = new int[data.length];
        for(int d: data){
            if(newdata[d] >= 1){
                return d;
            }else {
                newdata[d] = 1;
            }
        }

        return -1;
    }

    public static int getDuplication4(int[] data){

        for(int i=0;i<data.length;i++){
            while (data[i] != i){
                if(data[i] == data[data[i]]){
                    return data[i];
                }else {
                    int temp = data[i];
                    data[i] = data[temp];
                    data[temp] = temp;
                }

            }
        }
        return -1;
    }

    /*


1、取模，取middle
2、查找前半部分，如middle=2，则无重复则返回3个元素，有重复返回大于3
3、验证返回的count是否 > middle ，如果大于更改“结束坐标”，为middle，因为已经确认 < middle 前半部分有重复数值，
                                如果不大于更改“起始坐标”为middle+1，因为已经确认middle前部分未找到重复数值
4、如果发生start、end坐标相等，表示已经定位到重复数值区间，然后验证count>1则返回。



     */
    public static int getDuplication5(int[] data){

        int start=0,end=data.length-2;
        while (start<=end){
            int middle = (end-start)/2+start;
            int count = countRange(data,start,middle);
            if(start==end){
                if(count > 1){
                    return data[start];
                }
            }

            if(count > middle-start+1){
                end = middle;
            }else {
                start = middle+1;
            }

        }

        return -1;
    }

    private static int countRange(int[] data,int start,int end){
        int count=0;
        for(int i=start;i<data.length;i++){
            if(start <= data[i] && end >= data[i]){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args){
        int[] data = {2,3,1,0,2,5,3};
        System.out.println(getDuplication(data));
        System.out.println(getDuplication2(data));
        System.out.println(getDuplication3(data));
        System.out.println(getDuplication4(data));
        System.out.println(getDuplication5(data));


        int[] data1 = {2,3,1,0,4,5,5};
        System.out.println(getDuplication(data1));
        System.out.println(getDuplication2(data1));
        System.out.println(getDuplication3(data1));
        int[] data2 = {2,3,1,0,4,5,5};
        System.out.println(getDuplication5(data2));

    }

}
