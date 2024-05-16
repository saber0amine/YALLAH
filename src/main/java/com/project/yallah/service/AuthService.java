package com.project.yallah.service;

import com.project.yallah.model.UserRole;
import com.project.yallah.model.Users;
import com.project.yallah.repository.UsersRepository;
import com.project.yallah.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
 import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthService {

    private final UsersRepository  usersRepository  ;
     private final AuthenticationManager authenticationManager;

     private final PasswordEncoder passwordEncoder ;
    private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);

     private final TokenService tokenService ;
    @Autowired
    public AuthService(UsersRepository usersRepository, AuthenticationManager authenticationManager, TokenService tokenService , PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.passwordEncoder=passwordEncoder;
    }

    public String login(String email  , String passwrod  ) {
        LOG.info("login request: {} {}", email  , passwrod);

        Users user = usersRepository.findByEmail(email)  ;
        LOG.info("login user: {}  ", user);

        if (user != null ) {
            LOG.info("Im here: {}  ", user);

            if ( !passwordEncoder.matches(passwrod, user.getPassword())) {
                LOG.info("password incorrect: {}  ", user);

                return "Invalid email or password" ;
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), passwrod ) );
                LOG.info("authenticatiom: {}  ", authentication);

                String jwt  = tokenService.generateToken(authentication , user.getId() , email);
                LOG.info("jwt: {}  ", jwt);

             return jwt;

    }
        else
        return "You should Register First" ;

    }


    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public String register(String name, String email , String password, Date age, byte[] profilePicture) {
        LOG.info("Register request: {} {} {} ", email , name , password);
         if(usersRepository.findByEmail(email) != null) {
            return "Email already exists" ;
             }
         else if (name.isBlank() || password.isEmpty() || email.isEmpty()  ) {
             return "Fill All Fields" ;
         } else if (!isValidEmail(email)) {
             return "Invalid Email" ;
         }
         Users user = new Users(name , email , passwordEncoder.encode(password)) ;
         user.setRole(UserRole.ROLE_USER);
        user.setAge(age);
        user.setProfilePicture(profilePicture);
         usersRepository.save(user) ;

        return "User registered successfully" ;
    }



}
