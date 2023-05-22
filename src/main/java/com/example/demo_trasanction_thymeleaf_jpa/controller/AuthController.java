package com.example.demo_trasanction_thymeleaf_jpa.controller;

import com.example.demo_trasanction_thymeleaf_jpa.DTO.UserDTO;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import com.example.demo_trasanction_thymeleaf_jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/login", "/"})
    public String showLoginPage(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null  || auth instanceof AnonymousAuthenticationToken){
            return "login";
        }
        return "redirect:/flights";
    }
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") UserDTO userDTO,
                           BindingResult result,
                           Model model) {
        System.out.println("Printing the user details:"+userDTO);
        if (result.hasErrors()) {
            return "register";
        }
        userService.save(userDTO);
        model.addAttribute("successMsg", true);
        return "response";
    }
}
