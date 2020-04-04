package com.logines.schedule.service;

import com.logines.schedule.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserProfileService {

    private static final BeanPropertyRowMapper<UserProfile> CLASS_ROW_MAPPER = BeanPropertyRowMapper.newInstance( UserProfile.class );

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserProfileRepository userProfileRepository;

    public void addUserProfile(UserProfile userProfile){
        userProfileRepository.save(userProfile);
    }

    public UserProfile findUserProfile(String username){
        String sql = "SELECT * FROM logines.user_profile WHERE username = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, CLASS_ROW_MAPPER);
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

    public void deleteByUsername(String username){
        userProfileRepository.deleteByFirstName(username);
    }
}
