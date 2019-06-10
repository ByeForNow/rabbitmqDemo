package com.rabbitmqDemo.work;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmqDemo.util.ConnectionUtil;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

//生产者
public class Sender {
    //队列的名称
    private final static String QUEUENAME = "s1";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        Connection connection = ConnectionUtil.getConn();
        //创建通道
        Channel channel = connection.createChannel();
        //声明队列QUEUENAME,如果队列存在，就什么也不做；队列不存在时再创建。
        //  1、队列的名字
        //  2、是否持久化队列。数据默认是存在内存中的，断电会丢失数据；如果设置为true,则会保存到erlang自带的数据库。
        //  3、是否排外。当连接关闭后，是否会自动删除队列；是否私有当前队列，如果私有了，其他通道不可以访问这个队列。
        //  4、是否自动删除。
        //  5、其他参数
        channel.queueDeclare(QUEUENAME, false, false, false, null);
        //发送消息
        for (int i = 0; i < 100; i++) {
            channel.basicPublish("", QUEUENAME, null, ("发送的消息" + i).getBytes());
        }
        System.out.println("发送完毕。");
        //关闭连接
        channel.close();
        connection.close();
    }
}
