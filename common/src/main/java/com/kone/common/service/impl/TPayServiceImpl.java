package com.kone.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kone.common.mapper.TPayMapper;
import com.kone.common.model.entity.TPay;
import com.kone.common.service.TPayService;
import org.springframework.stereotype.Service;

/**
* @author kone
* @description 针对表【t_pay(支付交易表)】的数据库操作Service实现
* @createDate 2024-09-09 08:31:57
*/
@Service
public class TPayServiceImpl extends ServiceImpl<TPayMapper, TPay>
    implements TPayService{

}




