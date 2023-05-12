package com.example.demo_trasanction_thymeleaf_jpa.service;

import com.example.demo_trasanction_thymeleaf_jpa.repository.PassengerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerInfoService {

    PassengerInfoRepository passengerInfoRepository;

    @Autowired
    public PassengerInfoService(PassengerInfoRepository passengerInfoRepository) {
        this.passengerInfoRepository = passengerInfoRepository;
    }


}
