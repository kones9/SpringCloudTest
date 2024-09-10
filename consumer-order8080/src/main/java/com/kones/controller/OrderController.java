package com.kones.controller;

import com.kone.common.model.dto.TPayDTO;
import com.kone.common.util.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @description: 订单控制层
 * @author: Kone
 * @version: 1.0.0
 **/
@RestController
@RequestMapping("/consumer/pay")
public class OrderController {
    public static final String LOCALHOST_URL = "http://cloud-payment-service";// 服务注册中心上的微服务名称

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/add")
    public Result<?> addPay(@RequestBody TPayDTO tPayDTO) {
        return restTemplate.postForObject(LOCALHOST_URL + "/pay/add", tPayDTO, Result.class);
    }

    @GetMapping("/delete/{id}")
    public Result<?> deletePay(@PathVariable("id") String id) {
        return restTemplate.getForObject(LOCALHOST_URL + "/pay/delete/{id}", Result.class, id);
    }

    @PostMapping("/update")
    public Result<?> updatePay(@RequestBody TPayDTO tPayDTO) {
        return restTemplate.postForObject(LOCALHOST_URL + "/pay/update", tPayDTO, Result.class);
    }

    @GetMapping("/get/{id}")
    public Result<?> getPay(@PathVariable("id") String id) {
        return restTemplate.getForObject(LOCALHOST_URL + "/pay/get/{id}", Result.class, id);
    }

    @GetMapping("/get/info")
    public Result<?> getPayInfo() {
        return restTemplate.getForObject(LOCALHOST_URL + "/pay/get/info", Result.class);
    }
}
