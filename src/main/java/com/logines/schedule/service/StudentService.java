package com.logines.schedule.service;

import com.logines.schedule.model.Class;
import com.logines.schedule.model.Student;
import com.logines.schedule.model.StudentClass;
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
    
    private static final BeanPropertyRowMapper<Student> STUDENT_ROW_MAPPER = BeanPropertyRowMapper.newInstance( Student.class );
    private static final BeanPropertyRowMapper<StudentClass> STUDENT_CLASS_ROW_MAPPER = BeanPropertyRowMapper.newInstance( StudentClass.class );
    private static final BeanPropertyRowMapper<Class> CLASS_ROW_MAPPER = BeanPropertyRowMapper.newInstance( Class.class );
    
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<Student> getAllStudentsWithClasses( List<Class> classes ){
        List<Student> students = getAllStudents();
        List<StudentClass> allStudentClasses = getAllStudentClasses();

        for( Student student : students ){
            List<Class> studentClasses = allStudentClasses.stream()
                    .filter( studentClass -> studentClass.getStudentId() == student.getId() )
                    .map( studentClass -> getScheduleClass( classes, studentClass ) )
                    .distinct()
                    .collect( Collectors.toList() );
            student.setClasses( studentClasses );
        }

        return students;
    }

    public Student getStudentWithClasses(List<Class> classes, long studentId){
        Student student = getStudent(studentId);
        List<StudentClass> allStudentClasses = getAllStudentClasses();

        List<Class> studentClasses = allStudentClasses.stream()
                .filter(studentClass -> {
                    assert student != null;
                    return studentClass.getStudentId() == student.getId();
                })
                .map( studentClass -> getScheduleClass( classes, studentClass ) )
                .distinct()
                .collect( Collectors.toList() );

        assert student != null;

        student.setClasses( studentClasses );
        student.setId(studentId);

        return student;
    }

    private Class getScheduleClass( List<Class> classes, StudentClass studentClass ){
        return classes.stream().filter( scheduleClass -> scheduleClass.getId() == studentClass.getClassId() ).findFirst().orElse( null );
    }

    public List<Student> getAllStudents(){
        return namedParameterJdbcTemplate.query("SELECT * FROM student", STUDENT_ROW_MAPPER);
    }

    private Student getStudent(long id){
        String sql = "SELECT * FROM student WHERE id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, STUDENT_ROW_MAPPER);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    private Student getStudent(String name){
        String sql = "SELECT * FROM student WHERE name = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{name}, STUDENT_ROW_MAPPER);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Class> getAllClassesByStudentName(String studentName){
        return namedParameterJdbcTemplate.query( "SELECT class.name, class.time_minutes, class_schedule.start_time FROM class INNER JOIN student_class ON class.id=student_class.class_id INNER JOIN student ON student.id=student_class.student_id  INNER JOIN class_schedule ON class_schedule.class_id=class.id WHERE student.name='" + studentName +"'  ", CLASS_ROW_MAPPER );
    }

    private List<StudentClass> getAllStudentClasses(){
        return namedParameterJdbcTemplate.query( "SELECT * FROM student_class", STUDENT_CLASS_ROW_MAPPER );
    }

    public Student getStudentClassesWithDistinctTime(String studentName) {
        Student student = getStudent(studentName);
        if(student != null) {
            List<Class> classList = getAllClassesByStudentName(studentName);

            List<Class> classesWithDistinctTime = classList.stream()
                    .filter(distinctByKey(Class::getStartTime))
                    .collect(Collectors.toList());
            student.setClassesWithClashes(classesWithDistinctTime);

            return student;
        }else {
            return new Student();
        }
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
