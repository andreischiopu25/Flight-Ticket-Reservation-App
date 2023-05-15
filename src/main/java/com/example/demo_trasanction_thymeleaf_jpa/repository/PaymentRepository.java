package com.example.demo_trasanction_thymeleaf_jpa.repository;


import com.example.demo_trasanction_thymeleaf_jpa.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByPaymentId(String id);

    List<Payment> findByUserId(Long id);


}
