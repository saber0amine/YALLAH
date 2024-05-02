package com.project.yallah.security;

import com.nimbusds.jwt.JWT;
import com.project.yallah.model.Users;
import com.project.yallah.repository.UsersRepository;
import com.project.yallah.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class AuthenticatedUser {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticatedUser.class);

    @Autowired
    private UsersRepository userRepository ;

//this method is used to get the name and password authenticated user
    public static UserDetails getAuthenticatedUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetails) authentication.getPrincipal();
    }

    //this method is used all information about authenticated user

public Users getAuthenticatedUser() throws ParseException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    LOG.info("authentication: {} ", authentication);
    if (authentication.getPrincipal() instanceof Jwt) {
        LOG.info("getPrincipal: {} ", authentication.getPrincipal());
        Jwt jwt = (Jwt) authentication.getPrincipal();
        LOG.info("jwt: {} ", jwt);

        String email = (String) jwt.getClaims().get("email");
        Users user = userRepository.findByEmail(email);
        return user;
    } else {
        throw new IllegalStateException("Unexpected principal type");
    }
}
}
