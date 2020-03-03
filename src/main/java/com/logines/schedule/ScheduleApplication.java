package com.logines.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class ScheduleApplication {

    public static void main( String[] args ){
        SpringApplication.run( ScheduleApplication.class, args );
    }
}
