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
            System.out.println("接受到的消息：" + new String(var2.getBody()));
        };

        CancelCallback cancelCallback = (var1) ->{
            System.out.println(var1 + "消息被取消消费接口回调逻辑");
        };

        System.out.println("C2等待接受消息");
        //消息的接收
        channel.basicConsume(QUNUE_NAME,true,deliverCallback,cancelCallback);
    }
}
