package com.zhengjy.test.netty_in_action.collback;

/**
 * Created by zhengjy on 2017/10/27 11:53
 */
public interface Fetcher {

    void fetchData(FetcherCallback fetcherCallback);
}
