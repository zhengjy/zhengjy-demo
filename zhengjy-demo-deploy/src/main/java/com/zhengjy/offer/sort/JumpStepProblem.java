package com.zhengjy.offer.sort;

/**
 * Created by zhengjy on 2018/4/22.
 */
public class JumpStepProblem {
    //解法2：迭代
    public int getRecursion(int n){
        if(n < 0)
            return -1;
        if(n <= 2)
            return n;
        int temp1 = 1;
        int temp2 = 2;
        int result = 0;
        for(int i = 3;i <= n;i++){
            result = temp1 + temp2;
            temp1 = temp2;
            temp2 = result;
        }
        return result;
    }

    public static void main(String[] args){
        JumpStepProblem test = new JumpStepProblem();
        System.out.println("使用迭代法求解结果："+test.getRecursion(10));
    }
}
