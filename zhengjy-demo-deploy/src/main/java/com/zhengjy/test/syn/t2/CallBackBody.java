package com.zhengjy.test.syn.t2;

/**
 * Created by zhengjy on 2017/3/27.
 */
public abstract class CallBackBody {

    void onSuccess(Object context) {
        System.out.println("onSuccess");
    }

    void onFailure(Object context) {
        System.out.println("onFailure");
    }

    abstract void execute(Object context) throws Exception;
}
