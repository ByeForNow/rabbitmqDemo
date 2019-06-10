package com.rabbitmqDemo.publish;

import com.rabbitmq.client.*;
import com.rabbitmqDemo.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receiver1 {
    //定义交换机的名称
    static final String EXCHANGE_NAME = "exchange";
    static final String QUEUE_NAME = "queue1";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection connection = ConnectionUtil.getConn();
        //创建channel
        final Channel channel = connection.createChannel();
        //创建队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");
        channel.basicQos(1);

        //接收消息
        DefaultConsumer customer = new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //收到消息时调用
                System.out.println("消费者1收到的内容是："+new String(body));
                //确认消息
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //注册消费者,第2个参数为false时表示收到服务器后需手动告知服务器。
        channel.basicConsume(QUEUE_NAME,false,customer);

        System.out.println("消费者1启动完毕。");

    }
}
