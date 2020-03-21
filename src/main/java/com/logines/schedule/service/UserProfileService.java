package com.logines.schedule.service;

import com.logines.schedule.model.UserProfile;
import com.logines.schedule.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile addUserDetails(UserProfile userProfile){
        return userProfileRepository.save(userProfile);
    }

    public UserProfile findUserProfile(int id){
        return userProfileRepository.getOne(id);
    }

    public Optional<UserProfile> findById(int id){
        return userProfileRepository.findById(id);
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
