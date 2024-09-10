package com.kone.payment.controller;

import cn.hutool.core.util.IdUtil;
import com.kone.common.util.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.kone.common.model.entity.TPay;
import com.kone.common.service.TPayService;

import java.util.concurrent.TimeUnit;

/**
 * @description: 订单控制层
 * @author: Kone
 * @version: 1.0.0
 **/
@RestController
@RequestMapping("/pay")
public class PayController {
    private TPayService tPayService;

    @Autowired
    public void setPayService(TPayService tPayService) {
        this.tPayService = tPayService;
    }

    @PostMapping("/add")
    public Result<Void> addPay(@RequestBody TPay tPay) {
        tPayService.save(tPay);
        return Result.success();
    }

    @GetMapping("/delete/{id}")
    public Result<Void> deletePay(@PathVariable("id") Integer id) {
        tPayService.removeById(id);
        return Result.success();
    }

    @PostMapping("/update")
    public Result<Void> updatePay(@RequestBody TPay tPay) {
        tPayService.updateById(tPay);
        return Result.success();
    }

    @GetMapping("/get/{id}")
    public Result<TPay> getPay(@PathVariable("id") Integer id) {
        try {
            TimeUnit.SECONDS.sleep(62);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success(tPayService.getById(id));
    }

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/get/info")
    private Result<String> getInfoByConsul(@Value("${kone.info}") String koneInfo) {
        return Result.success("koneInfo: " + koneInfo + "\t" + "port: " + port);
    }

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
}
