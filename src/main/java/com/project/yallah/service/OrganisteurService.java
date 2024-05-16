package com.project.yallah.service;

import com.project.yallah.model.*;
import com.project.yallah.repository.ActiviyRepository;
import com.project.yallah.repository.UsersRepository;
import com.project.yallah.security.AuthenticatedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrganisteurService {

    private final UsersRepository organisteurRepository ;
    private final ActiviyRepository activiyRepository ;
    private final AuthenticatedUser authenticatedUser ;
    private static final Logger LOG = LoggerFactory.getLogger(OrganisteurService.class);

    @Autowired
    public OrganisteurService(UsersRepository organisteurRepository , AuthenticatedUser authenticatedUser , ActiviyRepository activiyRepository) {
        this.organisteurRepository = organisteurRepository;
        this.authenticatedUser = authenticatedUser ;
        this.activiyRepository = activiyRepository ;
    }
    public List<String> addActivity(Activity activity, List<MultipartFile> images) {
        LOG.info("Activity images" + images);

        if (images.size() != 0)
        LOG.info("Activity images > 0" + images);
        else LOG.info("no image recivied from the mobile client");
        try{
            List<String> imagePaths = new ArrayList<>();
            for ( MultipartFile imageFile : images ) {
                Path dirPath = Paths.get("images");
                if (!Files.exists(dirPath)) {
                    Files.createDirectories(dirPath); // Create directory if it does not exist
                }
                Path path = dirPath.resolve(imageFile.getOriginalFilename()); // Resolve the file name against the directory path
                Files.write(path, imageFile.getBytes());
                imagePaths.add(path.toString());
                LOG.info("Activity images paths" + imagePaths);

            }
            Users user =  authenticatedUser.getAuthenticatedUser() ;
            if (user.getRole() == UserRole.ROLE_ORGANISATEUR) {
        Activity activityResponse = activity ;
        activityResponse.setStatus(ActivityStatus.valueOf("PENDING")) ;
        activityResponse.setOrganizer(user);
        activityResponse.setActivityImages(imagePaths);
                LOG.info("Activity images of the acitvity reponse that will stored in db" + activityResponse.getActivityImages());
                activiyRepository.save(activityResponse);
        return imagePaths ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public Boolean RemoveActivity(UUID idActivity) {
        try {
            LOG.info("Activity to remove: {}", idActivity);
            activiyRepository.deleteById(idActivity);
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    public Boolean SwitchToOrganisateur(GovernmentIdType governmentIdType, byte[] identityPicture) throws ParseException {
       try {
        Users user =  authenticatedUser.getAuthenticatedUser() ;
        if (user.getRole() == UserRole.ROLE_ORGANISATEUR) {
            return true;
        }
        if (governmentIdType == GovernmentIdType.PASSPORT)
       user.setGovernmentIdType(GovernmentIdType.PASSPORT);
        else   user.setGovernmentIdType(GovernmentIdType.IDENTITY_CARD);
        user.setIdentityPicture(identityPicture);
       user.setRole(UserRole.ROLE_ORGANISATEUR);
         organisteurRepository.save(user);
return true ;
       } catch (Exception e) {
           e.printStackTrace();
       }
return false ;
    }

    public List<Activity> getAllActivities() {

                return activiyRepository.findAll();
        }

}
