package com.wang.disruptor.producer;

import com.lmax.disruptor.RingBuffer;
import com.wang.disruptor.event.LongEvent;

import java.nio.ByteBuffer;

/**
 * Created by WANGDD on 2017/5/10.
 * We will need a source for these events, for the sake of an example I am going to assume that the data is coming from some sort of I/O device, e.g. network or file in the form of a ByteBuffer.
 */
public class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer)
    {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb)
    {
        long sequence = ringBuffer.next();  // Grab the next sequence
        try
        {
            LongEvent event = ringBuffer.get(sequence); // Get the entry in the Disruptor
            // for the sequence
            event.set(bb.getLong(0));  // Fill with data
        }
        finally
        {
            ringBuffer.publish(sequence);
        }
    }
}
