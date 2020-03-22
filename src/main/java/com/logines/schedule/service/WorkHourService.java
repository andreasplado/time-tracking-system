package com.logines.schedule.service;

import com.logines.schedule.model.WorkHour;
import com.logines.schedule.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WorkHourService {


    @Autowired
    private JobRepository jobRepository;

    public WorkHour addWorkHour(WorkHour workHour){
        return jobRepository.save(workHour);
    }

    public WorkHour viewWorkHour(int id){
        return jobRepository.getOne(id);
    }

    public List<WorkHour> getAllWorkHours(){
        return jobRepository.findAll();
    }

    public void updateJob(WorkHour workHour){
        jobRepository.save(workHour);
    }

    public boolean deleteJob(int id){
        if(jobRepository.existsById(id)){
            jobRepository.deleteById(id);

            return true;
        }else{
            return false;
        }

    }
}
