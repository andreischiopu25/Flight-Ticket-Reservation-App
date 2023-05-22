package com.example.demo_trasanction_thymeleaf_jpa.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String paymentId;
    private String cardNumber;
    private BigDecimal amount;
    private String cardType;
    private String expirationDate;
    private String cvc;

    @ManyToOne
    @JoinColumn(name= "user_id", nullable = false, unique = false)
    private User user;


    private String  flightId;

    private Integer seats;

    public Payment(Long id, String paymentId, String cardNumber, BigDecimal amount, String cardType, String expirationDate, String cvc, User user, String flightId, Integer seats) {
        this.id = id;
        this.paymentId = paymentId;
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.cardType = cardType;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
        this.user = user;
        this.flightId = flightId;
        this.seats = seats;
    }

    public Payment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
}
