package com.rabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/*
����Ϊ���ӹ��������ŵ��Ĺ�����
 */
public class RabbitMqUtils {
    public static Channel getChannel() throws Exception {
        //�������ӹ���
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.150.100");
        factory.setUsername("admin");
        factory.setPassword("123456");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        return channel;
    }

}
