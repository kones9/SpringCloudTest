package com.kone.util.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 通用返回类
 * @author: Kone
 * @version: 1.0.0
 **/
@Data
public class Result<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    // 默认构造函数
    public Result() {
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), data);
    }

    public static <T> Result<T> success() {
        return new Result<T>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), null);
    }

    public static <T> Result<T> fail(ResultEnum resultEnum) {
        return new Result<T>(resultEnum.getCode(), resultEnum.getMsg(), null);
    }

    public static <T> Result<T> fail(Integer code, String msg) {
        return new Result<T>(code, msg, null);
    }
}
