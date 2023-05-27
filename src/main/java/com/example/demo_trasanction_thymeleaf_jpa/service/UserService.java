package com.example.demo_trasanction_thymeleaf_jpa.service;

import com.example.demo_trasanction_thymeleaf_jpa.DTO.PaymentDTO;
import com.example.demo_trasanction_thymeleaf_jpa.DTO.UserDTO;
import com.example.demo_trasanction_thymeleaf_jpa.entity.Payment;
import com.example.demo_trasanction_thymeleaf_jpa.entity.User;
import com.example.demo_trasanction_thymeleaf_jpa.repository.PaymentRepository;
import com.example.demo_trasanction_thymeleaf_jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.text.ParseException;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PaymentRepository paymentRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private final PasswordEncoder passwordEncoder;


    public void save(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = mapToEntity(userDTO);
        user.setUserId(UUID.randomUUID().toString());
        userRepository.save(user);
    }

    public User mapToEntity(UserDTO userDTO) {
        User user =new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setBalance(userDTO.getBalance());
        user.setPassword(userDTO.getPassword());
        return user;
    }
    public User getLoggedInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = auth.getName();
        return userRepository.findByEmail(loggedInUserEmail).orElseThrow(() ->
                new UsernameNotFoundException("User not found for the email: " + loggedInUserEmail));
    }


}