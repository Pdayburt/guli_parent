package com.guli.edu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "service-order")
public interface OrderClient {

    @GetMapping("eduorder/order/isBuyCourse/{courseId}/{memberId}")
    Boolean isBuyCourse(@PathVariable String courseId, @PathVariable String memberId);

}