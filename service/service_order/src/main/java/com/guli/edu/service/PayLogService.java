package com.guli.edu.service;

import com.guli.edu.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author anatkh
* @description 针对表【t_pay_log(支付日志表)】的数据库操作Service
* @createDate 2022-12-03 06:00:50
*/
public interface PayLogService extends IService<PayLog> {

    Map createNative(String orderNo);

    Map queryPayStatus(String orderNo);

    void updateOrdersStatus(Map map);
}
