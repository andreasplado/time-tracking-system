package com.logines.schedule.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "title")
    private String jobTitle;

    @Column(name = "startTime")
    private DateTime startTime;

    @Column(name = "endTime")
    private DateTime endTime;

    @Column(name = "createdAt")
    private DateTime createdAt;

    @Column(name = "updatedAt", nullable = false)
    private DateTime updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    @PrePersist
    protected void prePersist() {
        if (this.createdAt == null) createdAt = new DateTime();
        if (this.updatedAt == null) updatedAt = new DateTime();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = new DateTime();
    }

    @PreRemove
    protected void preRemove() {
        this.updatedAt = new DateTime();
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
