package com.rabbitmqDemo.publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmqDemo.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Exchanger {
    //定义交换机的名称
    static final String EXCHANGE_NAME = "exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection connection = ConnectionUtil.getConn();
        //创建channel
        Channel channel = connection.createChannel();
        //声明交换机,类型是发布订阅模式
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        System.out.println("Exchanger启动完毕。");

        channel.close();
        connection.close();
    }
}
