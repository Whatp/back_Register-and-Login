package com.example.management_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 以Dept为例，前端发送请求会请求到controller的方法，controller会调用service来获取数据，
 * service调用mapper中的list()方法获取全部数据
 */

@SpringBootApplication
public class ManagementWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementWebApplication.class, args);
    }

}
