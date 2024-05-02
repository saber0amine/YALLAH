package com.project.yallah.contollers;

import com.project.yallah.model.Activity;
import com.project.yallah.model.GovernmentIdType;
import com.project.yallah.model.Users;
import com.project.yallah.service.UserDetailsServiceImp;
import com.project.yallah.service.OrganisteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

@RestController
public class OrganisateurController {

private OrganisteurService organisteurService;
private UserDetailsServiceImp userDetailsServiceImp ;
@Autowired
    public OrganisateurController(OrganisteurService organisteurService) {
        this.organisteurService = organisteurService;
    }

    @GetMapping("/get-Template" )
    public ResponseEntity<String> getTemplate(){
        return ResponseEntity.ok("Hello World");
    }


@PostMapping("/add-Activity")
public ResponseEntity<Activity> AddActivity(@RequestBody Activity activity){
Activity activityRes = organisteurService.addActivity(activity);
return ResponseEntity.ok(activityRes);
}





@PostMapping("/switch-to-organisateur")
public ResponseEntity<String> SwitchToOrganinsateur(@RequestParam("governmentIdType") String governmentIdTypeStr, @RequestParam("IdentityPicture") MultipartFile file) throws ParseException, IOException {
    GovernmentIdType governmentIdType = GovernmentIdType.valueOf(governmentIdTypeStr);
    byte[] IdentityPicture = file.getBytes();
    organisteurService.SwitchToOrganisateur(governmentIdType , IdentityPicture);
    return ResponseEntity.ok("You have switched to Organisateur");
}




    @DeleteMapping("/Remove-Activity/{id}")
    public ResponseEntity<String> removeActivity(@PathVariable UUID id) {
        Boolean isRemoved = organisteurService.RemoveActivity(id);
        if (isRemoved) {
            return ResponseEntity.ok("Activity removed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Activity not found");
        }
    }












}







































