package com.example.web123.Service;

import com.example.web123.entity.User;
import com.example.web123.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private  UserRepository userRepository;

    public User saveUser(String username, String password){
       return userRepository.save(new User(username,passwordEncoder.encode(password)));
    }
}
