package com.logines.schedule.DAO;

import com.logines.schedule.model.Job;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.DateTimeException;
import java.util.Date;
import java.util.List;

@Repository
public class JobDAO  {

    @PersistenceContext
    private EntityManager entityManager;

    public Job addJob(Job job){
        entityManager.persist(job);
        return job;
    }

    public List<Job> allJobs(){
        return entityManager.createQuery("SELECT e FROM jobs e").getResultList();
    }

    public Job viewJob(int id){
        String selectQuery = "SELECT e FROM job e WHERE id=:id";
        Job singleResult = (Job) entityManager.createQuery(selectQuery).setParameter("id", id).getSingleResult();
        return  singleResult;
    }

    public void updateJob(Job job, int id) {
        String jobTitle= job.getJobTitle();
        DateTime startTime = job.getStartTime();
        DateTime endTime = job.getEndTime();

        String updateQuery = "UPDATE job " +
                "SET jobTitle='" + jobTitle+"', startTime='"+ startTime.toString()
                +"', endTime=" + endTime.toString()
                + "updatedAt = NOW() WHERE id= :id";
        entityManager.createQuery(updateQuery).setParameter("id", id).executeUpdate();
    }

    public void deleteJob(int id) {
        String selectQuery = "SELECT e FROM ProductEntity e WHERE id= :id";
        List<Job> jobsToRemove = entityManager.createQuery(selectQuery).setParameter("id", id).getResultList();
        for (Job m: jobsToRemove) {
            entityManager.remove(m);
        }
    }
}
