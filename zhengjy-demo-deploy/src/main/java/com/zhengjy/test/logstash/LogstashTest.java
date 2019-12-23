package com.zhengjy.test.logstash;

import org.slf4j.LoggerFactory;

public class LogstashTest {
    private static final org.slf4j.Logger LGR = LoggerFactory.getLogger(LogstashTest.class);


    public static void main(String[] args) {
        try{
            int i = 10 /0;
        }catch(ArithmeticException ex){
            LGR.error("大家好111!", ex);
        }
    }
    public void test() {

    }
    private void divide(){

    }
}
