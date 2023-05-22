package com.example.demo_trasanction_thymeleaf_jpa.Exception;

public class FlightNotFoundException extends RuntimeException{

        private String message;

        public FlightNotFoundException(String message){
            this.message = message;
        }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
