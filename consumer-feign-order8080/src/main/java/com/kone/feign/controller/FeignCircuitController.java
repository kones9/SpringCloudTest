package com.kone.feign.controller;

import com.kone.common.util.result.Result;
import com.kone.common.util.result.ResultEnum;
import com.kone.feign.api.PayFeignApi;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * @description: 断路器客服端
 * @author: Kone
 * @version: 1.0.0
 **/
@RestController
@RequestMapping("/feign")
public class FeignCircuitController {

    private PayFeignApi payFeignApi;

    @Autowired
    public void setPayFeignApi(PayFeignApi payFeignApi) {
        this.payFeignApi = payFeignApi;
    }

    /**
     * 服务熔断测试
     *
     * @param id 输入id
     * @return 响应内容
     */
    @GetMapping("/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "testCircuitBreakerFallback")
    public Result<?> testCircuitBreaker(@PathVariable("id") Integer id) {
        return payFeignApi.testCircuitBreaker(id);
    }

    /**
     * myCircuitFallback就是服务降级后的兜底处理方法
     * 返回值应和断路器请求方法一致，否则报错，找不到方法
     *
     * @param id 请求参数
     * @param t  错误信息
     * @return 响应结果
     */
    public Result<?> testCircuitBreakerFallback(Integer id, Throwable t) {
        // 这里是容错处理逻辑，返回备用结果
        return Result.fail(ResultEnum.SYSTEM_ERROR, "myCircuitFallback，系统繁忙，请稍后再试-----/(ㄒoㄒ)/" + t.getMessage());
    }

    /**
     * 隔离测试（基于信号标）
     *
     * @param id 输入id
     * @return 响应内容
     */
    @GetMapping("/bulkhead/get/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "testBulkHeadFallback", type = Bulkhead.Type.SEMAPHORE)
    public Result<?> testBulkHeadSemaphore(@PathVariable("id") Integer id) {
        return payFeignApi.testBulkHead(id);
    }

    /**
     * testBulkHeadSemaphoreFallback就是服务降级后的兜底处理方法
     * 返回值应和断路器请求方法一致，否则报错，找不到方法
     *
     * @param id 请求参数
     * @param t  错误信息
     * @return 响应结果
     */
    public Result<?> testBulkHeadSemaphoreFallback(Integer id, Throwable t) {
        // 这里是容错处理逻辑，返回备用结果
        return Result.fail(ResultEnum.SYSTEM_ERROR, "myBulkheadFallback，隔板超出最大数量限制，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~" + t.getMessage());
    }

    /**
     * 隔离测试（基于线程THREADPOOL）
     *
     * @param id 请求参数
     * @return 响应结果
     */
    @GetMapping(value = "/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "testBulkHeadThreadPoolFallback", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<Result<?>> testBulkHeadThreadPool(@PathVariable("id") Integer id) {
        return CompletableFuture.supplyAsync(() -> payFeignApi.testBulkHead(id));
    }

    /**
     * testBulkHeadThreadPoolFallback就是服务降级后的兜底处理方法
     * 返回值应和断路器请求方法一致，否则报错，找不到方法
     *
     * @param id 请求参数
     * @param t  错误信息
     * @return 响应结果
     */
    public CompletableFuture<Result<?>> testBulkHeadThreadPoolFallback(Integer id, Throwable t) {
        return CompletableFuture.supplyAsync(() -> Result.fail(ResultEnum.SYSTEM_BUSY));
    }

    @GetMapping(value = "/ratelimiter/{id}")
    @RateLimiter(name = "cloud-payment-service", fallbackMethod = "testRateLimiterFallback")
    public Result<?> testRateLimiter(@PathVariable("id") Integer id) {
        return payFeignApi.testRateLimiter(id);
    }

    public Result<?> testRateLimiterFallback(Integer id, Throwable t) {
        return Result.fail(ResultEnum.SYSTEM_ERROR, "你被限流了，禁止访问/(ㄒoㄒ)/~~");
    }
}
