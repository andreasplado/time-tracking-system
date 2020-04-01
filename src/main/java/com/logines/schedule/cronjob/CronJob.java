package com.logines.schedule.cronjob;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class CronJob {


    public static void run(){

        JobDetail helloJob = JobBuilder.newJob(HelloJob.class)
                .withIdentity("dummyJobName", "group1").build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyTriggerName", "group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();

        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(helloJob, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
