package com.kone.feign.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: feign配置
 * @author: Kone
 * @version: 1.0.0
 **/
@Configuration
public class FeignConfig {
    /**
     * 配置feign超时重试机制
     * 最大请求次数为3(1+2)，初始间隔时间为100ms，重试间最大间隔时间为1s
     *
     * @return feign配置
     */
    @Bean
    public Retryer retryer() {
        return Retryer.NEVER_RETRY;
        // return new Retryer.Default(100, 1, 3);
    }
}
