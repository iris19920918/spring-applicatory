package com.wang.disruptor.handler;

import com.lmax.disruptor.EventHandler;
import com.wang.disruptor.event.ObjectEvent;

/**
 * Created by WANGDD on 2017/5/10.
 *
 */
public class ClearingEventHandler<T> implements EventHandler<ObjectEvent<T>> {
    @Override
    public void onEvent(ObjectEvent<T> event, long sequence, boolean endOfBatch) throws Exception {
        // Failing to call clear here will result in the
        // object associated with the event to live until
        // it is overwritten once the ring buffer has wrapped
        // around to the beginning.
        event.clear();
    }

}
