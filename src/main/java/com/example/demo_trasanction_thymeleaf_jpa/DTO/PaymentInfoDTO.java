package com.example.demo_trasanction_thymeleaf_jpa.DTO;

public class PaymentInfoDTO {

    private String paymentId;
    private String cardNumber;
    private double amount;
    private String cardType;
    private Long passengerId;

    public PaymentInfoDTO(String paymentId, String cardNumber, double amount, String cardType, Long passengerId) {
        this.paymentId = paymentId;
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.cardType = cardType;
        this.passengerId = passengerId;
    }

    public PaymentInfoDTO() {
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }
}
