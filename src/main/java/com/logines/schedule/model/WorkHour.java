package com.logines.schedule.model;

import javax.persistence.*;
import java.time.OffsetDateTime;

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
    private String start_time;

    @Column(name = "end_time")
    private String end_time;

    @Column(name = "lunch_time")
    private String lunch_time;

    @Column(name = "created_at")
    private OffsetDateTime created_at;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updated_at;

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

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStart_time(){
        return this.start_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getEnd_time(){
        return this.end_time;
    }

    public OffsetDateTime getCreated_at(){
        return this.created_at;
    }
    public void setCreated_at(OffsetDateTime offsetDateTime){
        this.created_at = offsetDateTime;
    }

    public void setLunch_time(String lunch_time) {
        this.lunch_time = lunch_time;
    }

    public String getLunch_time() {
        return this.lunch_time;
    }
}
