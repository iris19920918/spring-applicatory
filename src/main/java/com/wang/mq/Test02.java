package com.wang.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by wangdd on 2018/8/7.
 */
public class Test02 {

    public static void main(String[] args) throws IOException, ShutdownSignalException,
            ConsumerCancelledException, InterruptedException, TimeoutException {

        // 创建链接工厂
        ConnectionFactory connFac = new ConnectionFactory() ;

        //默认链接的主机名,RabbitMQ-Server安装在本机，所以可以直接用127.0.0.1
        connFac.setHost("192.168.1.56");
        connFac.setPort(5672);
        connFac.setUsername("zzg");
        connFac.setPassword("123456");

        //创建链接
        Connection conn = connFac.newConnection() ;

        //创建信息管道
        Channel channel = conn.createChannel() ;

        //定义Queue名称
        String queueName = "queue01";
        //1.队列名2.是否持久化，3是否局限与链接，4不再使用是否删除，5其他的属性
        channel.queueDeclare(queueName, false, false, false, null) ;

        //上面的部分，与Test01是一样的

        //声明一个消费者,配置好获取消息的方式
        QueueingConsumer consumer = new QueueingConsumer(channel) ;
        channel.basicConsume(queueName, true, consumer) ;

        //循环获取消息
        while(true){

            //循环获取信息
            //指向下一个消息，如果没有会一直阻塞
            QueueingConsumer.Delivery delivery = consumer.nextDelivery() ;

            String msg = new String(delivery.getBody()) ;

            System.out.println("接收 message[" + msg + "] from " + queueName);
        }
    }
}
