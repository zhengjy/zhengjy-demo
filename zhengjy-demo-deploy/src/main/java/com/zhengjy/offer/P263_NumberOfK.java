package com.zhengjy.offer;

/**
 *
 * Created by zhengjy on 2018/4/5 9:43
 *
 * 数字在排序数组中出现的次数
 */
public class P263_NumberOfK {
    //遍历的话时间复杂度为o(n)
    //考虑到数组是排序好的，可使用二分法，时间复杂度o(logn)
    public static int getNumberOfK(int[] data,int k){
        int start = getStartOfK(data,k);
        if(start==-1)
            return 0;
        int end = getEndOfK(data,start,k);
        return end-start+1;
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
        if(data[0]>k || data[data.length-1]<k)
            return -1;
        int left = 0,right = data.length-1,mid;
        while (left<=right) {
            if(right==left){
                if(data[left]==k)
                    return left;
                else
                    return -1;
            }
            //当长度为2，mid取左值
            mid = left + (right - left) / 2;
            if (data[mid] > k)
                right = mid-1;
            else if(data[mid] < k)
                left = mid+1;
            else
                right = mid;
        }
        return -1;
    }
    public static int getEndOfK(int[] data,int start, int k){
        int left = start,right = data.length-1,mid;
        while (left<=right){
            if(left==right){
                if(data[left]==k)
                    return left;
                else
                    return -1;
            }
            //当长度为2，mid取右值
            mid = left + (right - left + 1) / 2;
            if (data[mid] > k)
                right = mid-1;
            else if(data[mid] < k)
                left = mid+1;
            else
                left = mid;
        }
        return -1;
    }
    public static void main(String[] args){
//        int[] data0 = new int[]{1,2,4};
//        int[] data1 = new int[]{1,2,3,3,3,3,5,6};
//        int[] data2 = new int[]{1,2,3,3,3,3,4,5};
//        int[] data3 = new int[]{3,3,3,3,3,3,3,3};
//        System.out.println(getNumberOfK(data0,5));
//        System.out.println(getNumberOfK(data1,4));
//        System.out.println(getNumberOfK(data2,3));
//        System.out.println(getNumberOfK(data3,3));
    }

}
