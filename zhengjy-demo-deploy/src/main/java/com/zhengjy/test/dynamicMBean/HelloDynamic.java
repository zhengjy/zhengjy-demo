package com.zhengjy.test.dynamicMBean;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Constructor;
/**
 * Created by zhengjy on 2017/5/16.
 */
public class HelloDynamic implements DynamicMBean {

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {

        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName helloName = new ObjectName("MyMBean:name=helloDynamic");
        HelloDynamic hello = new HelloDynamic();
        server.registerMBean(hello,helloName);
    }

    private MBeanInfo mBeanInfo = null;
    private String className;
    private String description;
    private MBeanAttributeInfo[] attributes;
    private MBeanConstructorInfo[] constructors;
    private MBeanOperationInfo[] operations;
    MBeanNotificationInfo[] mBeanNotificationInfoArray;

    private void init(){
        className = this.getClass().getName();
        description = "Simple implementation of a dynamic MBean.";
        attributes = new MBeanAttributeInfo[1];
        constructors = new MBeanConstructorInfo[1];
        operations = new MBeanOperationInfo[1];
        mBeanNotificationInfoArray = new MBeanNotificationInfo[0];
    }

    private void buildDynamicMBean(){
        Constructor[] thisConstructors = this.getClass().getConstructors();
        constructors[0] = new MBeanConstructorInfo("HelloDynamic(): Constructs a HelloDynamic Object",thisConstructors[0]);

        attributes[0] = new MBeanAttributeInfo("name","java.lang.String","Name:name string.",true,true,false);

        MBeanParameterInfo[] params = null;
        operations[0] = new MBeanOperationInfo("print","print():print the name",params,"void",MBeanOperationInfo.INFO);
        mBeanInfo = new MBeanInfo(className,description,attributes,constructors,operations,mBeanNotificationInfoArray);
    }


    private void dynamicAddOperation(){
        init();
        operations = new MBeanOperationInfo[2];
        buildDynamicMBean();
        operations[1] = new MBeanOperationInfo("print1","print1():print the name",null,"void",MBeanOperationInfo.INFO);
        mBeanInfo = new MBeanInfo(className,description,attributes,constructors,operations,mBeanNotificationInfoArray);
    }


    public HelloDynamic(){
        init();
        buildDynamicMBean();
    }



    @Override
    public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
        return null;
    }

    @Override
    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {

    }

    @Override
    public AttributeList getAttributes(String[] attributes) {
        return null;
    }

    @Override
    public AttributeList setAttributes(AttributeList attributes) {
        return null;
    }

    @Override
    public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
        return null;
    }

    @Override
    public MBeanInfo getMBeanInfo() {
        return null;
    }
}
