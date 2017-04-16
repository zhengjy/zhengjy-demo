package com.zhengjy.test.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * Created by zhengjy on 2017/4/3.
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println(longEvent.getValue());
    }
}
