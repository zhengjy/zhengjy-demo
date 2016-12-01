package com.zhengjy.test.design.demo.proxy;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Administrator on 2016/11/20.
 */
public interface MyRemote extends Remote {
    public String sayHello() throws RemoteException;
}
