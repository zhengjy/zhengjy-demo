package com.zhengjy.offer;

/**
 *
 * Created by zhengjy on 2018/4/5 9:43
 *
 * 数字在排序数组中出现的次数
 */
public class P263_NumberOfK2 {
    //遍历的话时间复杂度为o(n)
    //考虑到数组是排序好的，可使用二分法，时间复杂度o(logn)
    public static int getNumberOfK(int[] data,int k){
        int start = getStartOfK(data,k);
        if(start == -1)
            return 0;
        int end = getEndOfK(data,k);
        return end+start;
    }

    /**
     * 获取前半段
     * @param data
     * @param k
     * @return
     */
    public static int getStartOfK(int[] data,int k){
        //data[0]>k             数组第一个元素 > 比较的值 (7 > 5) , 则表示数组中所有元素都是 > 要比较的值
        //data[data.length-1]<k 数组最后一个元素 < 比较的值 (4 < 5),则表示数组中所有元素都是 < 要比较的值
        if(data == null || data[0]>k || data[data.length-1]<k){
            return -1;
        }
        int mod = data.length/2;
        int count =0;
        for(int i=0;i<=mod;i++){
            //大于比较的元素直接返回
            if(data[i] > k){
                break;
            }
            if(data[i] == k){
                count ++;
            }
        }
        return count;
    }
    public static int getEndOfK(int[] data,int k){
        int mod = (data.length/2)+1;
        int count =0;
        for(int i=mod;i<data.length;i++){
            //大于比较的元素直接返回
            if(data[i] > k){
                break;
            }
            if(data[i] == k){
                count ++;
            }
        }
        return count;
    }
    public static void main(String[] args){
        int[] data0 = new int[]{3,3,3,4,4,5,6,7};
        int[] data1 = new int[]{1,2,3,3,3,3,5,6};
        int[] data2 = new int[]{1,2,3,3,3,3,4,5};
        int[] data3 = new int[]{3,3,3,3,3,3,3,3};
        System.out.println(getNumberOfK(data0,3));
        System.out.println(getNumberOfK(data1,4));
        System.out.println(getNumberOfK(data2,3));
        System.out.println(getNumberOfK(data3,3));
    }

}
