package com.rabbitmqDemo.publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmqDemo.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {
    //定义交换机的名称
    static final String EXCHANGE_NAME = "exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection connection = ConnectionUtil.getConn();
        //创建channel
        Channel channel = connection.createChannel();

        String msg = "发布订阅模式";
        //消息是先发布到交换机中，而交换机是没有保存功能的，如果没有消费者，消息就会丢失。
        channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());

        System.out.println("消息发送完毕");

        channel.close();
        connection.close();
    }

}
