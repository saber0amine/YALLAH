package com.project.yallah.contollers;


import com.project.yallah.model.Users;
import com.project.yallah.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final  AuthService authService ;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @GetMapping("/")
    public String index() {
        return "Welcome to Yallah";
    }

    @PostMapping("/login")
    public String login(@RequestBody Users loginRequest) {
         return this.authService.login(loginRequest.getName() , loginRequest.getEmail() )  ;
    }

        @PostMapping("/signup")
        public String signup() {
             authService.signup();
             return  "User created successfully" ;
    }

}
