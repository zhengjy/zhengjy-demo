package com.zhengjy.spring.other;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ServletRegistrationBean;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by zhengjy on 2017/7/3.
 */
public class FiterTest {
    public ServletRegistrationBean servletRegistrationBean(){

        return  new ServletRegistrationBean(new ServerTest(), "/xx/*");
    }
}

class ServerTest implements Servlet{

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
