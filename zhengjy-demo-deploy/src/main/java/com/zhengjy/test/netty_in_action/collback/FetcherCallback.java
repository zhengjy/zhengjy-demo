package com.zhengjy.test.netty_in_action.collback;

/**
 * Created by zhengjy on 2017/10/27 11:52
 */
public interface FetcherCallback {
    void onData(Data data);

    void onError(Throwable throwable);
}
