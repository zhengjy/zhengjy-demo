package com.zhengjy.spring.filter;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;

/**
 * Created by zhengjy on 2017/7/13.
 */
// HttpServletRequestWrapper 类实现了HttpServletRequest这个接口
// 继承这个实现类，进行方法重写来实现对请求信息的修改操作
public class  EscapeWrapper  extends HttpServletRequestWrapper {

    public EscapeWrapper(HttpServletRequest request) {

        super(request);

    }



    // 之前我们的过滤器不能对请求参数进行操作，但现在我们可以使用这个装饰模式对请求参数进行操作
    // 重写getParameter(String str)方法 来操作请求参数
    @Override
    public String  getParameter(String name){

        // 获取请求参数
        String value= super.getParameter(name);
        System.out.println("修改前"+value);
        if(StringUtils.isBlank(value)){
            return value;
        }
        try {
            // 编码设置
            byte[] by= value.getBytes("ISO8859-1");

            // 编码过滤器,这里的编码是自己定义的编码
            value= new String(by,"utf-8");

        }catch (UnsupportedEncodingException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();

        }

        //这里可以对内容进行相应的操作
        value=value.replaceAll("a","b");
        System.out.println("修改后"+value);
        return value;

    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        trim(values);
        return values;
    }


    public void trim(String[] values){
        for(String val : values){
            val.trim();
        }
    }


}
