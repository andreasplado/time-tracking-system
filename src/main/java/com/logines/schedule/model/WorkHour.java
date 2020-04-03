package com.logines.schedule.model;

import com.logines.schedule.utils.DateUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Table(name = "work_hour", schema = "logines")
public class WorkHour {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "username")
    private String username;

    @Column(name = "start_time")
    private LocalDateTime start_time;

    @Column(name = "end_time")
    private LocalDateTime end_time;

    @Column(name = "lunch_time")
    private String lunch_time;

    @Column(name = "created_at")
    private String created_at;

    @Column(name = "updated_at", nullable = false)
    private String updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrePersist
    protected void prePersist() {
        if (this.created_at == null) created_at = DateUtils.dateToString(new Date());
        if (this.updated_at == null) updated_at = DateUtils.dateToString(new Date());
    }

    @PreUpdate
    protected void preUpdate() {
        this.updated_at = DateUtils.dateToString(new Date());
    }

    @PreRemove
    protected void preRemove() {
        this.updated_at = DateUtils.dateToString(new Date());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public LocalDateTime getStart_time(){
        return this.start_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }

    public LocalDateTime getEnd_time(){
        return this.end_time;
    }

    public String getCreated_at(){
        return this.created_at;
    }
    public void setCreated_at(String offsetDateTime){
        this.created_at = offsetDateTime;
    }

    public void setLunch_time(String lunch_time) {
        this.lunch_time = lunch_time;
    }

    public String getLunch_time() {
        return this.lunch_time;
    }
}
