package com.zhengjy.spring.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * Fiter过滤器执行是在请求还没到达对应的servlet，
 * interceptor 是一般拦截某个方法
 * Created by zhengjy on 2017/7/13.
 */
@Component
public class FilterTest implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("fiter  init --------");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("fiter  doFilter --------");
        chain.doFilter(new HttpServletRequestWrapperTest((HttpServletRequest) request),
                       new HttpServletResponseWrapperTest((HttpServletResponse) response));

        // 在这里给FilterChain 传递的参数是传装饰后的request,之后调用的就是EscapeWrapper重写的getParameter(String name )这个方法
        // 获取装饰后的Request对象
        EscapeWrapper  ew =new EscapeWrapper((HttpServletRequest)request);
        // 使用过滤器就是在请求到大server之前，调用service(HttpServletRequest request,HttpServletResponse response)之前将request对象替换为装饰后的request对象
        // 只要使用了这个过滤器的请求的reques对象都会被替换为装饰后的request 对象，这样之后调用的getParameter(String str)方法自然就是装饰后的类的方法

        chain.doFilter(ew, response);

    }

    @Override
    public void destroy() {

    }
}
