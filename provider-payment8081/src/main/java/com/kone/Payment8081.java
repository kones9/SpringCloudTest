package com.kone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description: 8081主启动类
 * @author: Kone
 * @version: 1.0.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class Payment8081 {
    public static void main(String[] args) {
        SpringApplication.run(Payment8081.class, args);
    }
}