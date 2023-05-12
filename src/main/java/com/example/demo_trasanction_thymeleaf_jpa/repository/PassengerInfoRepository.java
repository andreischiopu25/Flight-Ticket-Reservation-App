package com.example.demo_trasanction_thymeleaf_jpa.repository;

import com.example.demo_trasanction_thymeleaf_jpa.entity.Flight;
import com.example.demo_trasanction_thymeleaf_jpa.entity.PassengerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PassengerInfoRepository extends JpaRepository<PassengerInfo, Long> {

    Optional<PassengerInfo> findByPassengerInfoId(String id);
}
