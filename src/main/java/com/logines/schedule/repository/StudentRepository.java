package com.logines.schedule.repository;

import com.logines.schedule.model.Class;
import com.logines.schedule.model.Job;
import com.logines.schedule.model.Student;
import com.logines.schedule.model.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("FROM student WHERE name = ?1")
    List<Student> findByFirstName(String name);

    @Query("SELECT * FROM student_class")
    List<StudentClass> getAllStudentClasses();

    @Query("SELECT class.name, class.time_minutes, class_schedule.start_time FROM class INNER JOIN student_class ON class.id=student_class.class_id INNER JOIN student ON student.id=student_class.student_id  INNER JOIN class_schedule ON class_schedule.class_id=class.id WHERE student.name= ?1")
    List<Class> getAllClassesByStudentName();
}
