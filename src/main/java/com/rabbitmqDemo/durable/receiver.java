package com.rabbitmqDemo.durable;

import com.rabbitmq.client.*;
import com.rabbitmqDemo.util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class receiver {

    final static String EXCHANGER_NAME = "durable_EXCHANGER";
    //持久化队列
    private final static String QUEUE = "durable_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = ConnectionUtil.getConn();
        //创建通道
        final Channel channel = connection.createChannel();
        //接收消息
        DefaultConsumer customer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //收到消息时调用
                System.out.println("消费者1收到的内容是：" + new String(body));
                //确认消息
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        System.out.println("消费者1启动。");
        //注册消费者,第2个参数为false时表示收到服务器后需手动告知服务器。
        channel.basicConsume(QUEUE, false, customer);
        //关闭连接
//        channel.close();
//        connection.close();
    }

}
