package com.guli.edu.controller;

import com.guli.edu.commonUtils.RMap;
import com.guli.edu.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("staservice/sta")
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @PostMapping("registerCount/{day}")
    public RMap registerCount(@PathVariable String day){
        statisticsDailyService.registerCount(day);
        return RMap.ok();
    }

    @GetMapping("showData/{type}/{begin}/{end}")
    public RMap showData(@PathVariable String type,
                         @PathVariable String begin,
                         @PathVariable String end){

        Map<String,Object> map = statisticsDailyService.showData(type,begin,end);
        return RMap.ok().data(map);
    }
}
