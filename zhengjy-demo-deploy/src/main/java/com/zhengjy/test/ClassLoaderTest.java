package com.zhengjy.test;

/**
 * Created by zhengjy on 2017/7/10.
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
    }
}
