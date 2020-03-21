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

    public WorkHour addJob(WorkHour workHour){
        return jobRepository.save(workHour);
    }

    public WorkHour viewJob(int id){
        return jobRepository.getOne(id);
    }

    public List<WorkHour> getAllJobs(){
        return jobRepository.findAll();
    }

    public void updateJob(WorkHour workHour){
        jobRepository.save(workHour);
    }

    public void deleteJob(int id){
        jobRepository.deleteById(id);
    }
}