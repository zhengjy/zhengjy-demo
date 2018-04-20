package com.zhengjy.offer;

/**和为s的数字
 * Created by zhengjy on 2018/4/8 15:07
 */
public class P280_TwoNumbersWithSum {
    public static int[] findNumbersWithSum(int[] data,int sum){

        int left =0,right=data.length-1;

        for(int i=0;i<=right;i++){
            int num = data[left] + data[right];
            if(num < sum){
                left++;
            }else if(num > sum){
                right--;
            }else if(num == sum){
                System.out.println(data[left] + "+" + data[right] + "=" + sum);
                left++;
                right--;
            }
        }



        int[] result = new int[]{0,0};
//        if(data==null || data.length<2)
//            return result;
//        int curSum = data[0]+data[data.length-1];
//        int left = 0,right = data.length-1;
//        while (curSum!=sum && left<right){
//            if(curSum>sum)
//                right--;
//            else
//                left++;
//            curSum = data[left]+data[right];
//        }
//        if(curSum==sum){
//            result[0] = data[left];
//            result[1] = data[right];
//        }
        return result;

    }
    public static void main(String[] args){
        int[] data = new int[]{0,1,7,2,10,5,11,12,2,4,8};
        int[] result = findNumbersWithSum(data,12);
    }

}
