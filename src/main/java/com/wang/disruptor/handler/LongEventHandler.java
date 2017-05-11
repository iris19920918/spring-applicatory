package com.wang.disruptor.handler;

import com.lmax.disruptor.EventHandler;
import com.wang.disruptor.event.LongEvent;

/**
 * Created by WANGDD on 2017/5/10.
 * Once we have the event defined we need to create a consumer that will handle these events. In our case all we want to do is print the value out the the console.
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Event：" + longEvent + ";sequence：" + sequence + ";endOfBatch：" + endOfBatch);
    }
}
