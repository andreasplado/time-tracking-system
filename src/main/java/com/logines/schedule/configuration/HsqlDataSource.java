package com.logines.schedule.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class HsqlDataSource{
    
    @Autowired
    @Qualifier("hsqlDataSource")
    private DataSource dataSource;
    
    @Bean
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(){
        return new NamedParameterJdbcTemplate( dataSource );
    }

    /*@Bean
    @Qualifier("hsqlDataSource")
    public DataSource dataSource(){
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();

        return builder.setType( EmbeddedDatabaseType.HSQL )
                .addScript( "database/create-db.sql" )
                .addScript( "database/insert-student.sql" )
                .addScript( "database/insert-class.sql" )
                .addScript( "database/insert-class_schedule.sql" )
                .addScript( "database/insert-student_class.sql" )
                .build();
    }*/

}
