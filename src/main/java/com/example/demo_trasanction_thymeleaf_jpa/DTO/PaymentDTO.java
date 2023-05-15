package com.example.demo_trasanction_thymeleaf_jpa.DTO;

public class PaymentDTO {
    private Long id;
    private String paymentId;
    private String cardNumber;
    private double amount;
    private String cardType;
    private String expirationDate;
    private String cvc;


    public PaymentDTO(Long id, String paymentId, String cardNumber, double amount, String cardType, String expirationDate, String cvc) {
        this.id = id;
        this.paymentId = paymentId;
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.cardType = cardType;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
    }

    public PaymentDTO() {
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
}
