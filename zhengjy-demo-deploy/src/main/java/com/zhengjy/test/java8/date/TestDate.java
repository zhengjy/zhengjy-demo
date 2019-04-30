package com.zhengjy.test.java8.date;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * Created by Jiyang.Zheng on 2018/10/18 9:20.
 */
public class TestDate {
    public static void main(String[] args) {
        LocalDate ld = LocalDate.now();
        int year = ld.getYear();
        Month month = ld.getMonth();
        int day = ld.getDayOfMonth();

        LocalDate date = LocalDate.of(2014, 3, 18);
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
