package com.logines.schedule.repository;

import com.logines.schedule.model.Class;
import com.logines.schedule.model.Job;
import com.logines.schedule.model.Student;
import com.logines.schedule.model.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("FROM Student WHERE name = ?1")
    List<Student> findByFirstName(String name);

    @Query("SELECT * FROM StudentClass")
    List<StudentClass> getAllStudentClasses();

    @Query("SELECT Class.name, Class.timeMinutes, ClassSchedule.startTime FROM Class INNER JOIN StudentClass ON Class.id=StudentClass.classId INNER JOIN Student ON Student.id=studentClass.studentId  INNER JOIN ClassSchedule ON ClassSchedule.classId=class.id WHERE Student.name== :name")
    List<Class> getAllClassesByStudentName(@Param("name") String name);
}
