package com.logines.schedule.model;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "Class")
public class Class{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "time_minutes")
    private int timeMinutes;

    @Column(name = "start_time")
    private OffsetDateTime startTime;

    public int getId(){
        return id;
    }

    public void setId( int id ){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName( String name ){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription( String description ){
        this.description = description;
    }

    public String getTeacherName(){
        return teacherName;
    }

    public void setTeacherName( String teacherName ){
        this.teacherName = teacherName;
    }

    public int getTimeMinutes(){
        return timeMinutes;
    }

    public void setTimeMinutes( int timeMinutes ){
        this.timeMinutes = timeMinutes;
    }

    public OffsetDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(OffsetDateTime startTime) {
        this.startTime = startTime;
    }
}
