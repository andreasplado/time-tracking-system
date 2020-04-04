package com.logines.schedule.service;

import com.logines.schedule.model.WorkHour;
import com.logines.schedule.repository.WorkHourRepository;
import org.apache.tomcat.jni.Local;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.standard.InstantFormatter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class WorkHourService {


    @Autowired
    private WorkHourRepository workHourRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public WorkHour addWorkHour(WorkHour workHour){
        return workHourRepository.save(workHour);
    }

    public WorkHour viewWorkHour(int id){
        return workHourRepository.getOne(id);
    }

    public List<WorkHour> getAllWorkHours(){
        return workHourRepository.findAll();
    }

    public List<WorkHour> getUserWorkHours(){

        return workHourRepository.findAll();
    }

    public void updateWorkHour(WorkHour workHour){
        workHourRepository.save(workHour);
    }

    public boolean deleteWorkHour(int id){
        if(workHourRepository.existsById(id)){
            workHourRepository.deleteById(id);

            return true;
        }else{
            return false;
        }

    }

    public List<WorkHour> findByUsernameReversed(String username) {
        List<WorkHour> workHours = workHourRepository.findWorkHoursByUsername(username);
        Collections.reverse(workHours);
        return workHours;
    }

    public String userWorkHoursSum(String username) {

        //String sql = "SELECT extract(start_time from logines.work_hour) as hour_of_day FROM logines.work_hour WHERE username = ?";
        List<WorkHour> workHours = workHourRepository.findWorkHoursByUsername(username);
        long diff = 0;
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;
        LocalTime lunchTime;
        for(int i= 0; i<workHours.size(); i++){

            startDateTime = LocalDateTime.parse(workHours.get(i).getStart_time());
            endDateTime = LocalDateTime.parse(workHours.get(i).getEnd_time());
            lunchTime = LocalTime.parse(workHours.get(i).getLunch_time(),
                    DateTimeFormatter.ISO_TIME);
            diff+=Duration.between(startDateTime, endDateTime).getSeconds();
            System.out.println("Between start: "  + Duration.between(startDateTime, endDateTime).getSeconds());
            long nanoseconds = lunchTime.getSecond();
            diff -= nanoseconds;
            System.out.println("lunch: "  + lunchTime.getSecond());
        }


        return Long.toString(diff / 60 / 60);
    }


    public void deleteAll(){
        workHourRepository.deleteAll();
    }

    @Scheduled(cron = "40 * * ? * MON-FRI")
    public void myScheduledMethod(){
        workHourRepository.deleteLast30DaysWorkHours();
    }
}
