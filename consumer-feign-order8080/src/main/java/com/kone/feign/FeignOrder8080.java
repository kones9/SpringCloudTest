package com.kone.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient // 该注解用于向使用consul为注册中心时注册服务
@EnableFeignClients
public class FeignOrder8080 {
    public static void main(String[] args) {
        SpringApplication.run(FeignOrder8080.class, args);
    }
}