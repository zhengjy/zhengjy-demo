package com.zhengjy.spring.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhengjy on 2017/4/24.
 */
@Service
public class ScheduleTaskService {
    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 500000)
    public void reportCurrentTime(){
        System.out.println("每隔5秒执行一次："+sdf.format(new Date()));
    }

    @Scheduled(cron = "0 38 15 ? * *")
    public void fixTime(){
        System.out.println("在指定时间内执行："+sdf.format(new Date()));
    }
}
