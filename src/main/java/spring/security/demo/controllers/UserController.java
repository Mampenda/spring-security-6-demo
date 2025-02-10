package spring.security.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin("*") // Make reuqest from any origin
public class UserController {
    
    @GetMapping("/home")
    public String home() {
        return "Welcome User";
    }
    
    @GetMapping("/profile")
    public String profile() {
        return "Profile Page User";
    }
    
    @GetMapping("/settings")
    public String settings() {
        return "Settings Page User";
    }
    
    @GetMapping("/logout")
    public String logout() {
        return "Logout Page User";
    }
}
