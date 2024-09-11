package com.kone.payment.controller;

import cn.hutool.core.util.IdUtil;
import com.kone.common.util.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 链路监控控制层
 * @author: Kone
 * @version: 1.0.0
 **/
@RestController
public class MicrometerController {
    /**
     * Micrometer(Sleuth)进行链路监控的例子
     *
     * @param id 接受id
     * @return 响应数据
     */
    @GetMapping(value = "/micrometer/{id}")
    public Result<String> testMicrometer(@PathVariable("id") Integer id) {
        return Result.success("Hello, 欢迎到来myMicrometer inputId:  " + id + " \t    服务返回:" + IdUtil.simpleUUID());
    }
}
