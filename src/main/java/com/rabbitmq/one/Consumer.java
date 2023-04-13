package com.rabbitmq.one;

import com.rabbitmq.client.*;

public class Consumer {
    //������Ϣ
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        //�������ӽ�����Ϣ
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.150.100");
        factory.setUsername("admin");
        factory.setPassword("123456");
        Connection connection = factory.newConnection();

        //�����ŵ�
        Channel channel = connection.createChannel();
        //���� ������Ϣ
        DeliverCallback deliverCallback = (consumerTag,message) -> {
            System.out.println(new String(message.getBody()));
        };
        //ȡ����Ϣ�Ļص�
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println("��Ϣ���ѱ��ж�");
        };
        /**
         * ��Ϣ���Ѷ���
         * 1.�����Ǹ�����
         * 2.���ѳɹ����Ƿ�Ҫ�Զ���Ӧ
         * 3.������δ�ɹ����ѵĻص�
         * 4.������ȡ¼���ѵĻص�
         */
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);

    }
}
