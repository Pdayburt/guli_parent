package com.guli.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guli.edu.commonUtils.JwtUtil;
import com.guli.edu.commonUtils.RMap;
import com.guli.edu.entity.Order;
import com.guli.edu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("eduorder/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("createOrder/{courseId}")
    public RMap createOrder(@PathVariable String courseId, HttpServletRequest request){
        String orderNo = orderService.createOrder(courseId, JwtUtil.getMemberIdByJwtToken(request));
        return RMap.ok().data("orderId",orderNo);
    }

    @GetMapping("getOrderInfo/{orderId}")
    public RMap getOrderInfo(@PathVariable String orderId){
        LambdaQueryWrapper<Order> orderLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderLambdaQueryWrapper
                .eq(Order::getOrderNo,orderId);
        Order order = orderService.getOne(orderLambdaQueryWrapper);
        return RMap.ok().data("item",order);
    }

    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public Boolean isBuyCourse(@PathVariable String courseId,@PathVariable String memberId){
        LambdaQueryWrapper<Order> orderLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderLambdaQueryWrapper
                .eq(Order::getCourseId,courseId)
                .eq(Order::getMemberId,memberId)
                .eq(Order::getStatus,1);
        int count = orderService.count(orderLambdaQueryWrapper);
        if (count > 0) return true;
        return false;
    }
}
