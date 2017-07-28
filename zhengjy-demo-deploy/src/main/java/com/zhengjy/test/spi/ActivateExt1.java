package com.zhengjy.test.spi;

/**
 * Created by zhengjy on 2017/7/26.
 */
@SPI("impl1")
public interface ActivateExt1 {
    String echo(String msg);
}
@Activate(group = {"default_group"})
public class ActivateExt1Impl1 implements ActivateExt1 {
    public String echo(String msg) {
        return msg;
    }
}
@Activate(group = {"group1", "group2"})
public class GroupActivateExtImpl implements ActivateExt1 {

    public String echo(String msg) {
        return msg;
    }
}
@Activate(order = 1, group = {"order"})
public class OrderActivateExtImpl1 implements ActivateExt1 {

    public String echo(String msg) {
        return msg;
    }
}
@Activate(order = 2, group = {"order"})
public class OrderActivateExtImpl2 implements ActivateExt1 {

    public String echo(String msg) {
        return msg;
    }
}
@Activate(value = {"value"}, group = {"value"})
public class ValueActivateExtImpl implements ActivateExt1 {

    public String echo(String msg) {
        return msg;
    }
}
