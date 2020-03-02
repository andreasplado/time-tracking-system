package com.logines.schedule.service;

import com.logines.schedule.model.Class;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private static final BeanPropertyRowMapper<Class> CLASS_ROW_MAPPER = BeanPropertyRowMapper.newInstance( Class.class );
}