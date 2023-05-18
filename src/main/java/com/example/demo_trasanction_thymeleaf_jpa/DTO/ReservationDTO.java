package com.example.demo_trasanction_thymeleaf_jpa.DTO;

import com.example.demo_trasanction_thymeleaf_jpa.entity.User;
import jakarta.persistence.*;

public class ReservationDTO {
    private Long id;

    private String reservationId;

    private User user;

    private String seatNumber;

    public ReservationDTO(Long id, String reservationId, User user, String seatNumber) {
        this.id = id;
        this.reservationId = reservationId;
        this.user = user;
        this.seatNumber = seatNumber;
    }

    public ReservationDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}
