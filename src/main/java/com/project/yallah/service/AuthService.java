package com.project.yallah.service;

import com.project.yallah.model.Users;
import com.project.yallah.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
 import org.springframework.security.crypto.password.PasswordEncoder;
 import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthService {

    private final UsersRepository  usersRepository  ;
     private   AuthenticationManager authenticationManager;

     private PasswordEncoder passwordEncoder ;
    private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);

     private final TokenService tokenService ;
    @Autowired
    public AuthService(UsersRepository usersRepository, AuthenticationManager authenticationManager, TokenService tokenService , PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.passwordEncoder=passwordEncoder;
    }

    public void signup() {
    }

    public String register(String name, String email , String password ) {
       // LOG.debug("Registering user: {}, email: {}, password: {}", name, email, password);
        Users user = new Users(name , email , passwordEncoder.encode(password)) ;
        this.usersRepository.save(user) ;
        Authentication authentication = new UsernamePasswordAuthenticationToken(name, password);
        String token  = tokenService.generateToken(authentication , user.getId());
        return token ;
    }



}
