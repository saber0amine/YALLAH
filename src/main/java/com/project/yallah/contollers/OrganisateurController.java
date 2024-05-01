package com.project.yallah.contollers;

import com.project.yallah.model.Activity;
import com.project.yallah.service.OrganisteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("/organisateur")
public class OrganisateurController {

private OrganisteurService organisteurService;

@Autowired
    public OrganisateurController(OrganisteurService organisteurService) {
        this.organisteurService = organisteurService;
    }

    @GetMapping("/getTemplate" )
    public ResponseEntity<String> getTemplate(){
        return ResponseEntity.ok("Hello World");
    }


@PostMapping("/addActivity ")
public ResponseEntity<Activity> AddActivity(@RequestBody Activity activity){
Activity activityRes = organisteurService.addActivity(activity);
return ResponseEntity.ok(activityRes);
}



    @PostMapping("/RemoveActivity/{id} ")
    public ResponseEntity<String> RemoveActivity(@RequestBody UUID IdActivity){
       Boolean Resp = organisteurService.RemoveActivity(IdActivity);
       if (Resp == false) {
                return ResponseEntity.badRequest().body("Activity not found");
            }
       else
            return ResponseEntity.ok("You have Removed Your Activity!");
    }










































}
