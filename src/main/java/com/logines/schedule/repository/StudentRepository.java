package com.logines.schedule.repository;

import com.logines.schedule.model.Class;
import com.logines.schedule.model.Job;
import com.logines.schedule.model.Student;
import com.logines.schedule.model.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("FROM Student WHERE name = ?1")
    List<Student> findByFirstName(String name);

    @Query("SELECT * FROM Student_class")
    List<StudentClass> getAllStudentClasses();

    @Query("SELECT Class.name, Class.time_minutes, Class_schedule.start_time FROM Class INNER JOIN Student_class ON Class.id=Student_class.class_id INNER JOIN Student ON Student.id=student_class.student_id  INNER JOIN Class_schedule ON Class_schedule.class_id=class.id WHERE Student.name= ?1")
    List<Class> getAllClassesByStudentName();
}
