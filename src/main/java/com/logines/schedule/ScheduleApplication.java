package com.logines.schedule;

import com.logines.schedule.cronjob.CronJob;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages={"com.logines.schedule"})
public class ScheduleApplication {

    public static void main( String[] args ){
        SpringApplication.run( ScheduleApplication.class, args );
        CronJob.run();
    }
}
