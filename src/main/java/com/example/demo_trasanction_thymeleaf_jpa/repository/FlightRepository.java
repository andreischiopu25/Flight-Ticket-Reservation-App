package com.example.demo_trasanction_thymeleaf_jpa.repository;

import com.example.demo_trasanction_thymeleaf_jpa.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    Optional<Flight> findByFlightId(String id);

    List<Flight> findByDestinationContainingAndDateBetween(String keyword, Date startDate, Date endDate);

    List<Flight> findByUserId(Long id);
}
