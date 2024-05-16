package com.project.yallah.contollers;

import com.project.yallah.dto.ActivityDto;
import com.project.yallah.dto.ActivityMapper;
import com.project.yallah.model.Activity;
import com.project.yallah.model.GovernmentIdType;
import com.project.yallah.model.Users;
import com.project.yallah.service.UserDetailsServiceImp;
import com.project.yallah.service.OrganisteurService;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.List;
import java.util.UUID;

@RestController
public class OrganisateurController {

private final OrganisteurService organisteurService;
private UserDetailsServiceImp userDetailsServiceImp ;

    private static final Logger LOG = LoggerFactory.getLogger(OrganisteurService.class);

@Autowired
    public OrganisateurController(OrganisteurService organisteurService) {
        this.organisteurService = organisteurService;
    }

    @GetMapping("/get-Template" )
    public ResponseEntity<String> getTemplate(){
        return ResponseEntity.ok("Hello World");
    }


@PostMapping("/add-Activity")
public ResponseEntity<List<String > > AddActivity(  @RequestPart("activity") Activity activity,
                                              @RequestPart("images") List<MultipartFile> images){

List<String > imagesActivitiesPath = organisteurService.addActivity(activity , images);
return ResponseEntity.ok(imagesActivitiesPath);
}





@PostMapping("/switch-to-organisateur")
public ResponseEntity<String> SwitchToOrganinsateur(@RequestParam("governmentIdType") String governmentIdTypeStr, @RequestParam("IdentityPicture") MultipartFile file) throws ParseException, IOException {
    GovernmentIdType governmentIdType = GovernmentIdType.valueOf(governmentIdTypeStr);
    byte[] IdentityPicture = file.getBytes();

    Boolean Res  = organisteurService.SwitchToOrganisateur(governmentIdType , IdentityPicture);
    if(Res){
        return ResponseEntity.ok("You have switched to Organisateur");
    }
else  {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You have not switched to Organisateur");

}
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




@GetMapping("get-All-Activities")
    public ResponseEntity<List<ActivityDto>> getAllActivities() {
        List<Activity> activities = organisteurService.getAllActivities();
        LOG.info("Activities List : {}", activities);
        List<ActivityDto> activityDtos  = ActivityMapper.INSTANCE.toDTOs(activities) ;
    LOG.info("Activities List  DTOS: {}", activityDtos);

    return ResponseEntity.ok(activityDtos ) ;
    }







}







































