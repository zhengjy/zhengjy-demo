package com.zhengjy.test.java8.t4;

/**
 * Created by Jiyang.Zheng on 2018/10/16 14:07.
 */
public class IsNumeric implements ValidationStrategy {
    @Override
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}
