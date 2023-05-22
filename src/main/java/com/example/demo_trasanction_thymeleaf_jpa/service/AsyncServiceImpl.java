package com.example.demo_trasanction_thymeleaf_jpa.service;

import com.example.demo_trasanction_thymeleaf_jpa.DTO.PaymentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncServiceImpl{

    private final PaymentService paymentService;
    @Autowired
    public AsyncServiceImpl(PaymentService paymentService) throws IOException {
        this.paymentService = paymentService;
    }

    private static final Logger logger  = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Async
    public CompletableFuture<Boolean> asyncMethod(String cardNumber) throws InterruptedException, ParseException {
        logger.info("----------->>>>> Retrieve the sum ");
        int nDigits = cardNumber.length();
        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--){
            int d = cardNumber.charAt(i) - '0';

            if (isSecond == true)
                d = d * 2;
            nSum += d / 10;
            nSum += d % 10;
            isSecond = !isSecond;
        }
        if  (nSum % 10 == 0){
            return CompletableFuture.completedFuture(true);
        }else {
            return CompletableFuture.completedFuture(false);
        }
      //  Thread.sleep(1000L);
    }

}
