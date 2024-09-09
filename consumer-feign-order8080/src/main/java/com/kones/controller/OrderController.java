package com.kones.controller;

import com.kone.model.dto.TPayDTO;
import com.kone.util.result.Result;
import com.kones.api.PayFeignApi;
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
        return payFeignApi.getPay(id);
    }

    @GetMapping("/get/info")
    public Result<?> getPayInfo() {
        return payFeignApi.getPayInfo();
    }
}
