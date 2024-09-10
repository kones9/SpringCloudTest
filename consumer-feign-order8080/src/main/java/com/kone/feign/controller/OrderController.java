package com.kone.feign.controller;

import cn.hutool.core.date.DateUtil;
import com.kone.common.model.dto.TPayDTO;
import com.kone.common.util.result.Result;
import com.kone.common.util.result.ResultEnum;
import com.kone.feign.api.PayFeignApi;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 订单控制层
 * @author: Kone
 * @version: 1.0.0
 **/
@RestController
@RequestMapping("/feign/pay")
public class OrderController {

    private PayFeignApi payFeignApi;

    @Autowired
    public void setPayFeignApi(PayFeignApi payFeignApi) {
        this.payFeignApi = payFeignApi;
    }

    @PostMapping("/add")
    public Result<?> addPay(@RequestBody TPayDTO tPayDTO) {
        return payFeignApi.addPay(tPayDTO);
    }

    @GetMapping("/delete/{id}")
    public Result<?> deletePay(@PathVariable("id") String id) {
        return payFeignApi.deletePay(id);
    }

    @PostMapping("/update")
    public Result<?> updatePay(@RequestBody TPayDTO tPayDTO) {
        return payFeignApi.updatePay(tPayDTO);
    }

    @GetMapping("/get/{id}")
    public Result<?> getPay(@PathVariable("id") String id) {
        try {
            System.out.println("调用开始----" + DateUtil.now());
            return payFeignApi.getPay(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用结束----" + DateUtil.now());
            return Result.fail(ResultEnum.SYSTEM_ERROR, e.getMessage());
        }
    }

    @GetMapping("/get/info")
    public Result<?> getPayInfo() {
        return payFeignApi.getPayInfo();
    }

    /**
     * 服务熔断测试
     *
     * @param id 输入id
     * @return 响应内容
     */
    @GetMapping("/circuit/get/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    public Result<?> myCircuit(@PathVariable("id") Integer id) {
        return payFeignApi.myCircuit(id);
    }

    /**
     * myCircuitFallback就是服务降级后的兜底处理方法
     * 返回值应和断路器请求方法一致，否则报错，找不到方法
     *
     * @param id 请求参数
     * @param t  错误信息
     * @return 响应结果
     */
    public Result<?> myCircuitFallback(Integer id, Throwable t) {
        // 这里是容错处理逻辑，返回备用结果
        return Result.fail(ResultEnum.SYSTEM_ERROR, "myCircuitFallback，系统繁忙，请稍后再试-----/(ㄒoㄒ)/" + t.getMessage());
    }
}
