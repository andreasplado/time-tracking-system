package com.logines.schedule.service;

import com.logines.schedule.model.Class;
import com.logines.schedule.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassService {
    
    private static final BeanPropertyRowMapper<Class> CLASS_ROW_MAPPER = BeanPropertyRowMapper.newInstance( Class.class );

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ClassRepository classRepository;


    public List<Class> getAllClasses(){
        return classRepository.findAll();
    }

    public Class getAClass(long id){
        String sql = "SELECT * FROM class WHERE id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, CLASS_ROW_MAPPER);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public void saveClass(Class aClass){
        classRepository.save(aClass);
    }

    public void updateClass(Class aClass){
        classRepository.updateClass(aClass.getName(), aClass.getDescription(), aClass.getTeacherName(), aClass.getStartTime(), aClass.getId());
    }

    public boolean deleteClass(Class aClass){
        if(getAClass(aClass.getId()) != null){
            classRepository.delete(aClass);
            //Lol
            return true;
        }else{
            return false;
        }
    }
}
