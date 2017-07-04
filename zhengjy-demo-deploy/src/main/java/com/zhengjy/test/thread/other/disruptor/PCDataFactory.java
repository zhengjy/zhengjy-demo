package com.zhengjy.test.thread.other.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by zhengjy on 2017/7/2.
 */
public class PCDataFactory implements EventFactory<PCData> {
    @Override
    public PCData newInstance() {
        return new PCData();
    }
}
