package spring.security.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*") // Make reuqest from any origin
public class AdminController {
    
    @GetMapping("/home")
    public String home() {
        return "Welcome Admin";
    }
    
    @GetMapping("/profile")
    public String profile() {
        return "Profile Page Admin";
    }
    
    @GetMapping("/settings")
    public String settings() {
        return "Settings Page Admin";
    }
    
    @GetMapping("/logout")
    public String logout() {
        return "Logout Page Admin";
    }
    
}
