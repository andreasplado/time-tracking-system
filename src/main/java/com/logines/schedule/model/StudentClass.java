package com.logines.schedule.model;

public class StudentClass {
    private long id;

    private long studentId;

    private long classId;

    public long getId(){
        return id;
    }

    public void setId( long id ){
        this.id = id;
    }

    public long getStudentId(){
        return studentId;
    }

    public void setStudentId( long studentId ){
        this.studentId = studentId;
    }

    public long getClassId(){
        return classId;
    }

    public void setClassId( long classId ){
        this.classId = classId;
    }
}
