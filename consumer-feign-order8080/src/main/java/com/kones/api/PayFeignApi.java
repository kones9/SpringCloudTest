package com.kones.api;

import com.kone.model.dto.TPayDTO;
import com.kone.util.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description: api接口
 * @author: Kone
 * @version: 1.0.0
 **/
@FeignClient("cloud-payment-service")
public interface PayFeignApi {
    @PostMapping("/pay/add")
    Result<?> addPay(@RequestBody TPayDTO tPayDTO);

    @GetMapping("/pay/delete/{id}")
    Result<?> deletePay(@PathVariable("id") String id);

    @PostMapping("/pay/update")
    Result<?> updatePay(@RequestBody TPayDTO tPayDTO);

    @GetMapping("/pay/get/{id}")
    Result<?> getPay(@PathVariable("id") String id);

    @GetMapping("/pay/get/info")
    Result<?> getPayInfo();
}
