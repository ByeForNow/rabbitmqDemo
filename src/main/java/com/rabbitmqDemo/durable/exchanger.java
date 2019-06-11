package com.rabbitmqDemo.durable;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmqDemo.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class exchanger {

    final static String EXCHANGER_NAME = "durable_EXCHANGER";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection connection = ConnectionUtil.getConn();
        //创建通道
        Channel channel = connection.createChannel();
        //创建持久化exchanger
        channel.exchangeDeclare(EXCHANGER_NAME,"fanout",true);

        System.out.println("持久化Exchanger启动完毕。");
        channel.close();
        connection.close();
    }

}
