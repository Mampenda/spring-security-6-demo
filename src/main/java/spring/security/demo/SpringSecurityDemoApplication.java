package spring.security.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import spring.security.demo.models.Role;
import spring.security.demo.models.User;
import spring.security.demo.repositories.RoleRepository;
import spring.security.demo.repositories.UserRepository;

@SpringBootApplication
public class SpringSecurityDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
		// Make sure there's an admin and a user ROLE in the ROLE repository from start
		return args -> {

			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;

			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("CLIENT"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			User admin = new User( null, "admin", passwordEncoder.encode("password"), roles);
			userRepository.save(admin);
		};
	}
}