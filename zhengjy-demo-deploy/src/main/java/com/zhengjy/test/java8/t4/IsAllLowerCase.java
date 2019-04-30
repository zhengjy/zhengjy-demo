package com.zhengjy.test.java8.t4;

/**
 * Created by Jiyang.Zheng on 2018/10/16 14:06.
 */
public class IsAllLowerCase implements ValidationStrategy {
    @Override
    public boolean execute(String s) {
        return s.matches("[a-z]+");
    }
}
