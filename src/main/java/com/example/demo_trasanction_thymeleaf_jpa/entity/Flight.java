package com.example.demo_trasanction_thymeleaf_jpa.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String flightId;

    private String destination;

    private String pickupTime;

    private String arrivalTime;

    private BigDecimal price;

    private String airplane;

    private Date date;

   @ManyToOne
   @JoinColumn(name= "user_id", nullable = false)
    private User user;

    public Flight(Long id, String flightId, String destination, String pickupTime, String arrivalTime, BigDecimal price, String airplane, Date date) {
        this.id = id;
        this.flightId = flightId;
        this.destination = destination;
        this.pickupTime = pickupTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.airplane = airplane;
        this.date = date;
    }

    public Flight(Long id, String flightId, String destination, String pickupTime, String arrivalTime, BigDecimal price, String airplane, Date date, User user) {
        this.id = id;
        this.flightId = flightId;
        this.destination = destination;
        this.pickupTime = pickupTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.airplane = airplane;
        this.date = date;
        this.user = user;
    }

    public Flight() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAirplane() {
        return airplane;
    }

    public void setAirplane(String airplane) {
        this.airplane = airplane;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
