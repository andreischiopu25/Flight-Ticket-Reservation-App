package com.example.demo_trasanction_thymeleaf_jpa.controller;


import com.example.demo_trasanction_thymeleaf_jpa.DTO.FlightDTO;
import com.example.demo_trasanction_thymeleaf_jpa.DTO.FlightFilterDTO;
import com.example.demo_trasanction_thymeleaf_jpa.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
        model.addAttribute("expenses", list);
     //   model.addAttribute("filter", new FlightFilterDTO());
        String totalFlights= flightService.totalFlights(list);
    //    model.addAttribute("totalExpenses", totalFlights);
        return "flights-list";
    }

    @GetMapping("/createFlight")
    public String showExpenseForm(Model model){
        model.addAttribute("flight", new FlightDTO());
        return "flight-form";
    }

    @PostMapping("/saveOrUpdateFlight")
    public String saveOrUpdateExpenseDetails(@ModelAttribute("expense") FlightDTO flightDTO,
                                             BindingResult result) throws ParseException {
        System.out.println("Printing the Expense DTO: "+flightDTO);

       /* new FlightValidator().validate(expenseDTO, result);
        if (result.hasErrors()){
            return "expense-form";
        }*/
        flightService.saveFlightDetails(flightDTO);
        return "redirect:/flights";
    }


















}
