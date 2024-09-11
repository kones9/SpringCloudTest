package com.kone.payment.controller;

import cn.hutool.core.util.IdUtil;
import com.kone.common.util.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @description: 服务熔断控制层
 * @author: Kone
 * @version: 1.0.0
 **/
@RestController
public class CircuitController {
    /**
     * 服务熔断测试
     *
     * @param id 接受id
     * @return 响应信息
     */
    @GetMapping("/circuit/{id}")
    public Result<String> testCircuitBreaker(@PathVariable("id") Integer id) {
        if (id == -4) throw new RuntimeException("----circuit id 不能负数");
        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return Result.success("Hello, circuit! inputId:  " + id + " \t " + IdUtil.simpleUUID());
    }

    /**
     * 隔离测试
     *
     * @param id 接受id
     * @return 响应信息
     */
    @GetMapping("/bulkhead/{id}")
    public Result<String> testBulkHead(@PathVariable("id") Integer id) {
        if (id == -4) throw new RuntimeException("----bulkhead id 不能负数");
        if (id == 10) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return Result.success("Hello, bulkhead! inputId:  " + id + " \t " + IdUtil.simpleUUID());
    }

    /**
     * ReteLimiter限流
     *
     * @param id 接受id
     * @return 响应信息
     */
    @GetMapping(value = "/ratelimiter/{id}")
    public Result<String> testRateLimiter(@PathVariable("id") Integer id) {
        return Result.success("Hello, testRateLimiter欢迎到来 inputId:  " + id + " \t " + IdUtil.simpleUUID());
    }
}
