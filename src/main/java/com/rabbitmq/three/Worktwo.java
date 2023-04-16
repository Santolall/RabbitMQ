package com.rabbitmq.three;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.utils.SleepUtils;

/**
 * 消息在手动应答时是不丢失，放回消息对队列中重新消费
 */
public class Worktwo {
    public static final String TASK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        System.out.println("C1等待接收消息处理时间较短");

        DeliverCallback deliverCallback = (var1,var2) ->{
            //沉睡1S
            SleepUtils.sleep(1);
            System.out.println("接受到的消息：" + new String(var2.getBody(),"UTF-8"));
            //手动应答
            /**
             * 1.消息的标识 tag
             * 2.是否批量应答
             */
            channel.basicAck(var2.getEnvelope().getDeliveryTag(),false);
        };

        //采用手动应答
        boolean aotuAck = false;
        channel.basicConsume(TASK_QUEUE_NAME,aotuAck,deliverCallback,(var1 -> {
            System.out.println(var1 + "消费者取消消费接口逻辑");
        }));
    }
}
