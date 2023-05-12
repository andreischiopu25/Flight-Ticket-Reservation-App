package com.example.demo_trasanction_thymeleaf_jpa.entity;

import jakarta.persistence.*;

@Entity
public class PassengerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pId;
    @Column(unique = true)
    private String passengerId;
    private String name;
    private String email;
    private String cardNumber;
    private String expirationDate;
    private String cvc;
    private String cardType;

    public PassengerInfo(Long pId, String passengerId, String name, String email, String cardNumber, String expirationDate, String cvc, String cardType) {
        this.pId = pId;
        this.passengerId = passengerId;
        this.name = name;
        this.email = email;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
        this.cardType = cardType;
    }

    public PassengerInfo() {
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
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

    public String getpassengerId() {
        return passengerId;
    }

    public void setpassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
