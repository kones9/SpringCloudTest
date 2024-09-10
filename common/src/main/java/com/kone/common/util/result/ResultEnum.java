package com.kone.common.util.result;

import lombok.Getter;

/**
 * @description: 枚举类
 * @author: Kone
 * @version: 1.0.0
 **/

@Getter
public enum ResultEnum {
    SUCCESS(2000, "success"),
    SYSTEM_ERROR(5000, "system error");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
