package com.rabbitmq.two;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.utils.RabbitMqUtils;

public class Workerone {
    public static final String QUNUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        DeliverCallback deliverCallback = (var1,var2) ->{
            System.out.println("���ܵ�����Ϣ��" + new String(var2.getBody()));
        };

        CancelCallback cancelCallback = (var1) ->{
            System.out.println(var1 + "��Ϣ��ȡ�����ѽӿڻص��߼�");
        };

        System.out.println("C2�ȴ�������Ϣ");
        //��Ϣ�Ľ���
        channel.basicConsume(QUNUE_NAME,true,deliverCallback,cancelCallback);
    }
}
