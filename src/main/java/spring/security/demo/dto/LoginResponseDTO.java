package spring.security.demo.dto;

import lombok.Getter;
import lombok.Setter;
import spring.security.demo.models.User;

@Getter
@Setter
public class LoginResponseDTO { // Create a response for a login, becasuse we pass back both the user and the string jwt token

    private User user;
    private String jwt;
    
    // No args constructor
    public LoginResponseDTO() {
        super();
    }

    // All args constructor
    public LoginResponseDTO(User user, String jwt) {
        this.user = user;
        this.jwt = jwt;
    }

}
