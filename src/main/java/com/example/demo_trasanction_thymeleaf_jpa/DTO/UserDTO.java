package com.example.demo_trasanction_thymeleaf_jpa.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

public class UserDTO {
    @NotBlank(message = "User name should not be null")
    private String name;

    @Email(message = "Invalid email address")
    private String email;

    @Range(min = 0, max = 100_000_000)
    private BigDecimal balance;

    @NotBlank(message = "Password should not be null")
    @Size(min = 5, message = "Password should be minimum {min} characters")
    private String password;

    private String confirmpassword;



    public UserDTO() {
    }

    public UserDTO(String name, String email, BigDecimal balance, String password, String confirmpassword) {
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.password = password;
        this.confirmpassword = confirmpassword;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }



}
