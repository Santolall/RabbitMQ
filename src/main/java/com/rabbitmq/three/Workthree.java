package com.rabbitmq.three;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.utils.SleepUtils;

/**
 * ��Ϣ���ֶ�Ӧ��ʱ�ǲ���ʧ���Ż���Ϣ�Զ�������������
 */
public class Workthree {
    public static final String TASK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        System.out.println("C2�ȴ�������Ϣ����ʱ��ϳ�");

        DeliverCallback deliverCallback = (var1,var2) ->{
            //��˯1S
            SleepUtils.sleep(30);
            System.out.println("���ܵ�����Ϣ��" + new String(var2.getBody(),"UTF-8"));

            //�ֶ�Ӧ��
            /**
             * 1.��Ϣ�ı�ʶ tag
             * 2.�Ƿ�����Ӧ��
             */
            channel.basicAck(var2.getEnvelope().getDeliveryTag(),false);
        };
        //���ò���ƽ�ַ�
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

        //�����ֶ�Ӧ��
        boolean aotuAck = false;
        channel.basicConsume(TASK_QUEUE_NAME,aotuAck,deliverCallback,(var1 -> {
            System.out.println(var1 + "������ȡ�����ѽӿ��߼�");
        }));
    }
}
