package com.logines.schedule.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.logines.schedule.utils.DateUtils;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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

    @Column(name = "start_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private ZonedDateTime start_time;

    @Column(name = "end_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private ZonedDateTime end_time;


    @Column(name = "lunch_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @DateTimeFormat(pattern="HH:mm")
    private OffsetTime lunch_time;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private ZonedDateTime created_at;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
    private ZonedDateTime updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrePersist
    protected void prePersist() {
        if (this.created_at == null) created_at = OffsetDateTime.now();
        if (this.updated_at == null) updated_at = OffsetDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updated_at = OffsetDateTime.now();
    }

    @PreRemove
    protected void preRemove() {
        this.updated_at = OffsetDateTime.now();
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

    public void setStart_time(OffsetDateTime start_time) {
        this.start_time = start_time;
    }

    public OffsetDateTime getStart_time(){
        return this.start_time;
    }

    public void setEnd_time(OffsetDateTime end_time) {
        this.end_time = end_time;
    }

    public OffsetDateTime getEnd_time(){
        return this.end_time;
    }

    public OffsetDateTime getCreated_at(){
        return this.created_at;
    }
    public void setCreated_at(OffsetDateTime offsetDateTime){
        this.created_at = offsetDateTime;
    }

    public void setLunch_time(OffsetTime lunch_time) {
        this.lunch_time = lunch_time;
    }

    public OffsetTime getLunch_time() {
        return this.lunch_time;
    }
}
