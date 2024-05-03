package com.project.yallah.security;

import com.nimbusds.jwt.JWT;
import com.project.yallah.model.Users;
import com.project.yallah.repository.UsersRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
@Service
public class TokenService {

    private final JwtEncoder encoder;
    private final JwtDecoder jwtDecoder;

    private UsersRepository usersRepository ;
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public TokenService(JwtEncoder encoder, JwtDecoder jwtDecoder) {
        this.encoder = encoder;
        this.jwtDecoder = jwtDecoder;
    }

    public String generateToken(Authentication authentication, UUID id , String email ) {
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));


        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(id.toString())
                .claim("scope", scope)
                .claim("email" , email)
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


public boolean validateToken(String token) {
    try {
         Jwt jwt = jwtDecoder.decode(token);

        // Check the issuer
        if (!"self".equals(jwt.getIssuer())) {
            return false;
        }

        // Check if the token has expired
        return jwt.getExpiresAt() == null || !jwt.getExpiresAt().isBefore(Instant.now());
    } catch (Exception e) {
        // If any exception occurs, return false
        return false;
    }
}


    public JWT jwtDecoder(String token) {
         return (JWT) jwtDecoder.decode(token);
     }
}
