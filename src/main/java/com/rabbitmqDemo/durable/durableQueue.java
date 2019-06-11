package com.rabbitmqDemo.durable;

import com.rabbitmq.client.*;
import com.rabbitmqDemo.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class durableQueue {

    final static String EXCHANGER_NAME = "durable_EXCHANGER";
    //队列的名称
    private final static String QUEUE = "durable_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = ConnectionUtil.getConn();
        //创建通道
        final Channel channel = connection.createChannel();
        //创建队列
        channel.queueDeclare(QUEUE, true, false, false, null);
        //绑定到exchanger上
        channel.queueBind(QUEUE,EXCHANGER_NAME,"");
        //关闭连接
        channel.close();
        connection.close();
    }

}
