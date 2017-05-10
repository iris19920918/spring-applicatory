package com.wang.disruptor.event;

/**
 * Created by WANGDD on 2017/5/10.
 * Clearing Objects From the Ring Buffer
 */
public class ObjectEvent<T> {

    T val;

    public void clear()
    {
        val = null;
    }
}
