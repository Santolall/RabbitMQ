package com.rabbitmq.one;

import com.rabbitmq.client.*;

public class Consumer {
    //队列消息
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        //创建链接接受消息
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.150.100");
        factory.setUsername("admin");
        factory.setPassword("123456");
        Connection connection = factory.newConnection();

        //创建信道
        Channel channel = connection.createChannel();
        //声明 接受消息
        DeliverCallback deliverCallback = (consumerTag,message) -> {
            System.out.println(new String(message.getBody()));
        };
        //取消消息的回调
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println("消息消费被中断");
        };
        /**
         * 消息消费队列
         * 1.消费那个队列
         * 2.消费成功后是否要自动答应
         * 3.消费者未成功消费的回调
         * 4.消费者取录消费的回调
         */
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);

    }
}
