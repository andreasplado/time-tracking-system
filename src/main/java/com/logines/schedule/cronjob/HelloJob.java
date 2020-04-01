package com.logines.schedule.cronjob;

import com.logines.schedule.service.WorkHourService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloJob implements Job {

    @Autowired
    private WorkHourService workHourService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Hello AP!");
        workHourService.deleteAll();
    }
}
