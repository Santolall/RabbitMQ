package com.rabbitmq.three;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmq.utils.RabbitMqUtils;

import java.util.Scanner;

/**
 * ��Ϣ���ֶ�Ӧ��ʱ�ǲ���ʧ���Ż���Ϣ�Զ�������������
 */
public class Tasktwo {
    public static final String TASK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        //��������
        boolean durable = true;//��Ҫ����Ϣ���г־û�
        channel.queueDeclare(TASK_QUEUE_NAME,durable,false,false,null);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            //������Ϣ
            //MessageProperties.PERSISTENT_TEXT_PLAIN�����������߷�����ϢΪ�־û���Ϣ��Ҫ�󱣴��ڴ����ϣ�
            // ��δ����һ�㱣�������ڴ��в����ǳ־û�
            channel.basicPublish("",TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes("UTF-8"));
            System.out.println("�����߷�����Ϣ��" + message);
        }
    }
}
