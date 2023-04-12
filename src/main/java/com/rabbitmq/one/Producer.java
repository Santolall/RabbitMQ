package com.rabbitmq.one;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    public static final String QUEUE_NAME="hello";

    //发消息
    public static void main(String[] args) throws Exception {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //连接ip
        factory.setHost("192.168.150.100");
        factory.setUsername("admin");
        factory.setPassword("123456");

        //创建连接
        Connection connection = factory.newConnection();
        //获取信道
        Channel channel = connection.createChannel();
        /**
         * 生成一个队列
         * 1.队列名称
         * 2.队列里面的消息是否持久化（磁盘） 默认情况消息存储在内存中
         * 3.该队列是否只提供一个消费者进行消费 是否进行消息共享，true可以进行多个消费者消费，false只能一个消费者消费
         * 4.是否自动删除 最后一个消费者发完消息后该队列是否自动删除
         * 5.其他参数
         */
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        String message = "hello world";//初次使用

        /**
         * 发送一个消费
         * 1.发送到那个交换机
         * 2.路由的key值是那个，本次是队列的名称
         * 3.其他参数值
         * 4.发送消息的消息体
         */
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println("消息发送完毕");
    }
}
