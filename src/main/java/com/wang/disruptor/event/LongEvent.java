package com.wang.disruptor.event;

/**
 * Created by WANGDD on 2017/5/10.
 * To get started with the Disruptor we are going to consider very simple and contrived example, one that will pass a single long value from a producer to a consumer, where the consumer will simply print out the value. Firstly we will define the Event that will carry the data.
 */
public class LongEvent {

    private long value;

    public void set(long value)
    {
        this.value = value;
    }
}
