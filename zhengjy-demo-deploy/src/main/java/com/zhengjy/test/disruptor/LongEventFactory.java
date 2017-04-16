
package com.zhengjy.test.disruptor;

import com.lmax.disruptor.EventFactory;


/**
 * Created by zhengjy on 2017/4/3.
 */

public class LongEventFactory implements EventFactory {
    @Override
    public Object newInstance() {
        return new LongEvent();
    }
}

