package com.rabbitmqDemo.route;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmqDemo.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {

    final static String EXCHANGER_NAME = "EXCCHANGER_ROUTE";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection connection = ConnectionUtil.getConn();
        //创建通道
        Channel channel = connection.createChannel();
        //发送带key的消息
        channel.basicPublish(EXCHANGER_NAME, "key1",null,"路由key1消息".getBytes());
        //发送带key的消息
        channel.basicPublish(EXCHANGER_NAME, "key1",null,"路由key1消息啊".getBytes());
        //发送带key的消息
        channel.basicPublish(EXCHANGER_NAME, "key2",null,"路由key2消息".getBytes());

        System.out.println("带key的消息发送完毕。");
        //关闭连接
        channel.close();
        connection.close();
    }

}
