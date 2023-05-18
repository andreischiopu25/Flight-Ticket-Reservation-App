package com.example.demo_trasanction_thymeleaf_jpa.threads;

import com.example.demo_trasanction_thymeleaf_jpa.DTO.ReservationDTO;
import com.example.demo_trasanction_thymeleaf_jpa.service.ReservationServiceSave;

import java.text.ParseException;

public class ThreadSave extends Thread{
    ReservationServiceSave reservationServiceSave;
    ReservationDTO reservationDTO;

    public ThreadSave(ReservationServiceSave reservationServiceSave,ReservationDTO reservationDTO) {
        this.reservationServiceSave = reservationServiceSave;
        this.reservationDTO = reservationDTO;
    }
    public void run(){
        try {
            reservationServiceSave.saveReservationDetails(reservationDTO);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
