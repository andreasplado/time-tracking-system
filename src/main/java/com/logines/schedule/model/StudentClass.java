package com.logines.schedule.model;

import javax.persistence.*;

@Entity
@Table(name = "StudentClass")
public class StudentClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "class_id")
    private int classId;

    public long getId(){
        return id;
    }

    public void setId( long id ){
        this.id = id;
    }

    public long getStudentId(){
        return studentId;
    }

    public void setStudentId( int studentId ){
        this.studentId = studentId;
    }

    public long getClassId(){
        return classId;
    }

    public void setClassId( int classId ){
        this.classId = classId;
    }
}
