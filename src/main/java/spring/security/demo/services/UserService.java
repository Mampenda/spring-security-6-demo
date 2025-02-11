package spring.security.demo.services;

import java.util.HashSet;
import java.util.Set;

import spring.security.demo.models.Role;
import spring.security.demo.models.User;
import spring.security.demo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("In the User Details Service.");

        // Return if there's a user with username in db, otherwise throw error
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not valid."));
    }
}