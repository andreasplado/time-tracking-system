package com.logines.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages={"com.logines.schedule"})
@EnableScheduling
public class LoginesTimeTracking {

    public static void main( String[] args ){
        SpringApplication.run( LoginesTimeTracking.class, args );
    }
}
