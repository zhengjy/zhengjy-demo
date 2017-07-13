package com.zhengjy.spring.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 我们可以利用HttpServletRequestWrapper包装HttpServletRequest，用HttpServletResponseWrapper包装HttpServletResponse
 * 可以动态改变传入的参数和输入流
 *
 * Created by zhengjy on 2017/7/13.
 */
public class HttpServletRequestWrapperTest extends HttpServletRequestWrapper {
    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public HttpServletRequestWrapperTest(HttpServletRequest request) {
        super(request);
    }


    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];

        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        return (value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return (value);
    }
}
