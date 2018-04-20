package com.zhengjy.offer;

import java.util.ArrayDeque;
import java.util.Deque;

/**滑动窗口的最大值
 *
 *
 * Created by zhengjy on 2018/4/9 15:19
 */
public class P288_MaxInSlidingWindow {

    private static int[] getMaxNumAndPoint(int[] data , int i, int num){
        int[] ret = {};
        for(int j=i;j<i+num;j++){
            if(data[j] > data[i] ){
                ret = new int[]{data[j],j};
            }
        }
        return ret;
    }

    private static void window(int[] data ,int num){
        if(data==null || data.length <num-1){
            return;
        }
        int maxNum = 0,maxPoint =-1,nextPoint=num-1;

        for(int i=0;i<=data.length-num;i++){
            if(i==0){
                int[] ret = getMaxNumAndPoint(data,i,num);
                if(ret != null && ret.length > 0 ){
                    maxNum = ret[0];
                    maxPoint = ret[1];
                }else {
                    maxNum = data[i];
                    maxPoint = i;
                }
            }else {
                if(data[nextPoint] > data[i]){
                    maxNum = data[nextPoint];
                    maxPoint = nextPoint;
                }else {
                    maxNum = data[i];
                    maxPoint = i;

                }
            }

            nextPoint++;
            System.out.print( maxNum +"\t");
        }
    }


    public static void main(String[] args) {
        int[] data = new int[]{2,3,4,2,6,2,5,1};
//        int[] data = new int[]{2,0,0,2,6,2,5,1};

        window(data,3);
        System.out.println();
        int[] result = maxInWindows(data,3);
        for(int i=0;i<result.length;i++){
            System.out.print(result[i]);
            System.out.print("\t");
        }
    }

//-------========================================


    //把可能会成为最大值的下标存储下来，从而降低扫描次数
    public static int[] maxInWindows(int[] data,final int size){
        if(data==null ||data.length==0||data.length<size)
            return new int[0];
        int[] result = new int[data.length-size+1];
        Deque<Integer> deque = new ArrayDeque<>();

        for(int i=0;i<size-1;i++){
            while (!deque.isEmpty()&&data[i]>=data[deque.getLast()])
                deque.removeLast();
            deque.addLast(i);
        }
        for(int i=size-1;i<data.length;i++){
            while (!deque.isEmpty()&&i-deque.getFirst()+1>size)
                deque.removeFirst();
            while (!deque.isEmpty()&&data[deque.getLast()]<=data[i])
                deque.removeLast();
            deque.addLast(i);
            result[i-(size-1)] = data[deque.getFirst()];
        }
        return result;
    }





}
