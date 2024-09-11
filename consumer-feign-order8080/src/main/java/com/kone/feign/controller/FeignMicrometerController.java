package com.kone.feign.controller;

import com.kone.feign.api.PayFeignApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 链路追踪测试
 * @author: Kone
 * @version: 1.0.0
 **/
@RestController
@RequestMapping("/feign")
public class FeignMicrometerController {
    private PayFeignApi payFeignApi;

    @Autowired
    public void setPayFeignApi(PayFeignApi payFeignApi) {
        this.payFeignApi = payFeignApi;
    }

    /**
     * 链路追踪测试
     *
     * @param id 接受id
     * @return 响应数据
     */
    @GetMapping(value = "/micrometer/{id}")
    public String testMicrometer(@PathVariable("id") Integer id) {
        return payFeignApi.testMicrometer(id);
    }
}
