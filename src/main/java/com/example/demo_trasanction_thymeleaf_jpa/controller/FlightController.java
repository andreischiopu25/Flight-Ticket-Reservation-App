package com.example.demo_trasanction_thymeleaf_jpa.controller;


import com.example.demo_trasanction_thymeleaf_jpa.DTO.FlightDTO;
import com.example.demo_trasanction_thymeleaf_jpa.DTO.FlightFilterDTO;
import com.example.demo_trasanction_thymeleaf_jpa.DTO.PassengerInfoDTO;
import com.example.demo_trasanction_thymeleaf_jpa.service.FlightService;
import com.example.demo_trasanction_thymeleaf_jpa.validator.FlightValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;

@Controller
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }
    @GetMapping("/flights")
    public String showFlightList(Model model){
        List<FlightDTO> list =  flightService.getAllFlights();
        model.addAttribute("flights", list);
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
    @GetMapping("/updateExpense")
    public String updateFlight(@RequestParam String id, Model model) throws ParseException{
        System.out.println("Printing the flight Id inside update method:" +id);
        FlightDTO flight =flightService.getFlightById(id);
        model.addAttribute("flight", flight);
        return "flight-form";
    }
    @GetMapping("/bookFlightTicket")
    public String payFlight(Model model){
        model.addAttribute("passengerinfo", new PassengerInfoDTO());
        return "passengerinfo-form";

    }





















}
