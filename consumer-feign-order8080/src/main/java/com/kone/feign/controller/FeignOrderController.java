package com.kone.feign.controller;

import cn.hutool.core.date.DateUtil;
import com.kone.common.model.dto.TPayDTO;
import com.kone.common.util.result.Result;
import com.kone.common.util.result.ResultEnum;
import com.kone.feign.api.PayFeignApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 订单控制层
 * @author: Kone
 * @version: 1.0.0
 **/
@RestController
@RequestMapping("/feign/pay")
public class FeignOrderController {

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
}
