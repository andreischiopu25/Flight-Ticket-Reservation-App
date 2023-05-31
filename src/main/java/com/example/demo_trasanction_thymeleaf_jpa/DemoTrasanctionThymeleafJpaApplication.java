package com.example.demo_trasanction_thymeleaf_jpa;

import com.example.demo_trasanction_thymeleaf_jpa.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class DemoTrasanctionThymeleafJpaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoTrasanctionThymeleafJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);

    }
}
