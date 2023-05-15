package com.example.demo_trasanction_thymeleaf_jpa.validator;

import com.example.demo_trasanction_thymeleaf_jpa.DTO.FlightDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FlightValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return FlightDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FlightDTO flightDTO = (FlightDTO) target;
        if (flightDTO.getDateString().equals("")
                || flightDTO.getDateString().isEmpty()
                || flightDTO.getDateString()==null){

            errors.rejectValue("dateString",
                    null,
                    "Flight date should not be null");
        }
    }
}
