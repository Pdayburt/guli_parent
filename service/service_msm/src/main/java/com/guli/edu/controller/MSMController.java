package com.guli.edu.controller;

import com.guli.edu.commonUtils.RMap;
import com.guli.edu.service.MSMService;
import com.guli.edu.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@CrossOrigin
@RestController
@RequestMapping("edumsm/msm")
public class MSMController {

    @Autowired
    private MSMService msmService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("send/{phone}")
    public RMap send(@PathVariable String phone){

        String s = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(s)) return RMap.ok();
        String sixBitRandom = RandomUtil.getSixBitRandom();
        Boolean aBoolean = msmService.sendSMS(phone, sixBitRandom, 5);
        if (aBoolean){
            redisTemplate.opsForValue().set(phone, sixBitRandom, 5, TimeUnit.MINUTES);
            return RMap.ok();
        }else {
            return RMap.error().msg("腾讯云短信发送失败");
        }
    }


}
