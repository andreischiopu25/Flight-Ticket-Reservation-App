package com.example.demo_trasanction_thymeleaf_jpa;

import com.example.demo_trasanction_thymeleaf_jpa.DTO.UserDTO;
import com.example.demo_trasanction_thymeleaf_jpa.entity.User;
import com.example.demo_trasanction_thymeleaf_jpa.repository.UserRepository;
import com.example.demo_trasanction_thymeleaf_jpa.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DemoTrasanctionThymeleafJpaApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void mapToEntityTest(){
        //Arrange
        UserDTO user1 = new UserDTO();
        user1.setName("andrei");
        user1.setEmail("andrei@gmail.com");
        user1.setBalance(BigDecimal.valueOf(124.6));
        user1.setPassword("123456");
        user1.setConfirmpassword("123456");
        User user = new User();
        user = userService.mapToEntity(user1);
        assertNotNull(user);
    }

    @Test
    void saveTest(){
        UserDTO user1 = new UserDTO();
        user1.setName("bob");
        user1.setEmail("bob@gmail.com");
        user1.setBalance(BigDecimal.valueOf(124.6));
        user1.setPassword("123456");
        user1.setConfirmpassword("123456");
        User user = new User();
        userService.save(user1);
        assertNotNull(user);
    }




}
