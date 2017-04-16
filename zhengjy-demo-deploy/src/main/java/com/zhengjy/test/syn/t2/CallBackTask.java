package com.zhengjy.test.syn.t2;

/**
 * Created by zhengjy on 2017/3/27.
 */
public class CallBackTask {
    private CallBackBody body;

    public CallBackTask(CallBackBody body) {
        this.body = body;
    }

    protected void start(final Object context) {
        final Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    body.execute(context);
                } catch (Exception e) {
                    e.printStackTrace();
                    body.onFailure(context);
                }
                body.onSuccess(context);
            }
        });
        t.start();
    }
}
