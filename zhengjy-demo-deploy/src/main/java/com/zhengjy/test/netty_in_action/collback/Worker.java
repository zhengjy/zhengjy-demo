package com.zhengjy.test.netty_in_action.collback;

/**
 * Created by zhengjy on 2017/10/27 11:49
 */
public class Worker {

    public void doWork(){
        Fetcher fetcher = new MyFetcher(new Data(1,2));
        fetcher.fetchData(new FetcherCallback() {
            @Override
            public void onData(Data data) {
                System.out.println("Data :"+data);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("error :"+throwable.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.doWork();
    }


}
