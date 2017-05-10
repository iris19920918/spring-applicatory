package com.wang.disruptor.producer;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.wang.disruptor.event.LongEvent;

import java.nio.ByteBuffer;

/**
 * Created by WANGDD on 2017/5/10.
 * With version 3.0 of the Disruptor a richer Lambda-style API was added to help developers by encapsulating this complexity within the Ring Buffer, so post-3.0 the preferred approach for publishing messages is via the Event Publisher/Event Translator portion of the API. E.g.
 */
public class LongEventProducerWithTranslator {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer)
    {
        this.ringBuffer = ringBuffer;
    }

//    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR =
//            new EventTranslatorOneArg<LongEvent, ByteBuffer>()
//            {
//                public void translateTo(LongEvent event, long sequence, ByteBuffer bb)
//                {
//                    event.set(bb.getLong(0));
//                }
//            };
    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR =
            (event, sequence, bb) -> event.set(bb.getLong(0));

    public void onData(ByteBuffer bb)
    {
        ringBuffer.publishEvent(TRANSLATOR, bb);
    }
}
