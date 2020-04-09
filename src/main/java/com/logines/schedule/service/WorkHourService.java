package com.logines.schedule.service;

import com.logines.schedule.model.WorkHour;
import com.logines.schedule.repository.WorkHourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

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
        Timestamp startDateTime;
        Timestamp endDateTime;
        LocalTime lunchTime;
        for(int i= 0; i<workHours.size(); i++){

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm.ss", Locale.US);

            startDateTime = workHours.get(i).getStart_time();
            endDateTime = workHours.get(i).getStart_time();

            lunchTime = LocalTime.ofNanoOfDay(workHours.get(i).getLunch_time().getTime());

            /*lunchTime = LocalTime.parse(workHours.get(i).getLunch_time().toString(),
                    DateTimeFormatter.ISO_TIME); */
            diff+=Duration.between(startDateTime.toLocalDateTime(), endDateTime.toLocalDateTime()).getSeconds() - lunchTime.toSecondOfDay();
            System.out.println("Between start: "  + Duration.between(startDateTime.toLocalDateTime(), endDateTime.toLocalDateTime()));

            System.out.println("lunch: "  + lunchTime.toSecondOfDay());
        }

        double diffFinal= (double) diff / 60.0 / 60.0;

        return Double.toString(diffFinal);
    }


    public void deleteAll(){
        workHourRepository.deleteAll();
    }

    @Scheduled(cron = "40 * * ? * MON-FRI")
    public void myScheduledMethod(){
        workHourRepository.deleteLast30DaysWorkHours();
    }

    public void findByEndTimeAndUsername(String endTime, String username) {
        //workHourRepository.findByEndTimeAndUsername(endTime, username);
    }

    public List<WorkHour> findByStartTimeAndUsername(String startTime, String username) {
        return workHourRepository.findByStartTimeAndUsername(startTime, username);
    }

    public List<WorkHour> findByStartTime(String startTime) {
        return workHourRepository.findByStartTime(startTime);

    }

    public List<WorkHour> findBetween(String startTime, String endTime){
        return workHourRepository.findBetweenTime(startTime, endTime);
    }

    public List<WorkHour> findBetweenTimeAndUsername(String startTime, String endTime, String username){
        return workHourRepository.findBetweenTimeAndUsername(startTime, endTime, username);
    }
}
