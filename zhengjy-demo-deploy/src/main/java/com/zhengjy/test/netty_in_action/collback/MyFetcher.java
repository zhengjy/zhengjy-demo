package com.zhengjy.test.netty_in_action.collback;

/**
 * Created by zhengjy on 2017/10/27 11:54
 */
public class MyFetcher implements Fetcher {
    final Data data;

    public MyFetcher(Data data) {
        this.data = data;
    }

    @Override
    public void fetchData(FetcherCallback fetcherCallback) {
        try {
            fetcherCallback.onData(data);
        }catch (Exception e){
            fetcherCallback.onError(e);
        }
    }
}
