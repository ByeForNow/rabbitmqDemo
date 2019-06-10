package com.rabbitmqDemo.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//用于连接的connect类，增加备注
public class ConnectionUtil {

    public static Connection getConn() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        //设置地址
        factory.setHost("localhost");

        //设置服务器端端口
        factory.setPort(5672);

        //设置用户名和密码
        factory.setUsername("admin");
        factory.setPassword("123456");

        //设置虚拟主机
        factory.setVirtualHost("adminHost");

        //创建一个新的链接
        return factory.newConnection();
    }

}
