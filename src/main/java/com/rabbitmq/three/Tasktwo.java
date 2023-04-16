package com.rabbitmq.three;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmq.utils.RabbitMqUtils;

import java.util.Scanner;

/**
 * 消息在手动应答时是不丢失，放回消息对队列中重新消费
 */
public class Tasktwo {
    public static final String TASK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //声明队列
        boolean durable = true;//需要让消息进行持久化
        channel.queueDeclare(TASK_QUEUE_NAME,durable,false,false,null);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            //发送消息
            //MessageProperties.PERSISTENT_TEXT_PLAIN：设置生产者发送消息为持久化消息（要求保存在磁盘上）
            // 如未设置一般保存在内内存中并不是持久化
            channel.basicPublish("",TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes("UTF-8"));
            System.out.println("生产者发出消息：" + message);
        }
    }
}
