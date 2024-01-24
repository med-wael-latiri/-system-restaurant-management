package com.example.demo.controller;


import com.example.demo.dto.RegistrationDto;
import com.example.demo.models.Users;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


   @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }
    @PostMapping("/register/save")
    public String register (@Valid @ModelAttribute("user")RegistrationDto user, BindingResult result ,Model model
    ) {
        Users existingUsersEmail = userService.findByEmail(user.getEmail());
        if (existingUsersEmail != null && existingUsersEmail.getEmail() != null && !existingUsersEmail.getEmail().isEmpty()){
           return "redirect:/register?fail";
        }
        Users existingUsersname = userService.findByUsername(user.getUsername());
        if (existingUsersname != null && existingUsersname.getUsername() != null && !existingUsersname.getUsername().isEmpty()){
            return "redirect:/register?fail";
        }
             if(result.hasErrors()) {
                 model.addAttribute("user", user);
                 return "register";
             }
             userService.saveUser(user);
             return "redirect:/restaurant?success";
    }
}
