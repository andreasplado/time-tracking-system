package com.logines.schedule.service;

import com.logines.schedule.model.Class;
import com.logines.schedule.model.UserProfile;
import com.logines.schedule.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {

    private static final BeanPropertyRowMapper<UserProfile> CLASS_ROW_MAPPER = BeanPropertyRowMapper.newInstance( UserProfile.class );

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile addUserDetails(UserProfile userProfile){
        return userProfileRepository.save(userProfile);
    }

    public UserProfile findUserProfile(int id){
        String sql = "SELECT * FROM userProfile WHERE id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, CLASS_ROW_MAPPER);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<UserProfile> getAllUserDetails(){
        return userProfileRepository.findAll();
    }

    public void updateUserDetails(UserProfile userProfile){
        userProfileRepository.save(userProfile);
    }

    public void deleteUserDetails(int id){
        userProfileRepository.deleteById(id);
    }
}
