package com.logines.schedule.service;

import com.logines.schedule.model.Job;
import com.logines.schedule.model.UserDetails;
import com.logines.schedule.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsService {
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public UserDetails addUserDetails(UserDetails userDetails){
        return userDetailsRepository.save(userDetails);
    }

    public UserDetails viewUserDetails(int id){
        return userDetailsRepository.getOne(id);
    }

    public List<UserDetails> getAllUserDetails(){
        return userDetailsRepository.findAll();
    }

    public void updateUserDetails(UserDetails userDetails){
        userDetailsRepository.save(userDetails);
    }

    public void deleteUserDetails(int id){
        userDetailsRepository.deleteById(id);
    }

    public UserDetails findByUsername(String username){
        return userDetailsRepository.findByUsername(username);
    }
}
