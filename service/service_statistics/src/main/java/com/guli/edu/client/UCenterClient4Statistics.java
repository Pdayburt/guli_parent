package com.guli.edu.client;

import com.guli.edu.commonUtils.RMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "service-userCenter")
public interface UCenterClient4Statistics {
    @GetMapping("educenter/member/countRegister/{day}")
    RMap countRegister(@PathVariable String day);
}
