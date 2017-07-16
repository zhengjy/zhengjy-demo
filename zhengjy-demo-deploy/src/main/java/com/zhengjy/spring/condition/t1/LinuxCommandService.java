package com.zhengjy.spring.condition.t1;

import org.springframework.stereotype.Service;

/**
 * Created by zhengjy on 2017/7/15.
 */
public class LinuxCommandService implements CommandService {

    public String showListCommand() {
        return "ls";
    }

}
