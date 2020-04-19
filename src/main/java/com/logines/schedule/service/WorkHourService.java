package com.logines.schedule.service;

import com.logines.schedule.model.WorkHour;
import com.logines.schedule.repository.WorkHourRepository;
import org.joda.time.Seconds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class WorkHourService {


    @Autowired
    private WorkHourRepository workHourRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public WorkHour addWorkHour(WorkHour workHour) {
        return workHourRepository.save(workHour);
    }

    public WorkHour viewWorkHour(int id) {
        return workHourRepository.getOne(id);
    }

    public List<WorkHour> getAllWorkHours() {
        return workHourRepository.findAll();
    }

    public List<WorkHour> getUserWorkHours() {

        return workHourRepository.findAll();
    }

    public void updateWorkHour(WorkHour workHour) {
        workHourRepository.save(workHour);
    }

    public boolean deleteWorkHour(int id) {
        if (workHourRepository.existsById(id)) {
            workHourRepository.deleteById(id);

            return true;
        } else {
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
        System.out.println(Arrays.toString(workHours.toArray()));
        long diff = 0;
        Timestamp startDateTime;
        Timestamp endDateTime;
        LocalTime lunchTime;
        long milliseconds = 0;
        for (int i = 0; i < workHours.size(); i++) {

            java.util.Date date = new java.util.Date();

            startDateTime = workHours.get(i).getStart_time(); //2020-04-19 10:00:00.0
            endDateTime = workHours.get(i).getEnd_time(); //2020-04-19 18:00:00.0
            lunchTime = workHours.get(i).getLunch_time().toLocalTime(); //00:30:00

            System.out.println(lunchTime.toString());


            // create a second time stamp
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            Date d = null;
            try {
                long difference = getDateDiff(startDateTime, endDateTime, TimeUnit.SECONDS);

                d = format.parse(lunchTime.toString());
                milliseconds += difference + lunchTime.getSecond();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(workHours.size()!= 0) {
            int seconds = (int) milliseconds / 1000;

            int hours = (int)milliseconds / 3600;
            int minutes = (seconds % 3600) / 60;
            seconds = (seconds % 3600) % 60;

            return hours + ":" + minutes;
        }
        return "00:00";
    }


    public String userLunchHoursSum(String username) {

        //String sql = "SELECT extract(start_time from logines.work_hour) as hour_of_day FROM logines.work_hour WHERE username = ?";
        List<WorkHour> workHours = workHourRepository.findWorkHoursByUsername(username);
        System.out.println(Arrays.toString(workHours.toArray()));
        long diff = 0;
        LocalTime lunchTime;
        long nanoSeconds = 0;
        for (int i = 0; i < workHours.size(); i++) {
            lunchTime = workHours.get(i).getLunch_time().toLocalTime(); //00:30:00

            // create a second time stamp
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            nanoSeconds += lunchTime.getNano();
        }
        if(workHours.size()!= 0) {
            int seconds = (int) nanoSeconds* 1000000 / 1000;

            int hours = (int)nanoSeconds * 1000000 / 3600;
            int minutes = (seconds % 3600) / 60;
            seconds = (seconds % 3600) % 60;

            return hours + ":" + minutes;
        }
        return "00:00";
    }

    public static long getDateDiff(Timestamp oldTs, Timestamp newTs, TimeUnit timeUnit) {
        long diffInMS = newTs.getTime() - oldTs.getTime();
        return timeUnit.convert(diffInMS, TimeUnit.MILLISECONDS);
    }


    public String totalWorkHoursSum() {

        //String sql = "SELECT extract(start_time from logines.work_hour) as hour_of_day FROM logines.work_hour WHERE username = ?";
        List<WorkHour> workHours = workHourRepository.findAll();
        long diff = 0;
        Timestamp startDateTime;
        Timestamp endDateTime;
        LocalTime lunchTime;
        long milliseconds = 0;
        for (int i = 0; i < workHours.size(); i++) {

            java.util.Date date = new java.util.Date();

            startDateTime = workHours.get(i).getStart_time();
            endDateTime = workHours.get(i).getEnd_time();
            lunchTime = workHours.get(i).getLunch_time().toLocalTime();


            // create a second time stamp
            SimpleDateFormat format = new SimpleDateFormat("hh:mm");
            Date d = null;
            try {
                d = format.parse(lunchTime.toString());
                milliseconds += endDateTime.getTime() - startDateTime.getTime() - d.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }


        }


        if(workHours.size()!= 0) {
            int seconds = (int) milliseconds / 1000;
            int hours = seconds / 3600;
            int minutes = (seconds % 3600) / 60;

            seconds = (seconds % 3600) % 60;
            return hours + ":" + minutes;
        }
        return "00:00";
    }


    public void deleteAll() {
        workHourRepository.deleteAll();
    }

    @Scheduled(cron = "40 12 * ? * MON-FRI")
    public void myScheduledMethod() {
        workHourRepository.deleteLast60DaysWorkHours();
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

    public List<WorkHour> findBetween(String startTime, String endTime) {
        return workHourRepository.findBetweenTime(startTime, endTime);
    }

    public List<WorkHour> findBetweenTimeAndUsername(String startTime, String endTime, String username) {
        return workHourRepository.findBetweenTimeAndUsername(startTime, endTime, username);
    }

    public List<WorkHour> findByUsername(String username) {
        return workHourRepository.findByUsername(username);
    }
}
