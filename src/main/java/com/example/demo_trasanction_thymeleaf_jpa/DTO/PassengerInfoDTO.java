package com.example.demo_trasanction_thymeleaf_jpa.DTO;

public class PassengerInfoDTO {

    private Long pId;
    private String passengerId;
    private String name;
    private String email;
    private String cardNumber;
    private String expirationDate;
    private String cvc;
    private String cardType;

    public PassengerInfoDTO(Long pId, String passengerId, String name, String email, String cardNumber, String expirationDate, String cvc, String cardType) {
        this.pId = pId;
        this.passengerId = passengerId;
        this.name = name;
        this.email = email;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
        this.cardType = cardType;
    }

    public PassengerInfoDTO() {
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
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

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
