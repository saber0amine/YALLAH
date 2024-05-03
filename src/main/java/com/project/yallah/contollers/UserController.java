package com.project.yallah.contollers;

import com.project.yallah.model.UserRole;
import com.project.yallah.model.Users;
import com.project.yallah.repository.ActiviyRepository;
import com.project.yallah.security.AuthenticatedUser;
import com.project.yallah.service.UserDetailsServiceImp;
import com.project.yallah.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {
    private   UserDetailsServiceImp userDetailsServiceImp ;
    private   AuthenticatedUser authenticatedUser ;

    private UserService userService ;

    @Autowired
    public UserController(UserDetailsServiceImp userDetailsServiceImp , AuthenticatedUser authenticatedUser   , UserService userService ) {
        this.userDetailsServiceImp = userDetailsServiceImp ;
        this.authenticatedUser = authenticatedUser ;
        this.userService=userService ;

   }
    @PostMapping("/book-activity/{id}")
    //@PreAuthorize("hasAnyAuthority('ROLE_ORGANISATEUR')")
public ResponseEntity<String> bookActivity(@PathVariable UUID id) throws ParseException {
        Users user =  authenticatedUser.getAuthenticatedUser() ;
    if (user!= null)  {
        Boolean Res = userService.bookActivity(id , user);
        return ResponseEntity.ok("Activity booked successfully") ;
    }

return ResponseEntity.ok("ERROR") ;

}

@DeleteMapping("/cancel-activity/{id}")
public ResponseEntity<String> cancelActivity(@PathVariable UUID id) throws ParseException {
    Users user =  authenticatedUser.getAuthenticatedUser() ;
    if (user != null)  {
        Boolean Res = userService.cancelActivity(id , user);
        return ResponseEntity.ok("Activity canceled successfully") ;
    }

    return ResponseEntity.ok("ERROR") ;

}








}
