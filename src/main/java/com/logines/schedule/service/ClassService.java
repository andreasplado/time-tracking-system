package com.logines.schedule.service;

import com.logines.schedule.model.Class;
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
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Class> getAllClasses(){
        return namedParameterJdbcTemplate.query( "SELECT * FROM class", CLASS_ROW_MAPPER );
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
        String sql = "INSERT INTO class(name,description,teacher_name,time_minutes) values (:name, :description, :teacher_name, :time_minutes)";

        Map<String, Serializable> namedParameters = new HashMap<>();
        namedParameters.put("name", aClass.getName());
        namedParameters.put("description", aClass.getDescription());
        namedParameters.put("teacher_name", aClass.getTeacherName());
        namedParameters.put("time_minutes", aClass.getTimeMinutes());
        namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    public void updateClass(Class aClass){
        String sql = "UPDATE class set name = :name, description = :description, teacher_name = :teacherName, time_minutes = :timeMinutes where id = :id";

        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(aClass));

    }

    public boolean deleteClass(long id){
        Class aClass = getAClass(id);
        String sql ="DELETE FROM class WHERE id=" + id;
        if(aClass != null){
            namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(aClass));
            return true;
        }else{
            return false;
        }
    }
}
