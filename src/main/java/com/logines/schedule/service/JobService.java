package com.logines.schedule.service;

import com.logines.schedule.DAO.JobDAO;
import com.logines.schedule.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JobService {

    @Autowired
    private JobDAO jobDAO;

    public Job addJob(Job job){
        return jobDAO.addJob(job);
    }

    public Job viewJob(int id){
        return jobDAO.viewJob(id);
    }

    public List<Job> getAllJobs(){
        return jobDAO.allJobs();
    }

    public void updateJob(Job job){
        int id = job.getId();
        jobDAO.updateJob(job, id);
    }

    public void deleteJob(int id){
        jobDAO.deleteJob(id);
    }
}
