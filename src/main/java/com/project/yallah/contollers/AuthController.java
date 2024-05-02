package com.project.yallah.contollers;


import com.project.yallah.model.Users;
import com.project.yallah.service.AuthService;
import com.project.yallah.security.TokenService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {
    private final  AuthService authService ;

    String Response = "" ;
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;

    @Autowired
    public AuthController(AuthService authService, TokenService tokenService) {
        this.authService = authService;
        this.tokenService = tokenService;
    }

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Welcome to Yallah" ) ;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerConroller(@RequestBody Users loginRequest) {
        LOG.info("Login request: {}", loginRequest);
        Response = authService.register(   loginRequest.getName() , loginRequest.getEmail() , loginRequest.getPassword())  ;
            return ResponseEntity.ok(Response);
    }

 @PostMapping("/login")
public ResponseEntity<String> loginController(@RequestBody Users loginRequest, HttpServletResponse response) {
        LOG.info("Login request: {}", loginRequest);
    String jwt = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
    if (jwt.startsWith("Invalid")) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(jwt);
    } else {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt);
        return new ResponseEntity<>(jwt, headers, HttpStatus.OK);
    }
}




}
