package com.logines.schedule.configuration;

import com.logines.schedule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Order(1)
@EnableAutoConfiguration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource ds() {
        return DataSourceBuilder.create().build();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }

    @Bean
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.parentAuthenticationManager(authenticationManagerBean());
        auth.jdbcAuthentication().dataSource(ds())
                .authoritiesByUsernameQuery("select USERNAME, ROLE from EMPLOYEE where USERNAME=?")
                .usersByUsernameQuery("select USERNAME, PASSWORD, 1 as enabled  from EMPLOYEE where USERNAME=?");
        auth.userDetailsService(auth.getDefaultUserDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
