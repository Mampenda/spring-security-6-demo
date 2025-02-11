package spring.security.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String username;
    private String password;

    public LoginDTO() {
        super();
    }

    public LoginDTO(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public String toString(){
        return "RegistrationDTO [username=" + this.username + ", password=" + this.password + "]";
    }
}
