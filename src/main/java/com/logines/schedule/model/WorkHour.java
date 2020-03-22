package com.logines.schedule.model;

import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "workHour")
public class WorkHour {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "created_at")
    private DateTime createdAt;

    @Column(name = "updated_at", nullable = false)
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
