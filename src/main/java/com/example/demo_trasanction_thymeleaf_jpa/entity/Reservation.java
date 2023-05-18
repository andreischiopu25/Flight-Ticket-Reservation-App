package com.example.demo_trasanction_thymeleaf_jpa.entity;

import jakarta.persistence.*;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reservationId;

    @ManyToOne
    @JoinColumn(name= "user_id", nullable = false)
    private User user;

    private String seatNumber;

    public Reservation() {
    }

    public Reservation(Long id, String reservationId, User user, String seatNumber) {
        this.id = id;
        this.reservationId = reservationId;
        this.user = user;
        this.seatNumber = seatNumber;
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
