package com.logines.schedule.service;

import com.logines.schedule.model.WorkHour;
import com.logines.schedule.repository.WorkHourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WorkHourService {


    @Autowired
    private WorkHourRepository workHourRepository;

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

    public List<WorkHour> findByUsername(String username) {
        return workHourRepository.findWorkHoursByUsername(username);
    }


    public void deleteAll(){
        workHourRepository.deleteAll();
    }

    @Scheduled(cron = "0 * * ? * MON-FRI")
    public void myScheduledMethod(){
        workHourRepository.deleteLast30DaysWorkHours();
    }
}
