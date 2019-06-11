package com.rabbitmqDemo.durable;

import com.rabbitmq.client.*;
import com.rabbitmqDemo.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class sender {

    final static String EXCHANGER_NAME = "durable_EXCHANGER";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConn();
        Channel channel = connection.createChannel();
        for (int i = 0; i < 10; i++) {
            //发送持久化消息
            channel.basicPublish(EXCHANGER_NAME, "", MessageProperties.PERSISTENT_TEXT_PLAIN, ("发送消息至持久化队列" + i).getBytes());
        }
        System.out.println("发送完毕。");
        //关闭连接
        channel.close();
        connection.close();


    }

}
