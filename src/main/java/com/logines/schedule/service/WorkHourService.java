package com.logines.schedule.service;

import com.logines.schedule.model.WorkHour;
import com.logines.schedule.repository.WorkHourRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

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

    public int userWorkHoursSum(String username) {

        //String sql = "SELECT extract(start_time from logines.work_hour) as hour_of_day FROM logines.work_hour WHERE username = ?";
        List<WorkHour> workHours = workHourRepository.findWorkHoursByUsername(username);
        int datetimeDifference = 0;
        for(int i=0; i<workHours.size(); i++){


            String startTime = workHours.get(i).getStart_time();
            String endTime = workHours.get(i).getEnd_time();
            DateTime startTimeDate = new DateTime(startTime);
            DateTime endTimeDate = new DateTime(endTime);


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime startDate = LocalDateTime.parse(startTime, formatter);
            LocalDateTime endDate = LocalDateTime.parse(endTime, formatter);

            Duration duration = Duration.between(startDate, endDate);
            datetimeDifference+= duration.getSeconds();

        }

        //The method queryForInt(String, Object...) from the type JdbcTemplate is deprecated
        //String count = jdbcTemplate.queryForObject(sql, new Object[]{username}, String.class);

        return datetimeDifference;
    }


    public void deleteAll(){
        workHourRepository.deleteAll();
    }

    @Scheduled(cron = "40 * * ? * MON-FRI")
    public void myScheduledMethod(){
        workHourRepository.deleteLast30DaysWorkHours();
    }
}
