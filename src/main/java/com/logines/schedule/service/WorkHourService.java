package com.logines.schedule.service;

import com.logines.schedule.model.WorkHour;
import com.logines.schedule.model.WorkHourTotal;
import com.logines.schedule.repository.WorkHourRepository;
import com.logines.schedule.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.*;
import java.util.*;

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

    public long userWorkHoursSumHelper(String username) {
        List<WorkHour> workHours = workHourRepository.findWorkHoursByUsername(username);
        long diff = 0;
        Timestamp startDateTime;
        Timestamp endDateTime;
        Duration totalDuration = Duration.ZERO;
        long milliseconds = 0;
        for (int i = 0; i < workHours.size(); i++) {
            startDateTime = workHours.get(i).getStart_time(); //2020-04-19 10:00:00.0
            endDateTime = workHours.get(i).getEnd_time(); //2020-04-19 18:00:00.0
            Duration duration = Duration.between(startDateTime.toLocalDateTime(), endDateTime.toLocalDateTime()); //00:30:00
            totalDuration = totalDuration.plusMinutes(duration.toMinutes());
        }

        if (workHours.size() != 0) {
            int seconds = (int) totalDuration.getSeconds();

            int hours = seconds / 3600;
            int minutes = (seconds % 3600) / 60;

            return totalDuration.toMinutes();
        }
        return 0;
    }


    public long userLunchHoursSumHelper(String username) {
        List<WorkHour> workHours = workHourRepository.findWorkHoursByUsername(username);
        Duration totalDuration = Duration.ZERO;
        for (WorkHour workHour : workHours) {
            // save your time in the appropriate format beforehand
            // do not use local time to store duration.
            Duration lunchTime = Duration.between(LocalTime.MIDNIGHT, workHour.getLunch_time().toLocalTime()); //00:30:00
            totalDuration = totalDuration.plusMinutes(lunchTime.toMinutes());
        }
        if (workHours.size() != 0) {
            return totalDuration.toMinutes();
        }
        return 0L;
    }


    public long allUserWorkHoursSumHelper() {
        List<WorkHour> workHours = workHourRepository.findAll();
        long diff = 0;
        Timestamp startDateTime;
        Timestamp endDateTime;
        Duration totalDuration = Duration.ZERO;
        long milliseconds = 0;
        for (int i = 0; i < workHours.size(); i++) {
            startDateTime = workHours.get(i).getStart_time(); //2020-04-19 10:00:00.0
            endDateTime = workHours.get(i).getEnd_time(); //2020-04-19 18:00:00.0
            Duration duration = Duration.between(startDateTime.toLocalDateTime(), endDateTime.toLocalDateTime()); //00:30:00
            totalDuration = totalDuration.plusMinutes(duration.toMinutes());
        }

        if (workHours.size() != 0) {
            int seconds = (int) totalDuration.getSeconds();

            int hours = seconds / 3600;
            int minutes = (seconds % 3600) / 60;

            return totalDuration.toMinutes();
        }
        return 0;
    }


    public long allUserLunchHoursSumHelper() {
        List<WorkHour> workHours = workHourRepository.findAll();
        Duration totalDuration = Duration.ZERO;
        for (WorkHour workHour : workHours) {
            // save your time in the appropriate format beforehand
            // do not use local time to store duration.
            Duration lunchTime = Duration.between(LocalTime.MIDNIGHT, workHour.getLunch_time().toLocalTime()); //00:30:00
            totalDuration = totalDuration.plusMinutes(lunchTime.toMinutes());
        }
        if (workHours.size() != 0) {
            return totalDuration.toMinutes();
        }
        return 0L;
    }


    public long userWorkHoursSumHelper2(WorkHour workHour) {

        long diff = 0;
        Timestamp startDateTime;
        Timestamp endDateTime;
        Duration totalDuration = Duration.ZERO;
        long milliseconds = 0;
        startDateTime = workHour.getStart_time(); //2020-04-19 10:00:00.0
        endDateTime = workHour.getEnd_time(); //2020-04-19 18:00:00.0
        Duration duration = Duration.between(startDateTime.toLocalDateTime(), endDateTime.toLocalDateTime()); //00:30:00
        totalDuration = totalDuration.plusMinutes(duration.toMinutes());
        int seconds = (int) totalDuration.getSeconds();
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        return totalDuration.toMinutes();
    }


    public long userLunchHoursSumHelper2(WorkHour workHour) {
        Duration totalDuration = Duration.ZERO;
        Duration lunchTime = Duration.between(LocalTime.MIDNIGHT, workHour.getLunch_time().toLocalTime()); //00:30:00
        totalDuration = totalDuration.plusMinutes(lunchTime.toMinutes());
        return totalDuration.toMinutes();
    }


    public String totalUserWorkHour(String username) {
        long timeDiff = userWorkHoursSumHelper(username) - userLunchHoursSumHelper(username);
        return TimeUtils.minutesToFullTime(timeDiff);
    }


    public List<WorkHourTotal> totalWorkHourRow(String username) {
        List<WorkHourTotal> workHourTotals = new ArrayList<>();
        List<WorkHour> workHourList = workHourRepository.findByUsername(username);

        for(WorkHour workHour: workHourList){
            WorkHourTotal workHourTotal = new WorkHourTotal();
            workHourTotal.setId(workHour.getId());
            long timeDiff = userWorkHoursSumHelper2(workHour) - userLunchHoursSumHelper2(workHour);
            workHourTotal.setWorkHoursField(TimeUtils.minutesToFullTime(timeDiff));
            workHourTotals.add(workHourTotal);
        }
        return workHourTotals;
    }


    public String userWorkHoursSum(String username) {
        List<WorkHour> workHours = workHourRepository.findWorkHoursByUsername(username);
        Timestamp startDateTime;
        Timestamp endDateTime;
        Duration totalDuration = Duration.ZERO;
        for (WorkHour workHour : workHours) {
            startDateTime = workHour.getStart_time(); //2020-04-19 10:00:00.0
            endDateTime = workHour.getEnd_time(); //2020-04-19 18:00:00.0
            Duration duration = Duration.between(startDateTime.toLocalDateTime(), endDateTime.toLocalDateTime()); //00:30:00
            totalDuration = totalDuration.plusMinutes(duration.toMinutes());
        }

        if (workHours.size() != 0) {
            int seconds = (int) totalDuration.getSeconds();
            int hours = seconds / 3600;
            int minutes = (seconds % 3600) / 60;

            seconds = (seconds % 3600) % 60;
            return TimeUtils.secondToFullTime(totalDuration.getSeconds());
        }
        return "00:00";
    }




    public String userTotalLunchHoursSum(String username) {
        List<WorkHour> workHours = workHourRepository.findWorkHoursByUsername(username);
        Duration totalDuration = Duration.ZERO;
        for (WorkHour workHour : workHours) {
            Duration lunchTime = Duration.between(LocalTime.MIDNIGHT, workHour.getLunch_time().toLocalTime()); //00:30:00
            totalDuration = totalDuration.plusMinutes(lunchTime.toMinutes());
        }
        if (workHours.size() != 0) {
            int seconds = (int) totalDuration.getSeconds();
            int hours = seconds / 3600;
            int minutes = (seconds % 3600) / 60;
            //return hours + ":" + minutes;
            return TimeUtils.secondToFullTime(totalDuration.getSeconds());
        }
        return "00:00";
    }


    public String totalWorkHoursSum() {
        long timeDiff = allUserWorkHoursSumHelper() - allUserLunchHoursSumHelper();
        return TimeUtils.minutesToFullTime(timeDiff);
    }

    /*public String totalWorkHoursSum() {
        List<WorkHour> workHours = workHourRepository.findAll();
        Timestamp startDateTime;
        Timestamp endDateTime;
        Duration totalDuration = Duration.ZERO;
        for (WorkHour workHour : workHours) {
            startDateTime = workHour.getStart_time(); //2020-04-19 10:00:00.0
            endDateTime = workHour.getEnd_time(); //2020-04-19 18:00:00.0
            Duration duration = Duration.between(startDateTime.toLocalDateTime(), endDateTime.toLocalDateTime()); //00:30:00
            totalDuration = totalDuration.plusMinutes(duration.toMinutes());
        }

        if (workHours.size() != 0) {
            int seconds = (int) totalDuration.getSeconds();
            int hours = seconds / 3600;
            int minutes = (seconds % 3600) / 60;

            seconds = (seconds % 3600) % 60;
            return hours + ":" + minutes;
        }
        return "00:00";
    }*/


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
