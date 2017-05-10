package com.wang.disruptor.event;

import com.lmax.disruptor.EventFactory;

/**
 * Created by WANGDD on 2017/5/10.
 * In order to allow the Disruptor to preallocate these events for us, we need to an EventFactory that will perform the construction
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
