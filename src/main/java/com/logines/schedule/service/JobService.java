package com.logines.schedule.service;

import com.logines.schedule.DAO.JobDAO;
import com.logines.schedule.model.Job;
import com.logines.schedule.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JobService {


    @Autowired
    private JobRepository jobRepository;

    public Job addJob(Job job){
        return jobRepository.save(job);
    }

    public Job viewJob(int id){
        return jobRepository.getOne(id);
    }

    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }

    public void updateJob(Job job){
        jobRepository.save(job);
    }

    public void deleteJob(int id){
        jobRepository.deleteById(id);
    }
}
