package com.example.demo_trasanction_thymeleaf_jpa;

import com.example.demo_trasanction_thymeleaf_jpa.DTO.FlightDTO;
import com.example.demo_trasanction_thymeleaf_jpa.entity.Flight;
import com.example.demo_trasanction_thymeleaf_jpa.repository.FlightRepository;
import com.example.demo_trasanction_thymeleaf_jpa.service.FlightService;
import com.example.demo_trasanction_thymeleaf_jpa.service.UserService;
import com.example.demo_trasanction_thymeleaf_jpa.util.DateTimeUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

    private final UserService userService;

    private final FlightService flightService;

    @MockBean
    private FlightRepository flightRepository;


    private Flight flight1;
    private Flight flight2;
    private DateTimeUtil dateTimeUtil;

    @Autowired
    public FlightServiceTest(UserService userService, FlightService flightService) {
        this.userService = userService;
        this.flightService = flightService;
    }

    @BeforeEach
    void init() throws ParseException {

        flight1 = new Flight();
        flight1.setFlightId("123456789");
        flight1.setSeats(100);
        flight1.setDestination("Dubai");
        flight1.setPickupTime("7:00 AM");
        flight1.setArrivalTime("12:00 AM");
        flight1.setPrice(BigDecimal.valueOf(123));
        flight1.setAirplane("Boeing");
        flight1.setDate(dateTimeUtil.convertStringToDate("08/07/2019"));


        flight2 = new Flight();
        flight2.setFlightId("987456321");
        flight2.setSeats(100);
        flight2.setDestination("London");
        flight1.setPickupTime("6:00 AM");
        flight1.setArrivalTime("10:00 AM");
        flight1.setPrice(BigDecimal.valueOf(256));
        flight1.setAirplane("Boeing");
        flight1.setDate(dateTimeUtil.convertStringToDate("09/07/2019"));
    }
    @Test
    @WithMockUser(username = "andrei@gmail.com", password = "123456")
    void testSaveFlightDetails() throws ParseException {
        //Given
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setFlightId("abcdefg");
        flightDTO.setId(1L);
        flightDTO.setSeats(100);
        flightDTO.setDestination("Dubai");
        flightDTO.setPickupTime("7:00 AM");
        flightDTO.setArrivalTime("12:00 AM");
        flightDTO.setPrice(BigDecimal.valueOf(123));
        flightDTO.setAirplane("Boeing");
        flightDTO.setDate(dateTimeUtil.convertStringToDate("08/07/2019"));
        flightDTO.setDateString("08/07/2019");
        Flight flight = flightService.mapToEntity(flightDTO);
        when(flightRepository.save(any())).thenReturn(flight);

        //When
        FlightDTO actual = flightService.saveFlightDetails(flightDTO);
        //Then
        assertEquals(userService.getLoggedInUser().getName(), "andrei");
        verify(flightRepository, times(1)).save(any(Flight.class));
        assertEquals(flightDTO.getFlightId(), actual.getFlightId()); // Assuming mapToDTO() method returns flightDTO as it is

        assertEquals(userService.getLoggedInUser(), flight.getUser());

    }

   /* @Test
    void getAllFLightsTest(){
        //Arrange
        List<FlightDTO> list;
        list = flightService.getAllFlights();
        when(flightService.getAllFlights()).thenReturn(list);
        assertNotNull(list);
    }*/


}
