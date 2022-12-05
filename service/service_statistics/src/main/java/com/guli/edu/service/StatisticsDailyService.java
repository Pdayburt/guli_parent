package com.guli.edu.service;

import com.guli.edu.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author anatkh
* @description 针对表【statistics_daily(网站统计日数据)】的数据库操作Service
* @createDate 2022-12-04 21:41:29
*/
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void registerCount(String day);

    Map<String, Object> showData(String type, String begin, String end);
}
