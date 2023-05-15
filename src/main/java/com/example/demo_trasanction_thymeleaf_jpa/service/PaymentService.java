package com.example.demo_trasanction_thymeleaf_jpa.service;

import com.example.demo_trasanction_thymeleaf_jpa.DTO.FlightDTO;
import com.example.demo_trasanction_thymeleaf_jpa.DTO.PaymentDTO;
import com.example.demo_trasanction_thymeleaf_jpa.entity.Flight;
import com.example.demo_trasanction_thymeleaf_jpa.entity.Payment;
import com.example.demo_trasanction_thymeleaf_jpa.entity.User;
import com.example.demo_trasanction_thymeleaf_jpa.repository.PaymentRepository;
import com.example.demo_trasanction_thymeleaf_jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserService userService;
    @Autowired
    public PaymentService(PaymentRepository paymentRepository,UserService userService ) {
        this.paymentRepository = paymentRepository;
        this.userService = userService;
    }

    public void save (PaymentDTO paymentDTO){
        Payment payment = mapToEntity(paymentDTO);
        payment.setPaymentId(UUID.randomUUID().toString());
        paymentRepository.save(payment);
    }

    public Payment getPayment(String id){
        return paymentRepository.findByPaymentId(id).orElseThrow(()->new RuntimeException("Payment not found for the ID"));
    }
    public PaymentDTO getPaymentById (String id){
        Payment existingPayment = getPayment(id);
        return mapToDTO(existingPayment);
    }


    public PaymentDTO savePaymentDetails(PaymentDTO paymentDTO) throws ParseException {
        //map the DTO to entity
        Payment payment = mapToEntity (paymentDTO);
        // add the logged in user to the expense entity

        payment.setUser(userService.getLoggedInUser());
      //  payment.setUser(userService.getLoggedInUser());

        //add the loggedin user to the expense entity
        //    expense.setUser(userService.getLoggedInUser());
        //Save the entity to Database
        payment = paymentRepository.save(payment);
        //map the entity to DTO
        return mapToDTO(payment);
    }

    private Payment mapToEntity(PaymentDTO paymentDTO){
        Payment payment = new Payment();
        payment.setId(paymentDTO.getId());
        if (payment.getId() == null){
            payment.setPaymentId(UUID.randomUUID().toString());
        }else payment.setPaymentId(paymentDTO.getPaymentId());
        payment.setAmount(paymentDTO.getAmount());
        payment.setCvc(paymentDTO.getCvc());
        payment.setCardNumber(paymentDTO.getCardNumber());
        payment.setCardType(paymentDTO.getCardType());
        payment.setExpirationDate(paymentDTO.getExpirationDate());
        return payment;
    }

    private PaymentDTO mapToDTO (Payment payment){
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(payment.getId());
        paymentDTO.setPaymentId(payment.getPaymentId());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setCvc(payment.getCvc());
        paymentDTO.setCardNumber(payment.getCardNumber());
        paymentDTO.setCardType(payment.getCardType());
        paymentDTO.setExpirationDate(payment.getExpirationDate());
        return paymentDTO;
    }




}
