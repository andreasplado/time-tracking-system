package com.logines.schedule.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.logines.schedule.utils.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
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
    private Instant start_time;

    @Column(name = "end_time")
    private Instant end_time;


    @Column(name = "lunch_time")
    private Instant lunch_time;

    @Column(name = "created_at")
    private Instant created_at;

    @Column(name = "updated_at", nullable = false)
    private Instant updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrePersist
    protected void prePersist() {
        if (this.created_at == null) created_at = Instant.now();
        if (this.updated_at == null) updated_at = Instant.now();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updated_at = Instant.now();
    }

    @PreRemove
    protected void preRemove() {
        this.updated_at = Instant.now();
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

    public void setStart_time(Instant start_time) {
        this.start_time = start_time;
    }

    public Instant getStart_time(){
        return this.start_time;
    }

    public void setEnd_time(Instant end_time) {
        this.end_time = end_time;
    }

    public Instant getEnd_time(){
        return this.end_time;
    }

    public Instant getCreated_at(){
        return this.created_at;
    }
    public void setCreated_at(Instant offsetDateTime){
        this.created_at = offsetDateTime;
    }

    public void setLunch_time(Instant lunch_time) {
        this.lunch_time = lunch_time;
    }

    public Instant getLunch_time() {
        return this.lunch_time;
    }
}
