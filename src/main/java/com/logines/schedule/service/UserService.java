package com.logines.schedule.service;

import com.logines.schedule.model.Role;
import com.logines.schedule.model.Users;
import com.logines.schedule.repository.RoleRepository;
import com.logines.schedule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(Users users) {
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        users.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(users);
        System.out.println("Saving users");
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users =  userRepository.findByUsername(username);
        if (users == null) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : users.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(users.getUsername(), users.getPassword(), grantedAuthorities);
    }
}
