package com.project.yallah.contollers;


import com.project.yallah.model.Users;
import com.project.yallah.service.AuthService;
import com.project.yallah.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {
    private final  AuthService authService ;
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;

    @Autowired
    public AuthController(AuthService authService, TokenService tokenService) {
        this.authService = authService;
        this.tokenService = tokenService;
    }
 @PostMapping("/token")
public String token(Authentication authentication) {
    if (authentication == null) {
        LOG.error("Authentication object is null");
        return "Authentication failed";
    }
    LOG.debug("Token requested for user: '{}'", authentication.getName());
    String token = tokenService.generateToken(authentication);
    LOG.debug("Token granted: {}", token);
    return token;
}
    @GetMapping("/")
    public String index() {
        return "Welcome to Yallah";
    }

    @PostMapping("/register")
    public String login(@RequestBody Users loginRequest) {
         return this.authService.register(loginRequest.getName() , loginRequest.getEmail()  , loginRequest.getPassword())  ;
    }

        @PostMapping("/signup")
        public String signup() {
             authService.signup();
             return  "User created successfully" ;
    }

    @GetMapping("/api/protected")
    public ResponseEntity<String> getProtectedResource() {
        // Logic to handle accessing protected resource
        return ResponseEntity.ok("You have accessed a protected resource!");
    }


}
