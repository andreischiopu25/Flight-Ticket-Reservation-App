package com.example.demo_trasanction_thymeleaf_jpa.controller;


import com.example.demo_trasanction_thymeleaf_jpa.DTO.FlightDTO;
import com.example.demo_trasanction_thymeleaf_jpa.DTO.PaymentDTO;
import com.example.demo_trasanction_thymeleaf_jpa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Controller
@EnableAsync
public class FlightController {

    private final FlightService flightService;
    private final PaymentService paymentService;
    private final ReservationServiceSave reservationServiceSave;
    private final ReservationServiceDelete reservationServiceDelete;

    private final UserService userService;

    private final AsyncServiceImpl asyncService;



    @Autowired
    private JavaMailSender mailSender;


    @Autowired
    public FlightController(FlightService flightService, PaymentService paymentService, ReservationServiceSave reservationServiceSave, ReservationServiceDelete reservationServiceDelete, UserService userService, AsyncServiceImpl asyncService) {
        this.flightService = flightService;
        this.paymentService = paymentService;
        this.reservationServiceSave = reservationServiceSave;
        this.reservationServiceDelete = reservationServiceDelete;
        this.userService = userService;
        this.asyncService = asyncService;
    }


    @GetMapping("/flights")
    public String showFlightList(Model model){
        List<FlightDTO> list =  flightService.getAllFlights();
        model.addAttribute("flights", list);
        model.addAttribute("user", userService.getLoggedInUser() );
     //   model.addAttribute("filter", new FlightFilterDTO());
        String totalFlights= flightService.totalFlights(list);
    //    model.addAttribute("totalExpenses", totalFlights);
        return "flights-list";
    }

    @GetMapping("/createFlight")
    public String showFlightForm(Model model){
        model.addAttribute("flight", new FlightDTO());
        return "flight-form";
    }

    @PostMapping("/saveOrUpdateFlight")
    public String saveOrUpdateFlightDetails(@ModelAttribute("flight") FlightDTO flightDTO,
                                             BindingResult result) throws ParseException {
        System.out.println("Printing the Flight DTO: "+flightDTO);
        System.out.println("Printing the Flight DTO: "+flightDTO.getDateString());

       /*new FlightValidator().validate(flightDTO, result);
        if (result.hasErrors()){
            return "flight-form";
        }*/
        flightService.saveFlightDetails(flightDTO);
        return "redirect:/flights";
    }

    @GetMapping("/deleteFlight")
    public String deleteFlight(@RequestParam String id){

        System.out.println("Printing the expense Id: "+id);
        flightService.deleteFlight(id);
        return "redirect:/flights";
    }
    @GetMapping("/updateFlight")
    public String updateFlight(@RequestParam String id, Model model) throws ParseException{
        System.out.println("Printing the flight Id inside update method:" +id);
        FlightDTO flight =flightService.getFlightById(id);
        model.addAttribute("flight", flight);
        return "flight-form";
    }

    @GetMapping("/bookFlightTicket")
    public String bookFlightTicket(@RequestParam String id ,Model model){
        System.out.println("Printing the flight Id inside bookFlightTicket method:"+id);
        model.addAttribute("flightId", id);
        model.addAttribute("nrOfSeats", 1);
        return "reservation-form";
    }

    @PostMapping("/saveOrUpdateReservation")
    public String saveOrUpdateReservationDetails(@RequestParam(value = "nrOfSeats", required = false) String nrOfSeats,
                                                 @RequestParam(value= "flightId", required =false) String flightId,
                                                 Model model) throws ParseException{
        System.out.println(" -------> Valoare nrOfSeats = "+ nrOfSeats);
       model.addAttribute("nrOfSeats", nrOfSeats);
       model.addAttribute("flightId", flightId);
       model.addAttribute("payment", new PaymentDTO());
        return "payment-form";
    }

    @PostMapping("/saveOrUpdatePayment")
    public String showPayment(@ModelAttribute("payment") PaymentDTO paymentDTO,
                              @RequestParam(value = "flightId", required = false) String id,
                              @RequestParam(value = "nr", required = false) String nr,
                              Model model) throws ParseException, InterruptedException, ExecutionException {
        System.out.println("Printing the flight Id inside showPayment method:" +id);
        System.out.println("Printing the flight nr inside showPayment method:" +nr);

        System.out.println("------------->: "+paymentDTO.getSeats());
        long start =System.currentTimeMillis();
        CompletableFuture<Boolean> check = asyncService.asyncMethod(paymentDTO.getCardNumber());
        if (check.get()) {
            FlightDTO flight =flightService.getFlightById(id);
            //     model.addAttribute("flight", flight);
            flightService.reserveTickets(nr,id );

            BigDecimal nrOfSeats = BigDecimal.valueOf(Integer.parseInt(nr));
            BigDecimal price = nrOfSeats.multiply(flight.getPrice());
            paymentDTO.setAmount(price);
            paymentDTO.setFlightId(id);
            paymentDTO.setSeats((Integer)Integer.parseInt(nr));
            PaymentDTO newPaymentDTO = paymentService.savePaymentDetails(paymentDTO);
            String response = "task completes in " +
                    (System.currentTimeMillis() - start) + " milliseconds";
            System.out.println("Async task duration: " + response);
            model.addAttribute("paymentId", newPaymentDTO.getPaymentId());

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("contact@blueair.com");
            message.setTo("licenta.utm2023@gmail.com");
            message.setSubject("Payment Confirmation");
            message.setText("Hi there," + "\n"+"Thank you for choosing our company ! We've successfully processed your payment."+ "\n"
                    + "If you have any further questions please send us an email at BlueSky@office.com" );
            mailSender.send(message);

            return "payment-confirmation";
        }
            System.out.println(" ---------------> The card number is not correct! ");
            List<FlightDTO> list =  flightService.getAllFlights();
            model.addAttribute("flights", list);
            //   model.addAttribute("filter", new FlightFilterDTO());
            //   String totalFlights= flightService.totalFlights(list);
            return "flights-list";
    }



/*
    @GetMapping("/saveOrUpdatePayment")
    public String saveOrUpdatePaymentDetails (@ModelAttribute("payment") PaymentDTO paymentDTO,
                                              BindingResult result) throws ParseException{

        paymentService.savePaymentDetails(paymentDTO);
        return "redirect:/flights";
    }*/



    // ----------------- ADMIN----------

    @GetMapping("/createFlightAdmin")
    public String showFlightAdminForm(Model model){
        model.addAttribute("flight", new FlightDTO());
        return "flight-form";
    }





















}
