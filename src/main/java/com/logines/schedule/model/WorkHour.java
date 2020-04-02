package com.logines.schedule.model;

import com.logines.schedule.utils.DateUtils;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
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
    private Timestamp start_time;

    @Column(name = "end_time")
    private Timestamp end_time;

    @Column(name = "lunch_time")
    @Temporal(TemporalType.TIMESTAMP)
    private String lunch_time;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrePersist
    protected void prePersist() {
        Date date = new Date();
        if (this.created_at == null) created_at = new Timestamp(date.getTime());
        if (this.updated_at == null) updated_at = new Timestamp(date.getTime());
    }

    @PreUpdate
    protected void preUpdate() {
        Date date = new Date();
        this.updated_at = new Timestamp(date.getTime());
    }

    @PreRemove
    protected void preRemove() {
        Date date = new Date();
        this.updated_at = new Timestamp(date.getTime());
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

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getStart_time(){
        return this.start_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public Timestamp getEnd_time(){
        return this.end_time;
    }

    public Timestamp getCreated_at(){
        return this.created_at;
    }
    public void setCreated_at(Timestamp offsetDateTime){
        this.created_at = offsetDateTime;
    }

    public void setLunch_time(String lunch_time) {
        this.lunch_time = lunch_time;
    }

    public String getLunch_time() {
        return this.lunch_time;
    }
}
