package com.logines.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan(basePackages={"com.logines.schedule"})
public class ScheduleApplication {

    public static void main( String[] args ){
        SpringApplication.run( ScheduleApplication.class, args );
    }
}
