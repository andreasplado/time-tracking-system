package com.logines.schedule.cronjob;

import org.quartz.*;

public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Hello AP!");
    }
}
