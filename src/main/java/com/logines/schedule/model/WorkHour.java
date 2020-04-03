package com.logines.schedule.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.logines.schedule.utils.DateUtils;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private LocalTime start_time;

    @Column(name = "end_time")
    private LocalDateTime end_time;

    @Column(name = "lunch_time")
    private LocalDateTime lunch_time;

    @Column(name = "created_at")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime created_at;

    @Column(name = "updated_at", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrePersist
    protected void prePersist() {
        if (this.created_at == null) created_at = LocalDateTime.now();
        if (this.updated_at == null) updated_at = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updated_at = LocalDateTime.now();
    }

    @PreRemove
    protected void preRemove() {
        this.updated_at = LocalDateTime.now();
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

    public void setStart_time(LocalTime start_time) {
        this.start_time = start_time;
    }

    public LocalTime getStart_time(){
        return this.start_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }

    public LocalDateTime getEnd_time(){
        return this.end_time;
    }

    public LocalDateTime getCreated_at(){
        return this.created_at;
    }
    public void setCreated_at(LocalDateTime offsetDateTime){
        this.created_at = offsetDateTime;
    }

    public void setLunch_time(LocalDateTime lunch_time) {
        this.lunch_time = lunch_time;
    }

    public LocalDateTime getLunch_time() {
        return this.lunch_time;
    }
}
