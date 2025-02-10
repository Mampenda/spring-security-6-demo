package spring.security.demo.services;

import java.util.HashSet;
import java.util.Set;

import spring.security.demo.models.Role;
import spring.security.demo.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{


    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("In the user details service.");

        if(!username.equals("Alice")) throw new UsernameNotFoundException("Not Alice");

        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1, "USER"));
        return new User(1, "Alice", encoder.encode("password"), roles);
    }
}
