package com.project.yallah.service;

import com.project.yallah.model.Users;
import com.project.yallah.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthService {

    private final UsersRepository  usersRepository  ;
     private   AuthenticationManager authenticationManager;
    private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);

     private final TokenService tokenService ;
    @Autowired
    public AuthService(UsersRepository usersRepository, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.usersRepository = usersRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public void signup() {
    }

    public String register(String name, String email , String password ) {
        LOG.debug("Registering user: {}, email: {}, password: {}", name, email, password);
        this.usersRepository.save(new Users(name , email , password)  );
        Authentication authentication = new UsernamePasswordAuthenticationToken(name, password);
        String token  = tokenService.generateToken(authentication);
        return token ;
    }
}
