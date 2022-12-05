package com.guli.edu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wxpay.sdk.WXPayUtil;
import com.guli.edu.entity.Order;
import com.guli.edu.entity.PayLog;
import com.guli.edu.entity.constant.WXPayConstant;
import com.guli.edu.exception.GuliException;
import com.guli.edu.service.OrderService;
import com.guli.edu.service.PayLogService;
import com.guli.edu.mapper.PayLogMapper;
import com.guli.edu.utils.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* @author anatkh
* @description 针对表【t_pay_log(支付日志表)】的数据库操作Service实现
* @createDate 2022-12-03 06:00:50
*/
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog>
    implements PayLogService{

    @Autowired
    private OrderService orderService;
    @Override
    public Map createNative(String orderNo) {
        try {
            LambdaQueryWrapper<Order> orderLambdaQueryWrapper = new LambdaQueryWrapper<>();
            orderLambdaQueryWrapper
                    .eq(Order::getOrderNo,orderNo);
            Order order = orderService.getOne(orderLambdaQueryWrapper);

            Map m = new HashMap();
            //设置支付参数
            m.put("appid", WXPayConstant.APP_ID);
            m.put("mch_id", WXPayConstant.PARTNER);
            m.put("nonce_str", WXPayUtil.generateNonceStr());
            m.put("body", order.getCourseTitle());
            m.put("out_trade_no", orderNo);
            m.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");
            m.put("spbill_create_ip", "127.0.0.1");
            m.put("notify_url", WXPayConstant.NOTIFY_URL);
            m.put("trade_type", "NATIVE");

            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            //client设置参数
            client.setXmlParam(WXPayUtil.generateSignedXml(m, WXPayConstant.PARTNER_KEY));
            client.setHttps(true);
            client.post();
            //3、返回第三方的数据
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            //4、封装返回结果集
            Map map = new HashMap<>();
            map.put("out_trade_no", orderNo);
            map.put("course_id", order.getCourseId());
            map.put("total_fee", order.getTotalFee());
            map.put("result_code", resultMap.get("result_code"));
            map.put("code_url", resultMap.get("code_url"));
            //微信支付二维码2小时过期，可采取2小时未支付取消订单
            //redisTemplate.opsForValue().set(orderNo, map, 120, TimeUnit.MINUTES);
            return map;
        }catch (Exception e){
            throw new GuliException(20001, "创建微信二维码失败");
        }
    }

    @Override
    public Map queryPayStatus(String orderNo) {
        try {
            //1、封装参数
            Map m = new HashMap<>();
            m.put("appid", WXPayConstant.APP_ID);
            m.put("mch_id", WXPayConstant.PARTNER);
            m.put("out_trade_no", orderNo);
            m.put("nonce_str", WXPayUtil.generateNonceStr());
            //2、设置请求
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(m, WXPayConstant.PARTNER_KEY));
            client.setHttps(true);
            client.post();
            //3、返回第三方的数据
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            //6、转成Map
            //7、返回
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void updateOrdersStatus(Map map) {
        //获取订单id
        String orderNo = (String) map.get("out_trade_no");
        //根据订单id查询订单信息
        LambdaQueryWrapper<Order> orderLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderLambdaQueryWrapper
                .eq(Order::getOrderNo,orderNo);
        Order order = orderService.getOne(orderLambdaQueryWrapper);

        if(order.getStatus().intValue() == 1) return;
        order.setStatus(1);
        orderService.updateById(order);
        //记录支付日志
        PayLog payLog=new PayLog();
        payLog.setOrderNo(order.getOrderNo());//支付订单号
        payLog.setPayTime(new Date());
        payLog.setPayType(1);//支付类型
        payLog.setTotalFee(order.getTotalFee());//总金额(分)
        payLog.setTradeState((String) map.get("trade_state"));//支付状态
        payLog.setTransactionId((String) map.get("transaction_id"));
        payLog.setAttr(JSONObject.toJSONString(map));
        baseMapper.insert(payLog);//插入到支付日志表
    }
}




