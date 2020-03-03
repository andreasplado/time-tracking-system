package com.logines.schedule.model;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Student")
public class Student{
    
    private long id;
    private String name;
    private String email;
    private List<Class> classes;
    private List<Class> classesWithClashes;

    public long getId(){
        return id;
    }

    public void setId( long id ){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName( String name ){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail( String email ){
        this.email = email;
    }

    public List<Class> getClasses(){
        return classes;
    }

    public void setClasses( List<Class> classes ){
        this.classes = classes;
    }

    public List<Class> getClassesWithClashes() {
        return classesWithClashes;
    }

    public void setClassesWithClashes(List<Class> classesWithClashes) {
        this.classesWithClashes = classesWithClashes;
    }
}
