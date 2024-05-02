package com.project.yallah.service;

import com.project.yallah.contollers.AuthController;
import com.project.yallah.model.*;
import com.project.yallah.repository.ActiviyRepository;
import com.project.yallah.repository.UsersRepository;
import com.project.yallah.security.AuthenticatedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.UUID;

@Service
public class OrganisteurService {

    private UsersRepository organisteurRepository ;
    private ActiviyRepository activiyRepository ;
    private AuthenticatedUser authenticatedUser ;
    private static final Logger LOG = LoggerFactory.getLogger(OrganisteurService.class);

    @Autowired
    public OrganisteurService(UsersRepository organisteurRepository , AuthenticatedUser authenticatedUser , ActiviyRepository activiyRepository) {
        this.organisteurRepository = organisteurRepository;
        this.authenticatedUser = authenticatedUser ;
        this.activiyRepository = activiyRepository ;
    }
    public Activity addActivity(Activity activity) {
        try{
            Users user =  authenticatedUser.getAuthenticatedUser() ;
            if (user.getRole() == UserRole.ORGANISATEUR) {
        Activity activityResponse = activity ;
        activityResponse.setStatus(ActivityStatus.valueOf("PENDING")) ;
        activityResponse.setUser(user);
        user.setActivities(activityResponse);
        organisteurRepository.save(user);
        activityResponse=activiyRepository.save(activity);
        return activityResponse;
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


    public void SwitchToOrganisateur(GovernmentIdType governmentIdType, byte[] identityPicture) throws ParseException {
       try {
        Users user =  authenticatedUser.getAuthenticatedUser() ;

        if (governmentIdType == GovernmentIdType.PASSPORT)
       user.setGovernmentIdType(GovernmentIdType.PASSPORT);
        else   user.setGovernmentIdType(GovernmentIdType.IDENTITY_CARD);
        user.setIdentityPicture(identityPicture);
       user.setRole(UserRole.ORGANISATEUR);
         organisteurRepository.save(user);

       } catch (Exception e) {
           e.printStackTrace();
       }

    }
}
