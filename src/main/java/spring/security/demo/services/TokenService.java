package spring.security.demo.services;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private JwtEncoder jwtEncoder; 
    
    @Autowired
    private JwtDecoder jwtDecoder;

    public String genertateJWT(Authentication auth){

        // Snapshot of time to know when token was issued
        Instant now = Instant.now();

        // Take all authorities and put them into one string
        String scope = auth.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));

        // Claimset with the information that the JWTs holds
        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .subject(auth.getName())
            .claim("roles", scope)
            .build();

        // Use JwtEncoder to encode and build a JWT token from the claimset
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }    
}
