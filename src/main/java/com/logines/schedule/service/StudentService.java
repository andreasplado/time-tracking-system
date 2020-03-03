package com.logines.schedule.service;

import com.logines.schedule.model.Class;
import com.logines.schedule.model.Student;
import com.logines.schedule.model.StudentClass;
import com.logines.schedule.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudentsWithClasses(List<Class> classes) {
        List<Student> students = getAllStudents();
        List<StudentClass> allStudentClasses = getAllStudentClasses();

        for (Student student : students) {
            List<Class> studentClasses = allStudentClasses.stream()
                    .filter(studentClass -> studentClass.getStudentId() == student.getId())
                    .map(studentClass -> getScheduleClass(classes, studentClass))
                    .distinct()
                    .collect(Collectors.toList());
            student.setClasses(studentClasses);
        }

        return students;
    }

    public Student getStudentWithClasses(List<Class> classes, int studentId) {
        Student student = getStudent(studentId);
        List<StudentClass> allStudentClasses = getAllStudentClasses();

        List<Class> studentClasses = allStudentClasses.stream()
                .filter(studentClass -> {
                    assert student != null;
                    return studentClass.getStudentId() == student.getId();
                })
                .map(studentClass -> getScheduleClass(classes, studentClass))
                .distinct()
                .collect(Collectors.toList());

        assert student != null;

        student.setClasses(studentClasses);
        student.setId(studentId);

        return student;
    }

    private Class getScheduleClass(List<Class> classes, StudentClass studentClass) {
        return classes.stream().filter(scheduleClass -> scheduleClass.getId() == studentClass.getClassId()).findFirst().orElse(null);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    private Student getStudent(int id) {
        String sql = "SELECT * FROM student WHERE id = ?";

        if (studentRepository.existsById(id)) {
            return studentRepository.getOne(id);
        } else {
            return null;
        }
    }

    private List<Student> getStudent(String name) {
        String sql = "SELECT * FROM student WHERE name = ?";
        return studentRepository.findByFirstName(name);

    }

    public List<Class> getAllClassesByStudentName(String studentName) {
        return studentRepository.getAllClassesByStudentName();
    }

    private List<StudentClass> getAllStudentClasses() {
        return studentRepository.getAllStudentClasses();
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
