package com.example.demo_trasanction_thymeleaf_jpa.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.sql.Date;

public class FlightDTO {

    private Long id;

    private String flightId;

    private String destination;

    private String pickupTime;

    private String arrivalTime;

    private BigDecimal price;

    private String airplane;

    private Date date;

    private String dateString;

    public FlightDTO(Long id, String flightId, String destination, String pickupTime, String arrivalTime, BigDecimal price, String airplane, Date date, String dateString) {
        this.id = id;
        this.flightId = flightId;
        this.destination = destination;
        this.pickupTime = pickupTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.airplane = airplane;
        this.date = date;
        this.dateString = dateString;
    }

    public FlightDTO() {
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
        destination = destination;
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

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }


}
