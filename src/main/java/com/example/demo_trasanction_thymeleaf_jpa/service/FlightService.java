package com.example.demo_trasanction_thymeleaf_jpa.service;

import com.example.demo_trasanction_thymeleaf_jpa.DTO.FlightDTO;
import com.example.demo_trasanction_thymeleaf_jpa.DTO.FlightFilterDTO;
import com.example.demo_trasanction_thymeleaf_jpa.entity.Flight;
import com.example.demo_trasanction_thymeleaf_jpa.entity.User;
import com.example.demo_trasanction_thymeleaf_jpa.repository.FlightRepository;
import com.example.demo_trasanction_thymeleaf_jpa.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.math.BigDecimal.*;

@Service
public class FlightService{

    private FlightRepository flightRepository;
    private final UserService userService;

    @Autowired
    public FlightService(FlightRepository flightRepository, UserService userService) {
        this.flightRepository = flightRepository;
        this.userService = userService;
    }

    public List<FlightDTO> getAllFlights(){
    /*    User user =userService.getLoggedInUser();
        List<Flight> list = flightRepository.findByUserId(user.getId());*/
        List<Flight> list = flightRepository.findAll();
        List<FlightDTO> flightList = list.stream().map(this::mapToDTO).collect(Collectors.toList());
        return flightList;
    }


    public FlightDTO saveFlightDetails(FlightDTO flightDTO) throws ParseException {


        Flight flight = mapToEntity (flightDTO);

        flight.setUser(userService.getLoggedInUser());


        flight = flightRepository.save(flight);

        return mapToDTO(flight);
    }



    public Flight getFlight(String id){
        return flightRepository.findByFlightId(id).orElseThrow( ()->new RuntimeException("Flight not found for the ID"));
    }

   /* public FlightDTO bookSeats (Integer nr){
            Flight flight;
    }*/
   @Transactional
    public FlightDTO getFlightById (String id){
        Flight existingFlight = getFlight(id);
        return mapToDTO(existingFlight);
    }

    public void deleteFlight(String id){
        Flight existingExpense = getFlight(id);
        flightRepository.delete(existingExpense);
    }

    public List<FlightDTO> getFilteredExpenses(FlightFilterDTO expenseFilterDTO) throws ParseException {

        String keyword = expenseFilterDTO.getKeyword();
        String sortBy = expenseFilterDTO.getSortBy();
        String startDate = expenseFilterDTO.getStartDate();
        String endDate = expenseFilterDTO.getEndDate();

        Date sDate= !startDate.isEmpty() ? DateTimeUtil.convertStringToDate(startDate): new Date(0);
        Date eDate = !endDate.isEmpty()? DateTimeUtil.convertStringToDate(endDate) : new Date(System.currentTimeMillis());

        List<Flight> list= flightRepository.findByDestinationContainingAndDateBetween(keyword, sDate, eDate);
        List<FlightDTO> filteredList= list.stream().map(this::mapToDTO).collect(Collectors.toList());
        if (sortBy.equals("date")){
            // sort by date
            filteredList.sort((o1,o2) ->o2.getDate().compareTo(o1.getDate()));
        }else {
            filteredList.sort((o1,o2) -> o2.getPrice().compareTo(o1.getPrice()));
        }
        return filteredList;
    }

    public String totalFlights (List<FlightDTO> flight){
        BigDecimal sum = new BigDecimal(0);
        BigDecimal total = flight.stream().map(x -> x.getPrice().add(sum))
                .reduce(ZERO, BigDecimal::add);

        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
        return format.format(total).substring(1);

    }
    private FlightDTO mapToDTO (Flight flight) {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setId(flight.getId());
        flightDTO.setFlightId(flight.getFlightId());
        flightDTO.setDestination(flight.getDestination());
        flightDTO.setPickupTime(flight.getPickupTime());
        flightDTO.setArrivalTime(flight.getArrivalTime());
        flightDTO.setPrice(flight.getPrice());
        flightDTO.setAirplane(flight.getAirplane());
        flightDTO.setDate(flight.getDate());
        flightDTO.setDateString(DateTimeUtil.convertDateToString((Date) flightDTO.getDate()));
        flightDTO.setSeats(flight.getSeats());
        return flightDTO;
    }

    public Flight mapToEntity(FlightDTO flightDTO) throws ParseException {

        Flight flight =new Flight();
        flight.setId(flightDTO.getId());
        if (flight.getId() == null){
            flight.setFlightId(UUID.randomUUID().toString());
        }else flight.setFlightId(flightDTO.getFlightId());
        flight.setDestination(flightDTO.getDestination());
        flight.setPickupTime(flightDTO.getPickupTime());
        flight.setArrivalTime(flightDTO.getArrivalTime());
        flight.setPrice(flightDTO.getPrice());
        flight.setAirplane(flightDTO.getAirplane());
        flight.setDate(DateTimeUtil.convertStringToDate(flightDTO.getDateString()));
        flight.setSeats(flightDTO.getSeats());

        return flight;
    }

    @Transactional
    public void reserveTickets(String nrOfSeats, String flightId) throws ParseException {
        FlightDTO flightDTO = getFlightById(flightId);
        if (flightDTO.getSeats() >= Integer.parseInt(nrOfSeats)){
            flightDTO.setSeats(flightDTO.getSeats()-Integer.parseInt(nrOfSeats));
            Flight flight = mapToEntity (flightDTO);
            flight.setUser(userService.getLoggedInUser());

            flightRepository.save(flight);
            System.out.println("Reservare success");
            return ;
        }
        System.out.println("Rezervare nereusita");
    }
}
