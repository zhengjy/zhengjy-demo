package com.zhengjy.test.logstash;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Jiyang.Zheng on 2019/5/27 10:48.
 */
public class LogIpConfig extends ClassicConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogIpConfig .class);

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            LOGGER.error("获取日志Ip异常", e);
        }
        return "";
    }
}
