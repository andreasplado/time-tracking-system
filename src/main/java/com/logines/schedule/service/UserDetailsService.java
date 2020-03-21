package com.logines.schedule.service;

import com.logines.schedule.model.UserProfile;
import com.logines.schedule.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsService {
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public UserProfile addUserDetails(UserProfile userProfile){
        return userDetailsRepository.save(userProfile);
    }

    public UserProfile findById(int id){
        return userDetailsRepository.getOne(id);
    }

    public List<UserProfile> getAllUserDetails(){
        return userDetailsRepository.findAll();
    }

    public void updateUserDetails(UserProfile userProfile){
        userDetailsRepository.save(userProfile);
    }

    public void deleteUserDetails(int id){
        userDetailsRepository.deleteById(id);
    }
}