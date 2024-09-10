package com.kone.common.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * TPayDTO
 */
@Data
public class TPayDTO implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     * 支付流水号
     */
    private String payNo;

    /**
     * 订单流水号
     */
    private String orderNo;

    /**
     * 用户账号ID
     */
    private Integer userId;

    /**
     * 交易金额
     */
    private BigDecimal amount;

    private static final long serialVersionUID = 1L;
}