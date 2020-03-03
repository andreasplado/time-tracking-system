package com.logines.schedule.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Class")
public class Class{

    private int id;
    private String name;
    private String description;
    private String teacherName;
    private int timeMinutes;
    private String startTime;

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
