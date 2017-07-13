package com.zhengjy.spring.filter;

 import org.springframework.stereotype.Component;

 import java.io.IOException;

 import java.util.zip.GZIPOutputStream;

 import javax.servlet.Filter;

 import javax.servlet.FilterChain;

 import javax.servlet.FilterConfig;

 import javax.servlet.ServletException;

 import javax.servlet.ServletRequest;

 import javax.servlet.ServletResponse;

 import javax.servlet.http.HttpServletRequest;

 import javax.servlet.http.HttpServletResponse;
/**
 * Created by zhengjy on 2017/7/13.
 */
@Component
public class CompressionFilter implements Filter {

    @Override
    public void destroy() {
        //TODO Auto-generated method stub
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        HttpServletResponse res = (HttpServletResponse) response;

        String encodings = req.getHeader("accept-encoding");

        //判断浏览器是否支持gzip输出
        if((encodings != null) && (encodings.indexOf("gzip") > -1)){
            // 创建装饰后的resposne对象
            CompressionWrapper responseWrapper =new CompressionWrapper(res);
            responseWrapper.setHeader("content-encoding","gzip");

            // 这里传递的是装饰后的Response对象，所以printwrite 使用的是压缩输出
            // 使用过滤器就是将所有的响应在到大浏览器之前将原来的Response 替换为装饰后的Response对象
            chain.doFilter(request,responseWrapper);
            GZIPOutputStream gzipOutputStream = responseWrapper.getGZIPOutputStream();
            if(gzipOutputStream != null) {
                gzipOutputStream.finish();
            }
        }else{
            chain.doFilter(request,response);
        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
}
