package com.example.demo_trasanction_thymeleaf_jpa.service;

import com.example.demo_trasanction_thymeleaf_jpa.DTO.FlightDTO;
import com.example.demo_trasanction_thymeleaf_jpa.DTO.ReservationDTO;
import com.example.demo_trasanction_thymeleaf_jpa.entity.Flight;
import com.example.demo_trasanction_thymeleaf_jpa.entity.Reservation;
import com.example.demo_trasanction_thymeleaf_jpa.entity.User;
import com.example.demo_trasanction_thymeleaf_jpa.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceDelete {
    private final ReservationRepository reservationRepository;
    private final UserService userService;
    @Autowired
    public ReservationServiceDelete(ReservationRepository reservationRepository, UserService userService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
    }

    public List<ReservationDTO> getAllReservations(){
        User user = userService.getLoggedInUser();
        List<Reservation> list = reservationRepository.findByUserId(user.getId());
        List<ReservationDTO> reservationList = list.stream().map(this::mapToDTO).collect(Collectors.toList());
        return reservationList;
    }

    public synchronized Reservation getReservation(String id){
      return null;
    }

    public synchronized void deleteReservation (String id){
    }

    private ReservationDTO mapToDTO(Reservation reservation){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setReservationId(reservation.getReservationId());
        reservationDTO.setSeatNumber(reservation.getSeatNumber());
        return reservationDTO;
    }



}
