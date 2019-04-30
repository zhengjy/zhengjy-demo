package com.zhengjy.rpc.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**rpc服务请求结构
 * Created by Jiyang.Zheng on 2019/1/11 11:22.
 */
public class MessageRequest implements Serializable {

    private String messageId;

    private String className;

    private String methodName;

    private Class<?>[] typeParamters;

    private Object[] paramnetersVal;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getTypeParamters() {
        return typeParamters;
    }

    public void setTypeParamters(Class<?>[] typeParamters) {
        this.typeParamters = typeParamters;
    }

    public Object[] getParamnetersVal() {
        return paramnetersVal;
    }

    public void setParamnetersVal(Object[] paramnetersVal) {
        this.paramnetersVal = paramnetersVal;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("messageId", messageId).append("className", className)
                .append("methodName", methodName).toString();
    }
}
