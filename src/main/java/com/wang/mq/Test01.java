package com.wang.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangdd on 2018/8/7.
 * 发送消息
 */
public class Test01 {

    private static final Logger logger = LoggerFactory.getLogger(Test01.class);

    public static void main(String[] args) throws InterruptedException {

       while (true) {
           PublishThread thread = new PublishThread();
           logger.info(thread.getName() + "----");
           thread.start();
           PublishThread.sleep(100L);
       }
    }

}
