package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.client.EduClient;
import com.guli.edu.client.UCenterClient;
import com.guli.edu.commonUtils.orderVo.CourseWebVoOrder;
import com.guli.edu.commonUtils.orderVo.UCenterMemberForOrder;
import com.guli.edu.entity.Order;
import com.guli.edu.mapper.OrderMapper;
import com.guli.edu.service.OrderService;
import com.guli.edu.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
* @author anatkh
* @description 针对表【t_order(订单)】的数据库操作Service实现
* @createDate 2022-12-03 06:00:16
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

    @Autowired
    private EduClient eduClient;
    @Qualifier("com.guli.edu.client.UCenterClient")
    @Autowired
    private UCenterClient uCenterClient;
    @Override
    public String createOrder(String courseId, String memberIdByJwtToken) {
        UCenterMemberForOrder userInfo = uCenterClient.getUserInfo(memberIdByJwtToken);
        CourseWebVoOrder courseInfoForOrder = eduClient.getCourseInfoForOrder(courseId);
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfoForOrder.getTitle());
        order.setCourseCover(courseInfoForOrder.getCover());
        order.setTeacherName(courseInfoForOrder.getTeacherName());
        order.setTotalFee(courseInfoForOrder.getPrice());
        order.setMemberId(memberIdByJwtToken);
        order.setMobile(userInfo.getMobile());
        order.setNickname(userInfo.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}




