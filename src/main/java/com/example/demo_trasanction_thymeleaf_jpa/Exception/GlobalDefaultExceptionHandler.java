package com.example.demo_trasanction_thymeleaf_jpa.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(FlightNotFoundException.class)
    public String handleFlightNotFoundException(HttpServletRequest request, FlightNotFoundException ex, Model model){
        model.addAttribute("notFound", true);
        model.addAttribute("message", ex.getMessage());
        return "response";
    }
}
