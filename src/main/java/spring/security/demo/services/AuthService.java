package spring.security.demo.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.security.demo.dto.LoginResponseDTO;
import spring.security.demo.models.Role;
import spring.security.demo.models.User;
import spring.security.demo.repositories.RoleRepository;
import spring.security.demo.repositories.UserRepository;;

@Service
@Transactional
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager; 

    @Autowired
    private TokenService tokenService;

    public User registerUser(String username, String password) {
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        String encodedPassword = passwordEncoder.encode(password);
        Set<Role> authorities = new HashSet<>();    
        
        Role userRole = roleRepository.findByAuthority("CLIENT").get();
        
        authorities.add(userRole);

        return userRepository.save(new User(null, username, encodedPassword, authorities));
    }

    public LoginResponseDTO loginUser(String username, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.genertateJWT(auth);
            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);
            
        } catch (AuthenticationException e) {
            return new LoginResponseDTO(null, "");
        }
    }
}