package com.guli.edu.client;

import com.guli.edu.commonUtils.orderVo.UCenterMemberForOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(value = "service-userCenter")
public interface UCenterClient {

    @PostMapping("educenter/member/getUserInfoForOrder/{userId}")
    public UCenterMemberForOrder getUserInfo(@PathVariable String userId);
}
