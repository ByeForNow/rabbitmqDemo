package com.rabbitmqDemo.work;

import com.rabbitmq.client.*;
import com.rabbitmqDemo.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receiver2 {

    //队列的名称
    private  final static String QUEUE = "testWork";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = ConnectionUtil.getConn();
        //创建通道
        final Channel channel = connection.createChannel();
        //创建队列
        channel.queueDeclare(QUEUE, false, false, false, null);
        //接收消息
        DefaultConsumer customer = new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //收到消息时调用
                System.out.println("消费者2收到的内容是："+new String(body));
                //确认消息
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //注册消费者,第2个参数为false时表示收到服务器后需手动告知服务器。
        channel.basicConsume(QUEUE,false,customer);
    }

}
