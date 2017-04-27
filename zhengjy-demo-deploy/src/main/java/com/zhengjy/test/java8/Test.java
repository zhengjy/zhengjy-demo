package com.zhengjy.test.java8;


import java.io.File;

/**
 * Created by zhengjy on 2017/3/3.
 */
public class Test {
    public static void main(String[] args) {
        File[] hf = new File(".").listFiles(File::isHidden);
    }
}
