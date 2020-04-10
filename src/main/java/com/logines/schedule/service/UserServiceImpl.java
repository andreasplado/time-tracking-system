package com.logines.schedule.service;

import com.logines.schedule.model.Users;
import com.logines.schedule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void save(Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Users findByid(int id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<Users> findByFullname(String username) {
        return userRepository.findByFullname(username);
    }

    @Override
    public boolean editUser(Users users) {
        if (userRepository.existsById(users.getId())) {
            userRepository.save(users);
        }
        return false;
    }

    @Override
    public boolean deleteUser(String username) {
        userRepository.deleteUser(username);
        return true;
    }
}
