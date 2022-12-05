package com.guli.edu.controller;

import com.guli.edu.commonUtils.RMap;
import com.guli.edu.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("eduorder/payLog")
public class PayLogController {

    @Autowired
    private PayLogService payLogService;

    /**
     *
     * @param orderNo 订单号
     * @return 二维码
     */

    @GetMapping("createNative/{orderNo}")
    public RMap createNative(@PathVariable String orderNo){
        Map map = payLogService.createNative(orderNo);
        System.out.println("*********返回二维码map集合" + map);
        return RMap.ok().data(map);
    }

    @GetMapping("queryPayStatus/{orderNo}")
    private RMap queryPayStatus(@PathVariable String orderNo){
        Map map = payLogService.queryPayStatus(orderNo);
        System.out.println("*********返回订单状态" + map);
        if (map == null){
            return RMap.error().msg("微信支付失败");
        }if (map.get("trade_state").equals("SUCCESS")){
            payLogService.updateOrdersStatus(map);
            return RMap.ok().msg("微信支付成功");
        }
        return RMap.ok().code(25000).msg("微信支付中");
    }
}
