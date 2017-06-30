package com.zhengjy.spring.other;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by zhengjy on 2017/6/30.
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj> {

    public MyMessageConverter() {
        super(new MediaType("applicaiton","x-wisely", Charset.forName("UTF-8")));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return DemoObj.class.isAssignableFrom(clazz);
    }

    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(inputMessage.getBody(),Charset.forName("UTF-8"));
        String[] tempArr =temp.split("&");
        return new DemoObj(tempArr[0],tempArr[1]);
    }

    @Override
    protected void writeInternal(DemoObj demoObj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String out = "hello："+demoObj.getId() +"-" +demoObj.getName();
        outputMessage.getBody().write(out.getBytes());
    }
}
