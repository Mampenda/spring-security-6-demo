package spring.security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.security.demo.dto.LoginResponseDTO;
import spring.security.demo.dto.LoginDTO;
import spring.security.demo.models.User;
import spring.security.demo.services.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User registerUser(@RequestBody LoginDTO body){
        return authService.registerUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody LoginDTO body){
        return authService.loginUser(body.getUsername(), body.getPassword());
    }
    
}
