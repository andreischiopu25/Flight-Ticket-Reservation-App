package com.example.demo_trasanction_thymeleaf_jpa.repository;

import com.example.demo_trasanction_thymeleaf_jpa.entity.Payment;
import com.example.demo_trasanction_thymeleaf_jpa.entity.Reservation;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findByReservationId(String id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Reservation> findByUserId(Long id);

}
