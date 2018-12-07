package com.wang.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

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
           PublishThread.sleep(1L);
       }
    }

    /**
     * 发送消息
     * @throws IOException
     */
    @Test
    public void testPublish() throws IOException, TimeoutException {
        //创建链接工厂
        ConnectionFactory connFac = new ConnectionFactory();

        //默认链接的主机名,RabbitMQ-Server安装在本机，所以可以直接用127.0.0.1
        connFac.setHost("192.168.1.56");
        connFac.setPort(5672);
        connFac.setUsername("zzg");
        connFac.setPassword("123456");

        //创建链接
        Connection conn = connFac.newConnection();

        //创建信息管道
        Channel channel = conn.createChannel();

        // 创建一个名为queue01的队列，防止队列不存在
        String queueName = "queue01";

        //进行信息声明        1.队列名2.是否持久化，3是否局限与链接，4不再使用是否删除，5其他的属性
        channel.queueDeclare(queueName, false, false, false, null);
        String msg = "Hello World!";

        while (true) {

            //发送消息
            // 在RabbitMQ中，消息是不能直接发送到队列，它需要发送到交换器（exchange）中。
            // 第一参数空表示使用默认exchange，第二参数表示发送到的queue，第三参数是发送的消息是（字节数组）
            channel.basicPublish("", queueName, null, msg.getBytes());

            System.out.println("发送  message[" + msg + "] to " + queueName + " success!");
        }

//        //关闭管道
//        channel.close();
//        //关闭连接
//        conn.close();
    }

}
