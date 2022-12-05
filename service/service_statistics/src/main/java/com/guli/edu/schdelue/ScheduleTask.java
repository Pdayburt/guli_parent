package com.guli.edu.schdelue;

import com.guli.edu.service.StatisticsDailyService;
import com.guli.edu.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduleTask {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @Scheduled(cron = "0 0 1 * * ? ")
    public void statisticsTask(){
        statisticsDailyService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }


}
