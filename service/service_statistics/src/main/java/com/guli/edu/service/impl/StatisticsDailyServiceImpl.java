package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.client.UCenterClient4Statistics;
import com.guli.edu.commonUtils.RMap;
import com.guli.edu.entity.StatisticsDaily;
import com.guli.edu.mapper.StatisticsDailyMapper;
import com.guli.edu.service.StatisticsDailyService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author anatkh
 * @description 针对表【statistics_daily(网站统计日数据)】的数据库操作Service实现
 * @createDate 2022-12-04 21:41:29
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily>
        implements StatisticsDailyService {


    @Autowired
    private UCenterClient4Statistics uCenterClient4Statistics;

    @Override
    public void registerCount(String day) {

        LambdaQueryWrapper<StatisticsDaily> statisticsDailyLambdaQueryWrapper = new LambdaQueryWrapper<>();
        statisticsDailyLambdaQueryWrapper
                .eq(StatisticsDaily::getDateCalculated, day);
        baseMapper.delete(statisticsDailyLambdaQueryWrapper);
        RMap register = uCenterClient4Statistics.countRegister(day);
        Integer countRegister = (Integer) register.getData().get("countRegister");
        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setRegisterNum(countRegister);
        statisticsDaily.setDateCalculated(day);
        statisticsDaily.setVideoViewNum(RandomUtils.nextInt(100, 200));
        statisticsDaily.setLoginNum(RandomUtils.nextInt(100, 200));
        statisticsDaily.setCourseNum(RandomUtils.nextInt(100, 200));
        baseMapper.insert(statisticsDaily);

    }

    @Override
    public Map<String, Object> showData(String type, String begin, String end) {
        LambdaQueryWrapper<StatisticsDaily> statisticsDailyLambdaQueryWrapper = new LambdaQueryWrapper<>();
        statisticsDailyLambdaQueryWrapper
                .between(StatisticsDaily::getDateCalculated, begin, end);
        if ("login_num".equals(type)) {
            statisticsDailyLambdaQueryWrapper.select(StatisticsDaily::getDateCalculated, StatisticsDaily::getLoginNum);
        } else if ("register_num".equals(type)) {
            statisticsDailyLambdaQueryWrapper.select(StatisticsDaily::getDateCalculated, StatisticsDaily::getRegisterNum);
        } else if ("video_view_num".equals(type)) {
            statisticsDailyLambdaQueryWrapper.select(StatisticsDaily::getDateCalculated, StatisticsDaily::getVideoViewNum);
        } else if ("course_num".equals(type)) {
            statisticsDailyLambdaQueryWrapper.select(StatisticsDaily::getDateCalculated, StatisticsDaily::getCourseNum);
        }
        List<StatisticsDaily> staList = baseMapper.selectList(statisticsDailyLambdaQueryWrapper);
        ArrayList<String> date_calculatedList = new ArrayList<>();
        ArrayList<Integer> numDataList = new ArrayList<>();
        staList.forEach(item -> {
            date_calculatedList.add(item.getDateCalculated());
            switch (type) {
                case "login_num":
                    numDataList.add(item.getLoginNum());
                    break;
                case "register_num":
                    numDataList.add(item.getRegisterNum());
                    break;
                case "video_view_num":
                    numDataList.add(item.getVideoViewNum());
                    break;
                case "course_num":
                    numDataList.add(item.getCourseNum());
            }
        });
        HashMap<String, Object> map = new HashMap<>();
        map.put("date_calculatedList", date_calculatedList);
        map.put("numDataList", numDataList);
        return map;

    }
}




