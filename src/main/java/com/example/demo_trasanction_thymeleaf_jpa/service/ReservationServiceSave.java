package com.example.demo_trasanction_thymeleaf_jpa.service;

import com.example.demo_trasanction_thymeleaf_jpa.DTO.ReservationDTO;
import com.example.demo_trasanction_thymeleaf_jpa.entity.Reservation;
import com.example.demo_trasanction_thymeleaf_jpa.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.UUID;

@Service
public class ReservationServiceSave  {

    private final ReservationRepository reservationRepository;
    private final UserService userService;
    @Autowired
    public ReservationServiceSave(ReservationRepository reservationRepository, UserService userService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
    }

    @Transactional
    public ReservationDTO saveReservationDetails(ReservationDTO reservationDTO) throws ParseException{
        //map the DTO to entity
        Reservation reservation = mapToEntity (reservationDTO);
        reservation.setUser(userService.getLoggedInUser());
        //Save the entity to Database
        reservation = reservationRepository.save(reservation);
        //map the entity to DTO
        return mapToDTO(reservation);
    }

    private Reservation mapToEntity(ReservationDTO reservationDTO){
        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        if (reservation.getId() == null) {
            reservation.setReservationId(UUID.randomUUID().toString());
        }else reservation.setReservationId(reservationDTO.getReservationId());

        reservation.setSeatNumber(reservationDTO.getSeatNumber());
        return reservation;
    }

    private ReservationDTO mapToDTO(Reservation reservation){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setReservationId(reservation.getReservationId());
        reservationDTO.setSeatNumber(reservation.getSeatNumber());
        return reservationDTO;
    }



}
