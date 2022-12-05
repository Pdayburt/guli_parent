package com.guli.edu.service;

import com.guli.edu.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author anatkh
* @description 针对表【t_order(订单)】的数据库操作Service
* @createDate 2022-12-03 06:00:16
*/
public interface OrderService extends IService<Order> {

    String createOrder(String courseId, String memberIdByJwtToken);
}
