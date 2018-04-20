package com.zhengjy.offer;

/**和为s的连续正数序列
 * Created by zhengjy on 2018/4/8 16:34
 */
public class P282_ContinuousSequenceWithSum {
    public static void findContinuousSequence(int sum){
        int b=1,e=2,middle=sum >> 1,curNum =b+e;
        while (b <= middle){
            if(curNum < sum){
                e++;
                curNum+=e;
            }else if(curNum > sum){
                curNum-=b;
                b++;
            }else {
                printContinousSequence(b,e);
                e++;
                curNum+=e;
            }
        }

    }
    public static void printContinousSequence(int small,int big){
        for(int i=small;i<=big;i++){
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
    }
    public static void main(String[] args){
        findContinuousSequence(15);
    }

}
