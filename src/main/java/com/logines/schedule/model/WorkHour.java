package com.logines.schedule.model;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;

@Entity
@Table(name = "work_hour", schema = "logines")
public class WorkHour {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "notes")
    private String notes;

    @Column(name = "username")
    private String username;

    @Column(name = "start_time")
    private Timestamp start_time;

    @Column(name = "end_time")
    private Timestamp end_time;


    @Column(name = "lunch_time")
    private Time lunch_time;

    @Column(name = "created_at")
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
        if (this.created_at == null) created_at = new Timestamp(System.currentTimeMillis());
        if (this.updated_at == null) updated_at = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void preUpdate() {
        this.updated_at =  new Timestamp(System.currentTimeMillis());
    }

    @PreRemove
    protected void preRemove() {
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
    public void setCreated_at(Timestamp timestamp){
        this.created_at = timestamp;
    }

    public void setLunch_time(Timestamp lunch_time) {
        this.lunch_time = lunch_time;
    }

    public Timestamp getLunch_time() {
        return this.lunch_time;
    }
}
